package com.terraforming.server.aplication;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.initialize.TerraformingMarsInitialize;

public class CardsHandler {
	private static CardsHandler instance = null;
	
	private static List<String> cardDeck = new CopyOnWriteArrayList<String>();
	private static List<String> burnedCards = new CopyOnWriteArrayList<String>();
	private static List<String> corporations = new CopyOnWriteArrayList<String>();
	private static List<String> preludes = new CopyOnWriteArrayList<String>();
	
	private CardsHandler() {}
	
	public static CardsHandler getInstance() {
		if(instance == null) {
			cardDeck = TerraformingMarsInitialize.initCards(345, "");
			corporations = TerraformingMarsInitialize.initCards(38, "c");
			preludes = TerraformingMarsInitialize.initCards(35, "p");
			instance = new CardsHandler();
		}
		return instance;
	}
	
	public List<String> getCardsFromDeck(int quantity) {
		List<String> result = new CopyOnWriteArrayList<String>();
		if(cardDeck.size() < quantity) {
			cardDeck.addAll(burnedCards);
			Collections.shuffle(cardDeck);
		}
		int i = 1;
		while(i <= quantity && !cardDeck.isEmpty()) {
			result.add(cardDeck.get(0));
			cardDeck.remove(0);
			i++;
		}
		return result;
	}
	
	public void burneCards(List<String> cards) {
		burnedCards.addAll(cards);
	}
	
	public void putCardBack(List<String> cards) {
		cardDeck.addAll(cards);
		Collections.shuffle(cardDeck);
	}
	
	public List<String> getCorporations(int q) {
		List<String> result = new CopyOnWriteArrayList<String>();
		for(int i = 0; i < q; i++) {
			result.add(corporations.get(0));
			corporations.remove(0);
		}
		return result;
	}
	
	public List<String> getPreludes(int q) {
		List<String> result = new CopyOnWriteArrayList<String>();
		for(int i = 0; i < q; i++) {
			result.add(preludes.get(0));
			preludes.remove(0);
		}
		return result;
	}
}
