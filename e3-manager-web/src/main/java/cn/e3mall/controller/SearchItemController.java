package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.search.service.SearchItemService;

@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchService;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public E3Result importAllItems() {
		E3Result result = searchService.importAllItems();
		return result;
	}
}
