package cn.e3mall.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbItemMapper itemMapper;
	
	public E3Result addCart(long userId, long itemId, Integer num) {
		//向redis中添加购物车
		//数据类型为hash key：userId field：itemId value：商品信息
		//判断商品是否存在
		Boolean hexists = jedisClient.hexists("CART:"+userId,itemId+"");
		//如果存在，数量相加
		if(hexists) {
			String json = jedisClient.hget("CART:"+userId,itemId+"");
			//把json转换为Tbitem
			TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
			item.setNum(item.getNum()+num);
			//写回redis
			jedisClient.hset("CART:"+userId,itemId+"",JsonUtils.objectToJson(item));
			return E3Result.ok();
		}
		//如果不存在，根据商品id查询商品
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		//设置商品数量
		item.setNum(num);
		//取一张图片
		String images = item.getImage();
		if(StringUtils.isNoneBlank(images)) {
			item.setImage(images.split(",")[0]);
		}
		//添加到购物车列表
		jedisClient.hset("CART:"+userId,itemId+"",JsonUtils.objectToJson(item));
		return E3Result.ok();
	}

	//将cookie中的商品列表添加到redis中
	public E3Result mergeCart(long userId, List<TbItem> itemList) {
		for (TbItem tbItem : itemList) {
			addCart(userId, tbItem.getId(), tbItem.getNum());
		}
		return E3Result.ok();
	}

	//登录状态获取购物车列表
	public List<TbItem> getCartList(long userId) {
		//根据用户id查询购物车列表
		List<String> jsonList = jedisClient.hvals("CART:"+userId);
		List<TbItem> itemList = new ArrayList<>();
		for (String string : jsonList) {
			//创建一个Tbitem对象
			TbItem item = JsonUtils.jsonToPojo(string,TbItem.class);
			//添加到列表
			itemList.add(item);
		}
		
		return itemList;
	}

	public E3Result updateCart(long userId, long itemId, int num) {
		//从redis中取商品信息
		String json = jedisClient.hget("CART:"+userId,itemId+"");
		TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
		tbItem.setNum(num);
		//写入redis中
		jedisClient.hset("CART:"+userId,itemId+"", JsonUtils.objectToJson(tbItem));
		return E3Result.ok();
	}

	public E3Result deleteCart(long userId, long itemId) {
		// 删除购物车中的商品
		jedisClient.hdel("CART:"+userId,itemId+"");
		return E3Result.ok();
	}

	@Override
	public E3Result clearCart(long userId) {
		// 清空购物车
		jedisClient.del("CART:"+userId);
		return E3Result.ok();
	}

}
