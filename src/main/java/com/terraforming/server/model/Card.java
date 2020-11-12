package com.terraforming.server.model;

import java.util.List;
import java.util.Map;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;

public class Card extends Corporation {
	
	private int price;

	public Card(String id, List<Tag> tags, Map<Resource, Integer> resources, CardEffect effect, CardAction action, OnCardCollected collected, int price) {
		super(id, tags, resources, effect, action, collected);
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}
