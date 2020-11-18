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
	
	public Player pay(Player actualPlayer) {
		Player player = getPlayer(actualPlayer.getName());
		for(Map.Entry<Resource, Integer> actualEntry : actualPlayer.getPayingWith().entrySet()) {
			for(Map.Entry<Resource, Integer> entry : player.getResources().entrySet()) {
				if(actualEntry.getKey() == entry.getKey()) {
					player.getResources().put(entry.getKey(), entry.getValue() - actualEntry.getValue());
				}
			}
		}
		player.setPayingWith(null);
		return player;
	}
	
	public boolean setResources(Map<Resource, Integer> resources, Player actualPlayer) {
		boolean possible = false;
		Player player = 
		for(Map.Entry<Resource, Integer> entry : getPlayer(actualPlayer.getName()).getResources().entrySet()) {
			
		}
		return possible;
	}
	
	private void nextPlayer() {
		Player actualPlayer = null;
		Player nextPlayer = null;
		
		for(int i = 1; i < players.size(); i++) {
			if(players.get(i).isActive() == true) {
				actualPlayer = players.get(i);
				if(i < (players.size() -1) && !players.get(i +1).isReady()) {
					nextPlayer = players.get(i +1);
				} else if(i == players.size() - 1 && !players.get(1).isReady()) {
					nextPlayer = players.get(1);
				}
			}
		}
		if(nextPlayer != null) {
			actualPlayer.setActive(false);
			nextPlayer.setActive(true);
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
			getPlayer(name).setChosed(true);
		}
	}
	
	public boolean choseFirstTenCard(Player activePlayer) {
		boolean everyBodyReady = true;
		getPlayer(activePlayer.getName()).setDrawnCards(activePlayer.getCardsToBuy());
		cardsHandler.putCardBack(activePlayer.getDrawnCards());
		getPlayer(activePlayer.getName()).setReady(true);
		for(Player player : players) {
			if(!player.isReady() && !player.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			players.get(1).setActive(true);
			for(Player player : players) {
				if(!player.getName().equals("admin")) {
					player.setCorporationsToChose(cardsHandler.getCorporations(3));
					player.setReady(false);
				}
			}
		}
		return everyBodyReady;
	}
	
	public boolean choseCorporation(Player activePlayer) {
		boolean everyBodyReady = true;
		Corporation corp = corporationsHandler.getCorporation(activePlayer.getCorporation());
		Player player = getPlayer(activePlayer.getName());
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
		for(Player playerReady : players) {
			if(!playerReady.isReady() && !playerReady.getName().equals("admin")) {
				everyBodyReady = false;
			}
		}
		if(everyBodyReady) {
			for(Player playerReady : players) {
				playerReady.setReady(false);
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
				for(String cardId : actualPlayer.getDrawnCards()) {
					if(!player.getCardsInHand().contains(cardId)) {
						cardsHandler.burnCard(cardId);
					}
				}
				player.setDrawnCards(new CopyOnWriteArrayList<String>());
				player.setCardsToBuy(new CopyOnWriteArrayList<String>());
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
	
	public void doAction(Player actualPlayer) {
		for(Player player : players) {
			if(actualPlayer.getName().equals(player.getName())) {
				if(player.isFirstActionDone()) {
					passAction(player);
				} else {
					player.setFirstActionDone(true);
				}
			}
		}
	}
	
	public void passAction(Player actualPlayer) {
		for(Player player : players) {
			if(actualPlayer.getName().equals(player.getName()) && player.isFirstActionDone() == false) {
				player.setReady(true);
			}
		}
		nextPlayer();
	}
	
}
