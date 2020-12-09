package com.terraforming.server.model;

import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.constans.Resource;

public class PayWith {
	private Map<Resource, Integer> resources = new HashMap<Resource, Integer>();
	private Map<String, Integer> collections = new HashMap<String, Integer>();

	public PayWith() {
		resources = new HashMap<Resource, Integer>();
		collections = new HashMap<String, Integer>();
	}
	
	public PayWith(Map<Resource, Integer> resources) {
		this.resources = resources;
		collections = new HashMap<String, Integer>();
	}
	
	public PayWith(Map<Resource, Integer> resources, Map<String, Integer> collections) {
		this.resources = resources;
		this.collections = collections;
	}
	
	public Map<Resource, Integer> getResources() {
		return resources;
	}
	public void setResources(Map<Resource, Integer> resources) {
		this.resources = resources;
	}
	public Map<String, Integer> getCollections() {
		return collections;
	}
	public void setCollections(Map<String, Integer> collections) {
		this.collections = collections;
	}
	
	public boolean checkEmpty() {
		return resources.isEmpty() && collections.isEmpty();
	}
}
