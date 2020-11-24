package com.terraforming.server.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.TileType;

public class Tile {
	private String name;
	private List<Resource> bonus = new CopyOnWriteArrayList<Resource>();
	private TileType type;
	private String owner = "";
	private List<String> adjecent = new CopyOnWriteArrayList<String>();
	
	public Tile(String name, TileType type) {
		this.name = name;
		this.type = type;
	}
	
	public Tile(String name, TileType type, List<Resource> bonus) {
		this(name, type);
		this.bonus = bonus;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public List<String> getAdjecent() {
		return adjecent;
	}

	public void addAdjecent(String tile) {
		this.adjecent.add(tile);
	}

	public String getName() {
		return name;
	}

	public List<Resource> getBonus() {
		return bonus;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
}
