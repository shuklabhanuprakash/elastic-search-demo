package com.miles.elasticdemo.repository.impl;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

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

	  public PhoneRepositoryImpl( RestHighLevelClient restHighLevelClient) {
	    this.objectMapper = new ObjectMapper();
	    this.restHighLevelClient = restHighLevelClient;
	    restTemplate= new RestTemplate();
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
	  
	 
}
