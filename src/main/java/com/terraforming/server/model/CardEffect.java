package com.terraforming.server.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.EffectType;

public class CardEffect {
	
	private String id;
	private List<EffectType> types = new CopyOnWriteArrayList<EffectType>();
	
	public CardEffect() {}
	
	public CardEffect(String id, List<EffectType> types) {
		this.id = id;
		this.types = types;
	}

	public String getId() {
		return id;
	}

	public List<EffectType> getTypes() {
		return types;
	}
}
