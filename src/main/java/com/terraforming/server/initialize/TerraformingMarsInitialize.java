package com.terraforming.server.initialize;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.model.Player;

public class TerraformingMarsInitialize {
	
	public static List<Player> initPlayers() {
		List<Player> players = new CopyOnWriteArrayList<Player>();
		List<String> names = new CopyOnWriteArrayList<>(
				Arrays.asList("admin", "Bala", "Ezer", "Gida", "Lörcs", "Marci", "Manó", "Preszi", "Vendég"));
		for (String name : names) {
			Player player = new Player(name);
			players.add(player);
		}
		return players;
	}
	
	public static List<String> initCards(int n, String s) {
		List<String> cards = new CopyOnWriteArrayList<>();
		for (int i = 1; i < (n + 1); i++) {
			cards.add(s + i);
		}
		Collections.shuffle(cards);
		return cards;
	}
}
