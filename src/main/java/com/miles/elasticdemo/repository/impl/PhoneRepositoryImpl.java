package com.miles.elasticdemo.repository.impl;

import static org.assertj.core.api.Assertions.entry;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miles.elasticdemo.modal.Phone;

@Repository
public class PhoneRepositoryImpl  {

	  private final String INDEX = "ecommerce";
	  private final String TYPE = "product";  
	  private RestHighLevelClient restHighLevelClient;
	  private ObjectMapper objectMapper;
	  private RestTemplate restTemplate;
	
	  @Value("${elasticsearch.host}")
	  private String host;
	    
	  @Value("${elasticsearch.port}")
      private Integer port;
	  
	  
	  public PhoneRepositoryImpl( RestHighLevelClient restHighLevelClient) {
	    this.objectMapper = new ObjectMapper();
	    this.restHighLevelClient = restHighLevelClient;
	    this.restTemplate = new RestTemplate();
	  }
	  
	  public Phone save(Phone phone) {
		  
		 
		  Map dataMap = objectMapper.convertValue(phone, Map.class);
		  IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, phone.getId())
	                .source(dataMap);
		  try {
		    IndexResponse response = restHighLevelClient.index(indexRequest);
		  } catch(ElasticsearchException e) {
		    e.getDetailedMessage();
		  } catch (java.io.IOException ex){
		    ex.getLocalizedMessage();
		  }
		  return phone;
		  
	  }
	  
	  public Map<String, Object> getPhoneById(String id){
		  GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
		  GetResponse getResponse = null;
		  try {
		    getResponse = restHighLevelClient.get(getRequest);
		  } catch (java.io.IOException e){
		    e.getLocalizedMessage();
		  }
		  Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
		  return sourceAsMap;
		}
	
	  public SearchResponse findAll(){
		  SearchResponse searchResponse = null;
		  SearchRequest searchRequest = new SearchRequest(); 
		  SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		  searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		  searchRequest.source(searchSourceBuilder);
		  try {
			  searchResponse = restHighLevelClient.search(searchRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return searchResponse;
	  }
	  
	  public SearchResponse findByParameter(Map<String,Object> map){
		  String key = null;
		  Object value =null;
		  
		  for(Map.Entry<String, Object> entry: map.entrySet()) {
			  key = entry.getKey();
			  value=entry.getValue();
			  
		  }
		  
		  SearchResponse searchResponse = null;
		  SearchRequest searchRequest = new SearchRequest(); 
		  SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		  searchSourceBuilder.query(QueryBuilders.matchQuery(key,value));
		  searchRequest.source(searchSourceBuilder);
		  try {
			  searchResponse = restHighLevelClient.search(searchRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return searchResponse;
	  }

	public String findAny(String param) {
		URI url;//"http://localhost:9200/ecommerce/product/_search?q="+param
		String serviceUrl = "http://"+host+":"+port;
		try {
			url = new URI(serviceUrl + "/ecommerce/product/_search?q="+param);
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET,null,String.class);
		return exchange.getBody();
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	  
	  
	  
	 
}
