package com.terraforming.server.model;

import java.util.Map;

import com.terraforming.server.constans.Resource;

public class PayOption {
	private boolean possible;
	private int price;
	private Map<Resource, Integer> resourcesWithValue;	
	
	public PayOption(boolean possible, int price,  Map<Resource, Integer> resourcesWithValue) {
		this.possible = possible;
		this.price = price;
		this.resourcesWithValue = resourcesWithValue;
	}

	public boolean isPossible() {
		return possible;
	}
	
	public int getPrice() {
		return price;
	}

	public  Map<Resource, Integer> getResources() {
		return resourcesWithValue;
	}
}
