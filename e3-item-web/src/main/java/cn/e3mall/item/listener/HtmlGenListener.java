package cn.e3mall.item.listener;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenListener implements MessageListener {
	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	
	public void onMessage(Message message) {
		//创建模板对象
		try {
			//从消息中去除商品id
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId= new Long(text);
			//等待事务提交
			Thread.sleep(1000);
			//根据商品id查询商品信息
			TbItem tbItem = itemService.findItemById(itemId);
			Item item = new Item(tbItem);
			//查询商品描述
			TbItemDesc itemDesc = itemService.getItemDescById(itemId);
			//创建一个数据集，封装商品数据
			Map data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			//加载模板对象
			Configuration configuration = freeMarkerConfig.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			//创建一个输出流，指定输出的目录和文件名
			Writer out = new FileWriter(new File(HTML_GEN_PATH+itemId+".html"));
			//生成静态页面
			template.process(data, out);
			//关闭流
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
