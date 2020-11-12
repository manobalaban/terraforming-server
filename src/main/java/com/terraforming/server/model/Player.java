package com.terraforming.server.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;

public class Player {

	private String name;
	private boolean chosed = false;
	private boolean ready = false;
	private int order = 0;
	private boolean active = false;
	
	private int tr = 20;
	private Map<Resource, Integer> resources;
	private Map<Resource, Integer> payingWith = new HashMap<Resource, Integer>();
	
	private List<String> drawnCards = new CopyOnWriteArrayList<>();
	private List<String> cardsToBuy = new CopyOnWriteArrayList<>();
	private List<String> cardsInHand = new CopyOnWriteArrayList<>();
	private List<String> playedCards = new CopyOnWriteArrayList<>();
	private String corporation;
	private List<String> corporationsToChose = new CopyOnWriteArrayList<>();
	private List<String> preludes = new CopyOnWriteArrayList<>();
	private List<String> preludesToChose = new CopyOnWriteArrayList<>();
	
	private List<CardAction> actions = new CopyOnWriteArrayList<CardAction>(); 
	private List<CardEffect> effects = new CopyOnWriteArrayList<CardEffect>(); 
	private List<OnCardCollected> collections = new CopyOnWriteArrayList<OnCardCollected>(); 
	private List<Tag> tags = new CopyOnWriteArrayList<Tag>();
	
	public Player() {
		resources = new HashMap<>(Map.of(Resource.CREDIT, 0, Resource.CREDIT_PROD, 0, Resource.STEEL, 0, Resource.STEEL_PROD, 0, Resource.TITAN, 0, Resource.TITAN_PROD, 0, Resource.PLANT, 0, Resource.PLANT_PROD, 0, Resource.ENERGY, 0, Resource.ENERGY_PROD, 0));
		resources.put(Resource.HEAT, 0);
		resources.put(Resource.HEAT_PROD, 0);
	};

	public Player(String name) {
		this();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isChosed() {
		return chosed;
	}

	public void setChosed(boolean chosed) {
		this.chosed = chosed;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getTr() {
		return tr;
	}

	public void increaseTr(int tr) {
		this.tr += tr;
	}

	public Map<Resource, Integer> getResources() {
		return resources;
	}

	public void setResources(Map<Resource, Integer> resources) {
		this.resources = resources;
	}

	public Map<Resource, Integer> getPayingWith() {
		return payingWith;
	}

	public void setPayingWith(Map<Resource, Integer> payingWith) {
		this.payingWith = payingWith;
	}

	public List<String> getDrawnCards() {
		return drawnCards;
	}

	public void setDrawnCards(List<String> drawnCards) {
		this.drawnCards = drawnCards;
	}

	public List<String> getCardsToBuy() {
		return cardsToBuy;
	}

	public void setCardsToBuy(List<String> cardsToBuy) {
		this.cardsToBuy = cardsToBuy;
	}

	public List<String> getCardsInHand() {
		return cardsInHand;
	}

	public void setCardsInHand(List<String> cardsInHand) {
		this.cardsInHand = cardsInHand;
	}
	
	public void addCardsToHand(List<String> cards) {
		this.cardsInHand.addAll(cards);
	}

	public List<String> getPlayedCards() {
		return playedCards;
	}

	public void setPlayedCards(List<String> playedCards) {
		this.playedCards = playedCards;
	}

	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public List<String> getPreludes() {
		return preludes;
	}

	public void setPreludes(List<String> preludes) {
		this.preludes = preludes;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public List<String> getCorporationsToChose() {
		return corporationsToChose;
	}

	public void setCorporationsToChose(List<String> corporationsToChose) {
		this.corporationsToChose = corporationsToChose;
	}

	public List<String> getPreludesToChose() {
		return preludesToChose;
	}

	public void setPreludesToChose(List<String> preludesToChose) {
		this.preludesToChose = preludesToChose;
	}

	public List<CardAction> getActions() {
		return actions;
	}

	public List<CardEffect> getEffects() {
		return effects;
	}

	public List<OnCardCollected> getCollections() {
		return collections;
	}
	
	public void addToActions(CardAction action) {
		actions.add(action);
	}
	
	public void addToEffects(CardEffect effect) {
		effects.add(effect);
	}
	
	public void addToCollections(OnCardCollected collection) {
		collections.add(collection);
	}

	public List<Tag> getTags() {
		return tags;
	}
	
	public void AddTags(List<Tag> tags) {
		this.tags.addAll(tags);
	}
}