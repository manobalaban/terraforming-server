package com.terraforming.server.aplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.EffectType;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.Party;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.PayWith;
import com.terraforming.server.model.Player;

public class TurmoilHandler {
	private static TurmoilHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	
	private static Map<String, Party> parties = new HashMap<String, Party>();
	private static List<String> freeDelegates = new CopyOnWriteArrayList<String>();
	private static Map<String, Integer> delegates = new HashMap<String, Integer>();
	private static String dominantParty = "";
	
	
	private TurmoilHandler() {}
	
	public static TurmoilHandler getInstance() {
		if(instance == null) {
			parties = TerraformingMarsInitialize.initTurmoil();
			instance = new TurmoilHandler();
		}
		return instance;
	}

	public Map<String, Party> getParties() {
		return parties;
	}
	
	public List<String> getFreeDelegates() {
		return freeDelegates;
	}

	public Map<String, Integer> getDelegates() {
		return delegates;
	}
	
	public String getDominantParty() {
		return dominantParty;
	}

	public static void init(List<Player> players) {
		for(Player player : players) {
			freeDelegates.add(player.getName());
			delegates.put(player.getName(), 6);
		}
	}

	public List<PayOption> checkBuyDelegateEffect(Player actualPlayer) {
		List<PayOption> result = new CopyOnWriteArrayList<PayOption>();
		if(freeDelegates.contains(actualPlayer.getName())) {
			result.add(new PayOption(true, 0));
		}
		int price = 5;
		TerraformingMarsHandler.getTriggeredEffects(EffectType.BUY_DELEGATE, actualPlayer.getName()).forEach(effect -> EffectSorter.onBuyDelegate(effect.getId(), price));
		if(delegates.get(actualPlayer.getName()) > 0) {
			result.add(TerraformingMarsHandler.checkPayIntention(actualPlayer, price));
		} else {
			result.add(new PayOption(false));
		}
		return result;
	}
	
	public void buyDelegate(String partyName, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setPayWithResources(actualPlayer.getPayingWith());
		player.setPayingWith(new PayWith());
		Party party = parties.get(partyName);
		party.addDelegate(player);
		if(party.getNumberOfDelegates() > parties.get(dominantParty).getNumberOfDelegates()) {
			dominantParty = party.getName();
		}
		if(actualPlayer.isUsingFreeDelegate()) {
			int index = 0;
			for(int i = 0; i < freeDelegates.size(); i++) {
				if(freeDelegates.get(i).equals(player.getName())) {
					index = i;
				}
			}
			freeDelegates.remove(index);
		} else {
			for(Map.Entry<String, Integer> delegate : delegates.entrySet()) {
				if(player.getName().equals(delegate.getKey())) {
					delegates.put(delegate.getKey(), delegate.getValue() - 1);
				}
			}
		}
	}
}
