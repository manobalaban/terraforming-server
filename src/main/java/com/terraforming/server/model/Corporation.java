package com.terraforming.server.model;

import java.util.List;
import java.util.Map;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;

public class Corporation extends BasicCard {
	private CardEffect effect;
	private CardAction action;
	private OnCardCollected collected;

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, CardEffect effect) {
		super(id, tags, resources);
		this.effect = effect;
	}

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, CardAction action) {
		super(id, tags, resources);
		this.action = action;
	}

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, CardEffect effect,
			CardAction action) {
		super(id, tags, resources);
		this.effect = effect;
		this.action = action;
	}

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, OnCardCollected collected) {
		super(id, tags, resources);
		this.collected = collected;
	}

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, CardEffect effect,
			OnCardCollected collected) {
		this(id, tags, resources, effect);
		this.collected = collected;
	}

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, CardAction action,
			OnCardCollected collected) {
		this(id, tags, resources, action);
		this.collected = collected;
	}

	public Corporation(String id, List<Tag> tags, Map<Resource, Integer> resources, CardEffect effect,
			CardAction action, OnCardCollected collected) {
		this(id, tags, resources, effect, action);
		this.collected = collected;
	}

	public CardEffect getEffect() {
		return effect;
	}

	public CardAction getAction() {
		return action;
	}

	public OnCardCollected getCollected() {
		return collected;
	}
}
