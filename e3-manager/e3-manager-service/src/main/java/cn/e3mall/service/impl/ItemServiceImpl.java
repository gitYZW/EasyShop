package cn.e3mall.service.impl;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.pojo.TbItemExample.Criteria;
import cn.e3mall.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource
	private Destination topicDestination;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ITEM_INFO_PRE}")
	private String ITEM_INFO_PRE;
	@Value("${EXIST_TIME}")
	private Integer EXIST_TIME;

	public TbItem findItemById(long itemId) {
		// 查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId + ":BASE");
			if (StringUtils.isNoneBlank(json)) {
				TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
				return tbItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 根据主键查询
		// return itemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			// 添加缓存
			try {
				jedisClient.set(ITEM_INFO_PRE + ":" + itemId + ":BASE", JsonUtils.objectToJson(list.get(0)));
				// 设置过期时间
				jedisClient.expire(ITEM_INFO_PRE + ":" + itemId + ":BASE", EXIST_TIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list.get(0);
		}
		return null;
	}

	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 设置分页信息
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 取出分页结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

	public E3Result addItem(TbItem item, String desc) {
		// 生成商品ID
		final long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 商品状态：1.正常 2.下架 3.删除
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		// 添加商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		// 发送添加商品消息
		jmsTemplate.send(topicDestination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(itemId + "");
				return textMessage;
			}
		});
		return E3Result.ok();
	}

	public TbItemDesc getItemDescById(long itemId) {
		// 查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId + ":DESC");
			if (StringUtils.isNoneBlank(json)) {
				TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return tbItemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		// 添加缓存
		try {
			jedisClient.set(ITEM_INFO_PRE + ":" + itemId + ":DESC", JsonUtils.objectToJson(itemDesc));
			// 设置过期时间
			jedisClient.expire(ITEM_INFO_PRE + ":" + itemId + ":DESC", EXIST_TIME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}

}
