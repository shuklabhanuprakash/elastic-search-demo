package com.miles.elasticdemo.service;

import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.action.search.SearchResponse;

import com.miles.elasticdemo.modal.Phone;

public interface PhoneService {

	Phone save(Phone phone);

	Map<String,Object> getPhoneById(String id);

	SearchResponse findAll();

	String findAny(String map);
	
	SearchResponse findByParameter(Map<String,Object> map);
	
}
