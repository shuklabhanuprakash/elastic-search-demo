package com.miles.elasticdemo.modal;

import java.util.List;
import java.util.Map;


public class Phone {

	private String id;
	private String name;
	private String description;
	private Long price;
	private String status;
	private Long quantity;
	private List<Map<String,String>> categories;
	
	private List<String> tags;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public List<Map<String, String>> getCategories() {
		return categories;
	}
	public void setCategories(List<Map<String, String>> categories) {
		this.categories = categories;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
