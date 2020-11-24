package com.terraforming.server.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Party {
	
	private String name;
	private Map<String, Integer> delegates = new HashMap<String, Integer>();
	private String partyLeader;
	private boolean active;
	private int partyBonus;
	private int partyEffect;
	
	public Party(String name) {
		this.name = name;
	}

	public Map<String, Integer> getDelegates() {
		return delegates;
	}

	public void clearDelegates() {
		this.delegates = new HashMap<String, Integer>();
	}
	
	public void addDelegate(Player player) {
		if(delegates.containsKey(player.getName())) {
			delegates.put(player.getName(), delegates.get(player.getName()) + 1);
		} else {
			delegates.put(player.getName(), 1);
		}
		Map.Entry<String, Integer> newPartyLeader = null;
		for(Map.Entry<String, Integer> entry : delegates.entrySet()) {
			if(newPartyLeader == null || entry.getValue() > newPartyLeader.getValue()) {
				newPartyLeader = entry;
			}
		}
		if(!partyLeader.equals(newPartyLeader.getKey())) {
			partyLeader = newPartyLeader.getKey();
			if(partyLeader.equals("admin")) {
				partyBonus = new Random().nextInt(2) + 1;
				if(!active) {
					partyEffect = new Random().nextInt(4) + 1; 
				}
			}
			//TODO SSE
		}
	}
	
	public String getPartyLeader() {
		return partyLeader;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPartyBonus() {
		return partyBonus;
	}

	public void setPartyBonus(int partyBonus) {
		this.partyBonus = partyBonus;
	}

	public int getPartyEffect() {
		return partyEffect;
	}

	public void setPartyEffect(int partyEffect) {
		this.partyEffect = partyEffect;
	}

	public String getName() {
		return name;
	}
	
	public int getNumberOfDelegates() {
		int numberOfDelegates = 0;
		for(Map.Entry<String, Integer> entry : delegates.entrySet()) {
			numberOfDelegates += entry.getValue();
		}
		return numberOfDelegates;
	}
}
