package com.terraforming.server.model;

import java.util.List;
import java.util.Map;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;

public class BasicCard {
	private String id;
	private List<Tag> tags;
	private Map<Resource, Integer> resources;
	
	public BasicCard(String id, List<Tag> tags, Map<Resource, Integer> resources) {
		this.id = id;
		this.tags = tags;
		this.resources = resources;
	}
	
	public String getId() {
		return id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public Map<Resource, Integer> getResources() {
		return resources;
	}
	
	public static void main(String[] args) {
		int t = 20;
		int s = -2;
		t += s;
		System.out.println(t);
	}
}
