package com.terraforming.server.aplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.CardResource;
import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.initialize.CorporationsInitializer;
import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.Corporation;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.PayWith;
import com.terraforming.server.model.Player;

public class CardsHandler {
	private static CardsHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	private ColoniesHandler coloniesHandler = ColoniesHandler.getInstance();
	
	private static List<String> cardDeck = new CopyOnWriteArrayList<String>();
	private static List<String> burnedCards = new CopyOnWriteArrayList<String>();
	private static List<String> corporations = new CopyOnWriteArrayList<String>();
	private static Map<String, Corporation> corporationCards = new HashMap<String, Corporation>();
	
	private CardsHandler() {}
	
	public static CardsHandler getInstance() {
		if(instance == null) {
			cardDeck = TerraformingMarsInitialize.initCards(345, "");
			corporations = TerraformingMarsInitialize.initCards(38, "c");
			corporationCards = CorporationsInitializer.initCorporations();
			instance = new CardsHandler();
		}
		return instance;
	}
	
	public static List<String> getCardsFromDeck(int quantity) {
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
	
	public void burnCard(String cardId) {
		burnedCards.add(cardId);
	}
	
	public void burneCards(List<String> cards) {
		burnedCards.addAll(cards);
	}
	
	public void putCardBack(List<String> cards) {
		cardDeck.addAll(cards);
		Collections.shuffle(cardDeck);
	}
	
	public List<String> drawCorporations(int q) {
		List<String> result = new CopyOnWriteArrayList<String>();
		for(int i = 0; i < q; i++) {
			result.add(corporations.get(0));
			corporations.remove(0);
		}
		return result;
	}
	
	public Corporation getCorporation(String corpId) {
		for(Map.Entry<String, Corporation> corpEntry : corporationCards.entrySet()) {
			if(corpEntry.getKey().equals(corpId)) {
				return corpEntry.getValue();
			}
		}
		return null;
	}
	
	public boolean choseFirstTenCard(Player activePlayer) {
		boolean everyBodyReady = true;
		playersHandler.getPlayer(activePlayer.getName()).setDrawnCards(activePlayer.getCardsToBuy());
		putCardBack(activePlayer.getDrawnCards());
		playersHandler.getPlayer(activePlayer.getName()).setReady(true);
		for(Player player : playersHandler.getPlayers()) {
			if(!player.isReady() && !player.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			playersHandler.getPlayers().get(1).setActive(true);
			for(Player player : playersHandler.getPlayers()) {
				if(!player.getName().equals("admin")) {
					player.setCorporationsToChose(drawCorporations(3));
					player.setReady(false);
				}
			}
		}
		return everyBodyReady;
	}
	
	public void checkCorpPlayedEffect(Player actualPlayer) {
		if(getCorporation(actualPlayer.getCorporation()).getCollected() != null) {
			CardResource cardResource = getCorporation(actualPlayer.getCorporation()).getCollected().getResource();
			coloniesHandler.checkColoniesForActivation(cardResource);
		}
		TerraformingMarsHandler.getTriggeredEffects(EffectType.CARD_PLAY, actualPlayer.getName()).forEach(effect -> 
			EffectSorter.onCorporationsEffect(effect.getId(), getCorporation(actualPlayer.getCorporation()), playersHandler.getPlayer(effect.getOwner())));
	}
	
	public boolean choseCorporation(Player activePlayer) {
		boolean everyBodyReady = true;
		Corporation corp = getCorporation(activePlayer.getCorporation());
		Player player = playersHandler.getPlayer(activePlayer.getName());
		playersHandler.nextPlayer();
		player.setCorporation(corp.getId());
		player.AddTags(corp.getTags());
		player.getResources().putAll(corp.getResources());
		player.addToEffects(corp.getEffect());
		player.addToActions(corp.getAction());
		player.addToCollections(corp.getCollected());
		if(corp.getId().equals("c19")) {
			player.addCardsToHand(CardsHandler.getCardsFromDeck(1));
		}
		if(corp.getId().equals("c29")) {
			player.increaseTr(-2);
		}
		if(corp.getId().equals("c31")) {
			player.increaseTr(-1);
		}
		if(corp.getId().equals("c34")) {
			for(Player p : playersHandler.getPlayers()) {
				if(!p.getName().equals("admin") && !p.getName().equals(player.getName())) {
					p.getResources().put(Resource.CREDIT_PROD, p.getResources().get(Resource.CREDIT_PROD) - 2);
				}
			}
		}
		player.setReady(true);
		for(Player playerReady : playersHandler.getPlayers()) {
			if(!playerReady.isReady() && !playerReady.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			for(Player playerReady : playersHandler.getPlayers()) {
				playerReady.setReady(false);
			}
		}
		return everyBodyReady;
	}
	
	public PayOption checkPayForResearchEffect(Player player) {
		PayOption payOption = new PayOption(true, 3);
		TerraformingMarsHandler.getTriggeredEffects(EffectType.RESEARCH_PRICE, player.getName()).forEach(effect -> EffectSorter.onCardBuyEffect(effect.getId(), payOption));
		int price = player.getCardsToBuy().size() * payOption.getPrice();
		return TerraformingMarsHandler.checkPayIntention(player, price);
	}
	
	public boolean research(Player actualPlayer) {
		boolean everyBodyReady = true;
		playersHandler.nextPlayer();
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setPayWithResources(actualPlayer.getPayingWith());
		player.addCardsToHand(actualPlayer.getCardsToBuy());
		for(String cardId : actualPlayer.getDrawnCards()) {
			if(!player.getCardsInHand().contains(cardId)) {
				burnCard(cardId);
			}
		}
		player.setDrawnCards(new CopyOnWriteArrayList<String>());
		player.setCardsToBuy(new CopyOnWriteArrayList<String>());
		player.setPayingWith(new PayWith());
		player.setReady(true);
		for(Player playerReady : playersHandler.getPlayers()) {
			if(!playerReady.isReady() && !playerReady.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			for(Player playerReady : playersHandler.getPlayers()) {
				playerReady.setReady(false);
			}
		}
		return everyBodyReady;
	}
}
