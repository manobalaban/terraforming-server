package com.terraforming.server.aplication;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.Player;

public class PlayersHandler {
	private static PlayersHandler instance = null;
	
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
	
	public void nextPlayer() {
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
					player.setDrawnCards(CardsHandler.getCardsFromDeck(20));
					order++;
				}
			}
			Collections.sort(players, (a, b) -> a.getOrder() - b.getOrder());
			ColoniesHandler.init(players.size());
			TurmoilHandler.init(players);
		} else {
			getPlayer(name).setChosed(true);
		}
	}
}
