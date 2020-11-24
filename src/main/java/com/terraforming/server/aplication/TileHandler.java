package com.terraforming.server.aplication;

import java.util.Map;

import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.TileType;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.initialize.MarsTilesInitializer;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;
import com.terraforming.server.model.Tile;

public class TileHandler {
	private static TileHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	private GlobalParameterHandler globalParameterHandler = GlobalParameterHandler.getInstance();
	
	private static Map<String, Tile> tiles;
	
	private TileHandler() {}
	
	public static TileHandler getInstance() {
		if(instance == null) {
			tiles = MarsTilesInitializer.initTiles();
			instance = new TileHandler();
		}
		return instance;
	}
	
	public Map<String, Tile> getTiles() {
		return tiles;
	}
	
	public Tile getTile(String tileName) {
		return tiles.get(tileName);
	}
	
	public PayOption checkPlantingEffect(Player actualPlayer) {
		PayOption result = new PayOption(false);
		TerraformingMarsHandler.getTriggeredEffects(EffectType.USING_PLANT, actualPlayer.getName()).forEach(effect -> EffectSorter.onPlantingEffect(effect.getId(), result));
		result.setPossible(actualPlayer.getResources().get(Resource.PLANT) > result.getPrice());
		result.putResourcesWithValue(Map.of(Resource.PLANT, 1));
		return result;
	}
	
	public void planting(String coordinate, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setResources(actualPlayer.getPayingWith());
		player.setPayingWith(null);
		TerraformingMarsHandler.getTriggeredEffects(EffectType.PLACE_TILE, actualPlayer.getName()).forEach(effect -> EffectSorter.onPlaceTileEffect(effect.getId()));
		Tile tile = tiles.get(coordinate);
		tile.setOwner(player.getName());
		tile.setType(TileType.FOREST);
		globalParameterHandler.increaseOxygen(true, player);
		for(Resource resource : tile.getBonus()) {
			player.setResources(Map.of(resource, 1));
		}
	}
}
