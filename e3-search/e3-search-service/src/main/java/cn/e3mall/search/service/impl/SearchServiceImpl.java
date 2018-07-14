package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;

	public SearchResult search(String keyword, int page, int rows) throws Exception {
		//创建solrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(keyword);
		//设置分页条件
		if(page<=0) page=1;
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置默认搜索域
		query.set("df","item_title");
		//开启高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("<em>");
		//调用dao执行查询
		SearchResult searchResult = searchDao.search(query);
		//计算总页数
		long recordCount = searchResult.getRecordCount();
		int totalPage = (int) (recordCount / rows);
		if(recordCount / rows>0)
			totalPage++;
		//添加总页数
		searchResult.setTotalPages(totalPage);
		//返回结果
		
		return searchResult;
	}

}
