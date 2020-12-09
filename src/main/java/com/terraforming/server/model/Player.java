package com.terraforming.server.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.aplication.CardsHandler;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;

public class Player {

	private String name;
	private boolean chosed = false;
	private boolean ready = false;
	private int order = 0;
	private boolean active = false;
	private boolean firstActionDone = false;
	
	private int tr = 20;
	private Map<Resource, Integer> resources;
	private PayWith payingWith = new PayWith();
	
	private List<String> drawnCards = new CopyOnWriteArrayList<>();
	private List<String> cardsToBuy = new CopyOnWriteArrayList<>();
	private List<String> cardsInHand = new CopyOnWriteArrayList<>();
	private List<String> playedCards = new CopyOnWriteArrayList<>();
	private String corporation;
	private List<String> corporationsToChose = new CopyOnWriteArrayList<>();
	
	private Map<String, CardAction> actions = new HashMap<String, CardAction>();
	private Map<String, CardEffect> effects = new HashMap<>();
	private Map<String, OnCardCollected> collections = new HashMap<>();
	private List<Tag> tags = new CopyOnWriteArrayList<Tag>();
	
	private int fleet = 1;
	private int availableFleet = 1;
	private boolean usingFreeDelegate = false;
	
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

	public void setPayWithResources(PayWith payWith) {
		for(Map.Entry<Resource, Integer> actualEntry : payWith.getResources().entrySet()) {
			for(Map.Entry<Resource, Integer> entry : this.resources.entrySet()) {
				if(actualEntry.getKey() == entry.getKey() && entry.getKey() != Resource.CARD) {
					this.resources.put(entry.getKey(), entry.getValue() + actualEntry.getValue());
				} else if(actualEntry.getKey() == entry.getKey() && entry.getKey() == Resource.CARD) {
					cardsInHand.addAll(CardsHandler.getCardsFromDeck(actualEntry.getValue()));
				}
			}
		}
		for(Map.Entry<String, Integer> actualEntry : payWith.getCollections().entrySet()) {
			for(Map.Entry<String, OnCardCollected> entry : this.collections.entrySet()) {
				if(actualEntry.getKey().equals(entry.getKey())) {
					this.collections.put(actualEntry.getKey(), this.collections.get(entry.getKey()).addToQuantity(actualEntry.getValue()));
				}
			}
		}
	}
	
	public boolean checkPayWithIntention(PayWith payWith) {
		boolean possible = true;
		for(Map.Entry<Resource, Integer> entry : this.resources.entrySet()) {
			for(Map.Entry<Resource, Integer> newEntry : payWith.getResources().entrySet()) {
				if(entry.getKey() == newEntry.getKey()) {
					if(entry.getKey() == Resource.CREDIT_PROD && entry.getValue() - newEntry.getValue() < -5) {
						possible = false;
					} else if(entry.getKey() != Resource.CREDIT_PROD && entry.getValue() - newEntry.getValue() < 0) {
						possible = false;
					}
				}
			}
		}
		for(Map.Entry<String, OnCardCollected> entry : this.collections.entrySet()) {
			for(Map.Entry<String, Integer> newEntry : payWith.getCollections().entrySet()) {
				if(entry.getKey().equals(newEntry.getKey())) {
					if(entry.getValue().getQuantity() - newEntry.getValue() < 0) {
						possible = false;
					}
				}
			}
		}
		return possible;
	}

	public PayWith getPayingWith() {
		Map<Resource, Integer> resources = new HashMap<Resource, Integer>();
		for(Map.Entry<Resource, Integer> entry : payingWith.getResources().entrySet()) {
			resources.put(entry.getKey(), entry.getValue() * -1);
		}
		Map<String, Integer> collections = new HashMap<String, Integer>();
		for(Map.Entry<String, Integer> entry : payingWith.getCollections().entrySet()) {
			collections.put(entry.getKey(), entry.getValue() * -1);
		}
		return new PayWith(resources, collections);
	}

	public void setPayingWith(PayWith payingWith) {
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
	
	public String pullCardFromHand(String card) {
		int cardIndex = 0;
		for(int i = 0; i < cardsInHand.size(); i++) {
			if(cardsInHand.get(i).equals(card)) {
				cardIndex = i;
			}
		}
		cardsInHand.remove(cardIndex);
		return null;
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

	public Map<String, CardAction> getActions() {
		return actions;
	}

	public Map<String, CardEffect> getEffects() {
		return effects;
	}

	public Map<String, OnCardCollected> getCollections() {
		return collections;
	}
	
	public void addToActions(CardAction action) {
		if(action != null) {
			action.setOwner(name);
			actions.put(action.getId(), action);
		}
	}
	
	public void addToEffects(CardEffect effect) {
		if(effect != null) {
			effect.setOwner(name);
			effects.put(effect.getId(), effect);
		}
	}
	
	public void addToCollections(OnCardCollected collection) {
		if(collection != null) {
			collection.setOwner(name);
			collections.put(collection.getId(), collection);
		}
	}

	public List<Tag> getTags() {
		return tags;
	}
	
	public void AddTags(List<Tag> tags) {
		this.tags.addAll(tags);
	}

	public boolean isFirstActionDone() {
		return firstActionDone;
	}

	public void setFirstActionDone(boolean firstActionDone) {
		this.firstActionDone = firstActionDone;
	}

	public int getFleet() {
		return fleet;
	}

	public void setFleet(int fleet) {
		this.fleet = fleet;
	}

	public int getAvailableFleet() {
		return availableFleet;
	}

	public void setAvailableFleet(int availableFleet) {
		this.availableFleet = availableFleet;
	}

	public boolean isUsingFreeDelegate() {
		return usingFreeDelegate;
	}

	public void setUsingFreeDelegate(boolean usingFreeDelegate) {
		this.usingFreeDelegate = usingFreeDelegate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTr(int tr) {
		this.tr = tr;
	}

	public void setActions(Map<String, CardAction> actions) {
		this.actions = actions;
	}

	public void setEffects(Map<String, CardEffect> effects) {
		this.effects = effects;
	}

	public void setCollections(Map<String, OnCardCollected> collections) {
		this.collections = collections;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
	
}