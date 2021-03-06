package com.terraforming.server.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Colony {
	
	private String name;
	private boolean active;
	private List<String> playersOnColony = new CopyOnWriteArrayList<String>();
	private int colonyLevel;
	private String traded = "";
	
	public Colony(String name, boolean active) {
		this.name = name;
		this.active = active;
		if(active) {
			colonyLevel = 2;
		}
			
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
		colonyLevel = 2;
	}

	public List<String> getPlayersOnColony() {
		return playersOnColony;
	}
	
	public void addPlayerToColony(String player) {
		playersOnColony.add(player);
	}
	
	public int getColonyLevel() {
		return colonyLevel;
	}

	public void setColonyLevel(int colonyLevel) {
		this.colonyLevel = colonyLevel;
	}

	public void increaseColonyLevel() {
		if(active && colonyLevel < 7) {
			colonyLevel++;
		}
	}
	
	public void trade(String player) {
		traded = player;
		colonyLevel = 1 + playersOnColony.size();
	}

	public String getTraded() {
		return traded;
	}
	
	public void clearTraded() {
		traded = "";
	}
	
}
