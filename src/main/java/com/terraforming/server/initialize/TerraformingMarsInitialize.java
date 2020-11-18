package com.terraforming.server.initialize;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public static Map<String, Player> initMilestones() {
		Map<String, Player> milestones = new HashMap<String, Player>();
		List<String> names = new CopyOnWriteArrayList<>(Arrays.asList(
				"builder", "diversifier", "ecologist", "energizer", "gardener",
				"generalist", "hoverlord", "legend", "mayor", "planner",
				"polarexplorer", "rimsettel", "specialist", "tactican", "terraformer", "tycoon"));
		Collections.shuffle(names);
		for(int i = 0; i < 10; i++) {
			milestones.put(names.get(i), null);
		}
		return milestones;
	}
	
	public static Map<String, Boolean> initAwards() {
		Map<String, Boolean> awards = new HashMap<String, Boolean>();
		List<String> names = new CopyOnWriteArrayList<>(Arrays.asList(
				"banker", "benfactor", "celebrity", "contractor", "culivator",
				"desertsettler", "eccentic", "industrialist", "landlord", "magnate",
				"miner", "realestate", "scientist", "spacebaron", "thermalist", "venuphile"));
		Collections.shuffle(names);
		for(int i = 0; i < 10; i++) {
			awards.put(names.get(i), false);
		}
		return awards;
	}
}
