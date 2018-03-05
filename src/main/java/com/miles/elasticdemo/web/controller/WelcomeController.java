package com.miles.elasticdemo.web.controller;


import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.miles.elasticdemo.modal.Phone;
import com.miles.elasticdemo.repository.impl.PhoneRepositoryImpl;
import com.miles.elasticdemo.service.PhoneService;

@RestController
public class WelcomeController {

	
	private PhoneRepositoryImpl phoneRepository;
	
	public WelcomeController(PhoneRepositoryImpl phoneRepository) {
		this.phoneRepository = phoneRepository;
	}
	
	
	@Autowired
	private PhoneService phoneService;
	
	@PostMapping("/phone")
	public ResponseEntity save(@RequestBody Phone phone) {
		try {
			return ResponseEntity.ok(phoneService.save(phone));
		}catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	
	@GetMapping("/allPhones/{id}")
	public ResponseEntity findAll(@PathVariable("id") String id) {
		return ResponseEntity.ok(phoneService.getPhoneById(id));
	}
	@GetMapping("/allPhones")
	public ResponseEntity findAll() {
		return ResponseEntity.ok(phoneService.findAll());
	}
	
	@PostMapping("/findAny")
	public ResponseEntity findAny(@RequestBody Map<String,Object> map) {
		
		
		return ResponseEntity.ok(phoneService.findAny(map));


	}
	
}
