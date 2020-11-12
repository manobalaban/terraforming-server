package com.terraforming.server.model;

import java.util.Map;

import com.terraforming.server.constans.Resource;

public class PayOption {
	private boolean possible;
	private int sum;
	private Map<Resource, Integer> resourcesWithValue;	
	
	public PayOption(boolean possible, int sum,  Map<Resource, Integer> resourcesWithValue) {
		this.possible = possible;
		this.sum = sum;
		this.resourcesWithValue = resourcesWithValue;
	}

	public boolean isPossible() {
		return possible;
	}
	
	public int getSum() {
		return sum;
	}

	public  Map<Resource, Integer> getResources() {
		return resourcesWithValue;
	}
}
