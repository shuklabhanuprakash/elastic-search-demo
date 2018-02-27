package com.miles.elasticdemo.service.impl;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miles.elasticdemo.modal.Phone;
import com.miles.elasticdemo.repository.impl.PhoneRepositoryImpl;
import com.miles.elasticdemo.service.PhoneService;

@Service
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneRepositoryImpl phoneRepository;
	
	@Override
	public Phone save(Phone phone) {
		return phoneRepository.save(phone);
	}

	@Override
	public Map<String, Object> getPhoneById(String id) {
		return phoneRepository.getPhoneById(id);
	}

	@Override
	public SearchResponse findAll() {
		return phoneRepository.findAll();
	}

	

}
