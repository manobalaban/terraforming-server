package com.terraforming.server.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.EffectType;

public class CardEffect {
	
	private String id;
	private boolean triggeredByEveryne = false;
	private List<EffectType> types = new CopyOnWriteArrayList<EffectType>();
	
	public CardEffect() {}
	
	public CardEffect(String id, List<EffectType> types) {
		this.id = id;
		this.types = types;
	}
	
	public CardEffect(String id, List<EffectType> types, boolean triggeredByEveryne) {
		this(id, types);
		this.triggeredByEveryne = triggeredByEveryne;
	}

	public String getId() {
		return id;
	}

	public List<EffectType> getTypes() {
		return types;
	}

	public boolean isTriggeredByEveryne() {
		return triggeredByEveryne;
	}
}
