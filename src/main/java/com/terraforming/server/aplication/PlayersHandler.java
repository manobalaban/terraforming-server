package com.terraforming.server.aplication;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.initialize.CorporationsHandler;
import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.Corporation;
import com.terraforming.server.model.Player;

public class PlayersHandler {
	private static PlayersHandler instance = null;
	
	private CardsHandler cardsHandler = CardsHandler.getInstance();
	private CorporationsHandler corporationsHandler = CorporationsHandler.getInstance();
	
	private static List<Player> players = new CopyOnWriteArrayList<Player>();
	
	private PlayersHandler() {}
	
	public static PlayersHandler getInstance() {
		if(instance == null) {
			players = TerraformingMarsInitialize.initPlayers();
			instance = new PlayersHandler();
		}
		return instance;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(String name) {
		Player result = null;
		for(Player player : players) {
			if(player.getName().equals(name)) {
				return player;
			}
		}
		return result;
	}
	
	private void nextPlayer() {
		for(int i = 1; i < players.size(); i++) {
			if(players.get(i).isActive() == true) {
				players.get(i).setActive(false);
				if(i < (players.size() -1)) {
					players.get(i +1).setActive(true);
				} else {
					players.get(1).setActive(true);
				}
			}
		}
	}
	
	public void chosePlayer(String name) {
		if(name.equals("admin")) {
			List<Player> removePLayerList = new CopyOnWriteArrayList<>();
			for(Player player : players) {
				if(player.isChosed() == false && !player.getName().equals("admin")) {
					removePLayerList.add(player);
				}
			}
			players.removeAll(removePLayerList);
			Collections.shuffle(players);
			int order = 1;
			for(Player player : players) {
				if(!player.getName().equals("admin")) {
					player.setOrder(order);
					player.setDrawnCards(cardsHandler.getCardsFromDeck(20));
					order++;
				}
			}
			Collections.sort(players, (a, b) -> a.getOrder() - b.getOrder());
		} else {
			for(Player player : players) {
				if(player.getName().equals(name)) {
					player.setChosed(true);
				}
			}
		}
	}
	
	public boolean choseFirstTenCard(Player activePlayer) {
		boolean everyBodyReady = true;
		for(Player player : players) {
			if(player.getName().equals(activePlayer.getName())) {
				player.setDrawnCards(activePlayer.getCardsToBuy());
				cardsHandler.putCardBack(activePlayer.getDrawnCards());
				player.setReady(true);
			}
			if(!player.isReady() && !player.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			players.get(1).setActive(true);
			for(Player player : players) {
				if(!player.getName().equals("admin")) {
					player.setCorporationsToChose(cardsHandler.getCorporations(3));
					player.setPreludesToChose(cardsHandler.getPreludes(4));
					player.setReady(false);
				}
			}
		}
		return everyBodyReady;
	}
	
	public boolean choseCorporation(Player activePlayer) {
		boolean everyBodyReady = true;
		Corporation corp = corporationsHandler.getCorporation(activePlayer.getCorporation());
		for(Player player : players) {
			if(player.getName().equals(activePlayer.getName())) {
				nextPlayer();
				player.setCorporation(activePlayer.getCorporation());
				player.AddTags(corp.getTags());
				player.getResources().putAll(corp.getResources());
				player.addToEffects(corp.getEffect());
				player.addToActions(corp.getAction());
				player.addToCollections(corp.getCollected());
				if(corp.getId().equals("c19")) {
					player.addCardsToHand(cardsHandler.getCardsFromDeck(1));
				}
				if(corp.getId().equals("c29")) {
					player.increaseTr(-2);
				}
				if(corp.getId().equals("c31")) {
					player.increaseTr(-1);
				}
				if(corp.getId().equals("c34")) {
					for(Player p : players) {
						if(!p.getName().equals("admin") && !p.getName().equals(player.getName())) {
							p.getResources().put(Resource.CREDIT_PROD, p.getResources().get(Resource.CREDIT_PROD) - 2);
						}
					}
				}
				player.setReady(true);
			}
			if(!player.isReady() && !player.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			for(Player player : players) {
				player.setReady(false);
			}
		}
		return everyBodyReady;
	}
	
	public boolean research(Player actualPlayer) {
		boolean everyBodyReady = true;
		for(Player player : players) {
			if(player.getName().equals(actualPlayer.getName())) {
				nextPlayer();
				for(Map.Entry<Resource, Integer> actualEntry : actualPlayer.getPayingWith().entrySet()) {
					for(Map.Entry<Resource, Integer> entry : player.getResources().entrySet()) {
						if(actualEntry.getKey() == entry.getKey()) {
							player.getResources().put(entry.getKey(), entry.getValue() - actualEntry.getValue());
						}
					}
				}
				player.addCardsToHand(actualPlayer.getCardsToBuy());
				player.setPayingWith(null);
				player.setReady(true);
			}
			if(!player.isReady() && !player.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			for(Player player : players) {
				player.setReady(false);
			}
		}
		return everyBodyReady;
	}
	
}
