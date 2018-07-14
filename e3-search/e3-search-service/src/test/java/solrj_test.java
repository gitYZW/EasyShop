import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrj_test {
	@Test
	public void demo1() throws Exception {
		SolrServer server = new HttpSolrServer("http://192.168.25.129:8080/solr/collection1");
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id","test1");
		document.addField("title", "phone");
		document.addField("price", 500);
		server.add(document);
		server.commit();
	}
	
	@Test
	public void demo2() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/solr/collection1");
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		QueryResponse queryList = solrServer.query(query);
		SolrDocumentList list = queryList.getResults();
		for (SolrDocument solrDocument : list) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));

		}
	}
	
	@Test
	public void demo3() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.129:8080/solr/collection1");
		SolrQuery query = new SolrQuery();
		query.setQuery("手机");
		query.setStart(0);
		query.setRows(20);
		query.set("df","item_title");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		QueryResponse queryList = solrServer.query(query);
		SolrDocumentList list = queryList.getResults();
		for (SolrDocument solrDocument : list) {
			System.out.println(solrDocument.get("id"));
			Map<String, Map<String, List<String>>> highlighting = queryList.getHighlighting();
			List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
			String title="";
			if(list2!=null&&list2.size()>0) {
				title=list2.get(0);
			}else {
				title=(String) solrDocument.get("item_title");
			}
			System.out.println(title);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));

		}
	}
}
