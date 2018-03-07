package com.qingniao.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class SolrTest {
	//插入数据
	@Test
	public void demo1() throws SolrServerException, IOException {
		//创建HttpSolrServer对象
		HttpSolrServer solrServer = new HttpSolrServer("http://192.168.11.129:8080/solr");
		//创建SolrInputDocument对象，封装数据
		SolrInputDocument inputDocument = new SolrInputDocument();
		inputDocument.setField("id", 3);
		inputDocument.setField("name", "王五");
		solrServer.add(inputDocument);
		solrServer.commit();
	}
	//查询
	@Test
	public void demo2() throws SolrServerException {
		//创建HttpSolrServer对象
		HttpSolrServer solrServer = new HttpSolrServer("http://192.168.11.129:8080/solr");
		//创建SolrQuery对象，封装数据
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "id:111");
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList docs = queryResponse.getResults();
		for (SolrDocument doc : docs) {
			String id = (String)doc.get("id");
			String name = (String)doc.get("name");
			System.out.println(id+"  "+name);
		}
	}
	@Autowired
	private SolrServer solrServer;
	//删除
	@Test
	public void demo3() throws SolrServerException, IOException {
		solrServer.deleteById("111");
		solrServer.commit();
	}
	//修改
	@Test
	public void demo4() throws SolrServerException, IOException {
		SolrInputDocument inputDocument = new SolrInputDocument();
		inputDocument.setField("id", "111");
		inputDocument.setField("name", "老大");
		solrServer.add(inputDocument);
		solrServer.commit();
	}
}
