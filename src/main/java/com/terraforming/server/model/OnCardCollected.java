package com.terraforming.server.model;

import com.terraforming.server.constans.CardResource;

public class OnCardCollected {
	
	private String id;
	private int quantity;
	private CardResource resource;
	
	public OnCardCollected() {}
	
	public OnCardCollected(String id, int quantity, CardResource resource) {
		this.id = id;
		this.quantity = quantity;
		this.resource = resource;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public CardResource getResource() {
		return resource;
	}
	
}
