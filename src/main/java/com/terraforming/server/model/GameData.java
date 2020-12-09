package com.terraforming.server.model;

import java.util.List;
import java.util.Map;

import com.terraforming.server.constans.GamePhase;

public class GameData {
	private GamePhase actualPhase;
	private Map<String, Tile> tiles;
	
	private int oceans;
	private int temperature;
	private double oxygen;
	private int venus;
	
	private Map<String, String> milestones;
	private Map<String, Boolean> awards;
	private Map<String, Colony> colonies;
	
	private Map<String, Party> parties;
	private static List<String> freeDelegates;
	private static Map<String, Integer> delegates;
	private static String dominantParty;
	
	public GamePhase getActualPhase() {
		return actualPhase;
	}
	public void setActualPhase(GamePhase actualPhase) {
		this.actualPhase = actualPhase;
	}
	public Map<String, Tile> getTiles() {
		return tiles;
	}
	public void setTiles(Map<String, Tile> tiles) {
		this.tiles = tiles;
	}
	public int getOceans() {
		return oceans;
	}
	public void setOceans(int oceans) {
		this.oceans = oceans;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public double getOxygen() {
		return oxygen;
	}
	public void setOxygen(double oxygen) {
		this.oxygen = oxygen;
	}
	public int getVenus() {
		return venus;
	}
	public void setVenus(int venus) {
		this.venus = venus;
	}
	public Map<String, String> getMilestones() {
		return milestones;
	}
	public void setMilestones(Map<String, String> milestones) {
		this.milestones = milestones;
	}
	public Map<String, Boolean> getAwards() {
		return awards;
	}
	public void setAwards(Map<String, Boolean> awards) {
		this.awards = awards;
	}
	public Map<String, Colony> getColonies() {
		return colonies;
	}
	public void setColonies(Map<String, Colony> colonies) {
		this.colonies = colonies;
	}
	public Map<String, Party> getParties() {
		return parties;
	}
	public void setParties(Map<String, Party> parties) {
		this.parties = parties;
	}
	public List<String> getFreeDelegates() {
		return freeDelegates;
	}
	public void setFreeDelegates(List<String> freeDelegates) {
		GameData.freeDelegates = freeDelegates;
	}
	public Map<String, Integer> getDelegates() {
		return delegates;
	}
	public void setDelegates(Map<String, Integer> delegates) {
		GameData.delegates = delegates;
	}
	public String getDominantParty() {
		return dominantParty;
	}
	public void setDominantParty(String dominantParty) {
		GameData.dominantParty = dominantParty;
	}
	
}
