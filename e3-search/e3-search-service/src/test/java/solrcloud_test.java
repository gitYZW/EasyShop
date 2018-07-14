import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class solrcloud_test {

	@Test
	public  void  demo1() throws Exception{
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.129:2182,192.168.25.129:2183,192.168.25.129:2184");
		solrServer.setDefaultCollection("collection2");
		SolrInputDocument document = new SolrInputDocument();
		document.setField("id","测试1");
		document.setField("item_title", "测试1");
		document.setField("item_price", 500);
		solrServer.add(document);
		solrServer.commit();
	}
	
	@Test
	public void demo2() throws Exception{
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.129:2182,192.168.25.129:2183,192.168.25.129:2184");
		solrServer.setDefaultCollection("collection2");
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		QueryResponse queryResponse = solrServer.query(query);
		SolrDocumentList results = queryResponse.getResults();
		System.out.println("总记录数："+results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			
		}
		

	}
}
