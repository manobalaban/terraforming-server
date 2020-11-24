package com.terraforming.server.model;

import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.constans.Resource;

public class PayOption {
	private boolean possible;
	private int price;
	private Map<Resource, Integer> resourcesWithValue = new HashMap<>();
	private Map<OnCardCollected, Integer> onCardResourcesWithValue = new HashMap<>();
	
	public PayOption(boolean possible) {
		this.possible = possible;
	}
	
	public PayOption(boolean possible, int price) {
		this(possible);
		this.price = price;
	}
	
	public boolean isPossible() {
		return possible;
	}
	
	public void setPossible(boolean possible) {
		this.possible = possible;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public Map<Resource, Integer> getResourcesWithValue() {
		return resourcesWithValue;
	}

	public void putResourcesWithValue(Map<Resource, Integer> resourcesWithValue) {
		this.resourcesWithValue.putAll(resourcesWithValue);
	}

	public Map<OnCardCollected, Integer> getOnCardResourcesWithValue() {
		return onCardResourcesWithValue;
	}

	public void putOnCardResourcesWithValue(Map<OnCardCollected, Integer> onCardResourcesWithValue) {
		this.onCardResourcesWithValue.putAll(onCardResourcesWithValue);
	}
	
}
