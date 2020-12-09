package com.terraforming.server.aplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.CardResource;
import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.Colony;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.PayWith;
import com.terraforming.server.model.Player;

public class ColoniesHandler {
	private static ColoniesHandler instance = null;

	private PlayersHandler playersHandler = PlayersHandler.getInstance();

	private static Map<String, Colony> colonies = new HashMap<String, Colony>();

	private ColoniesHandler() {
	}

	public static ColoniesHandler getInstance() {
		if (instance == null) {
			instance = new ColoniesHandler();
		}
		return instance;
	}

	public Map<String, Colony> getColonies() {
		return colonies;
	}

	public Colony getColony(String name) {
		return colonies.get(name);
	}

	public static void init(int numberOfPLayers) {
		colonies = TerraformingMarsInitialize.initColonies(numberOfPLayers);
	}

	public void checkColoniesForActivation(CardResource cardResource) {
		if (cardResource == CardResource.ANIMAL && getColony("miranda") != null) {
			getColony("miranda").setActive(true);
		} else if (cardResource == CardResource.MICROBE && getColony("encaladus") != null) {
			getColony("encaladus").setActive(true);
		} else if (cardResource == CardResource.FLOATER && getColony("titan") != null) {
			getColony("titan").setActive(true);
		}
	}

	public List<PayOption> checkTradeEffect(Player actualPlayer, String colonyName) {
		List<PayOption> result = new CopyOnWriteArrayList<PayOption>();
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		PayOption payOption = TerraformingMarsHandler.checkPayIntention(actualPlayer, 9);
		payOption.putResourcesWithValue(Map.of(Resource.CREDIT, 1));
		result.add(payOption);
		payOption = new PayOption(player.checkPayWithIntention(new PayWith(Map.of(Resource.ENERGY, 3), new HashMap<String, Integer>())), 3);
		payOption.putResourcesWithValue(Map.of(Resource.ENERGY, 1));
		result.add(payOption);
		payOption = new PayOption(player.checkPayWithIntention(new PayWith(Map.of(Resource.TITAN, 3), new HashMap<String, Integer>())), 3);
		payOption.putResourcesWithValue(Map.of(Resource.TITAN, 1));
		result.add(payOption);

		TerraformingMarsHandler.getTriggeredEffects(EffectType.TRADING, actualPlayer.getName()).forEach(effect -> 
			EffectSorter.onTradeingEffetc(effect.getId(), playersHandler.getPlayer(effect.getOwner()), result));

		Colony colony = getColony(colonyName);

		if (player.getAvailableFleet() == 0 || !colony.getTraded().equals("") || !colony.isActive()) {
			for (PayOption actualOption : result) {
				actualOption.setPossible(false);
			}
		}
		return result;
	}

	public void trade(Player actualPlayer, String colonyName) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setPayWithResources(actualPlayer.getPayingWith());
		player.setPayingWith(new PayWith());
		player.setAvailableFleet(player.getAvailableFleet() - 1);
		switch (colonyName) {
		case "callisto":
			callisto(player);
			break;
		case "ceres":
			ceres(player);
			break;
		case "encaladus":
			encaladus(player);
			break;
		case "europa":
			europa(player);
			break;
		case "ganymede":
			ganymede(player);
			break;
		case "io":
			io(player);
			break;
		case "luna":
			luna(player);
			break;
		case "miranda":
			miranda(player);
			break;
		case "pluto":
			pluto(player);
			break;
		case "titan":
			titan(player);
			break;
		case "triton":
			triton(player);
			break;
		}
	}

	private void callisto(Player player) {
		Colony colony = getColony("callisto");
		switch (colony.getColonyLevel()) {
		case 2:
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 2)));
			break;
		case 3:
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 3)));
			break;
		case 4:
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 5)));
			break;
		case 5:
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 7)));
			break;
		case 6:
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 10)));
			break;
		case 7:
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 13)));
			break;
		}
		for (String playerOnColony : colony.getPlayersOnColony()) {
			playersHandler.getPlayer(playerOnColony).setPayWithResources(new PayWith(Map.of(Resource.ENERGY, 3)));
		}
		colony.trade(player.getName());
	}

	private void ceres(Player player) {
		Colony colony = getColony("ceres");
		switch (colony.getColonyLevel()) {
		case 1:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 1)));
			break;
		case 2:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 2)));
			break;
		case 3:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 3)));
			break;
		case 4:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 4)));
			break;
		case 5:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 6)));
			break;
		case 6:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 8)));
			break;
		case 7:
			player.setPayWithResources(new PayWith(Map.of(Resource.STEEL, 10)));
			break;
		}
		for (String playerOnColony : colony.getPlayersOnColony()) {
			playersHandler.getPlayer(playerOnColony).setPayWithResources(new PayWith(Map.of(Resource.STEEL, 2)));
		}
		colony.trade(player.getName());
	}

	private void encaladus(Player player) {
		Colony colony = getColony("encaladus");
		//TODO SSE
		colony.trade(player.getName());
	}

	private void europa(Player player) {
		Colony colony = getColony("europa");
		//TODO SSE
		colony.trade(player.getName());
	}

	private void ganymede(Player player) {
		Colony colony = getColony("ganymede");
		switch (colony.getColonyLevel()) {
		case 2:
			player.setPayWithResources(new PayWith(Map.of(Resource.PLANT, 1)));
			break;
		case 3:
			player.setPayWithResources(new PayWith(Map.of(Resource.PLANT, 2)));
			break;
		case 4:
			player.setPayWithResources(new PayWith(Map.of(Resource.PLANT, 3)));
			break;
		case 5:
			player.setPayWithResources(new PayWith(Map.of(Resource.PLANT, 4)));
			break;
		case 6:
			player.setPayWithResources(new PayWith(Map.of(Resource.PLANT, 5)));
			break;
		case 7:
			player.setPayWithResources(new PayWith(Map.of(Resource.PLANT, 6)));
			break;
		}
		for (String playerOnColony : colony.getPlayersOnColony()) {
			playersHandler.getPlayer(playerOnColony).setPayWithResources(new PayWith(Map.of(Resource.PLANT, 1)));
		}
		colony.trade(player.getName());
	}

	private void io(Player player) {
		Colony colony = getColony("io");
		switch (colony.getColonyLevel()) {
		case 1:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 2)));
			break;
		case 2:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 3)));
			break;
		case 3:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 4)));
			break;
		case 4:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 6)));
			break;
		case 5:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 8)));
			break;
		case 6:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 10)));
			break;
		case 7:
			player.setPayWithResources(new PayWith(Map.of(Resource.HEAT, 13)));
			break;
		}
		for (String playerOnColony : colony.getPlayersOnColony()) {
			playersHandler.getPlayer(playerOnColony).setPayWithResources(new PayWith(Map.of(Resource.HEAT, 2)));
		}
		colony.trade(player.getName());
	}

	private void luna(Player player) {
		Colony colony = getColony("luna");
		switch (colony.getColonyLevel()) {
		case 1:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 1)));
			break;
		case 2:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 2)));
			break;
		case 3:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 4)));
			break;
		case 4:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 7)));
			break;
		case 5:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 10)));
			break;
		case 6:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 13)));
			break;
		case 7:
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 17)));
			break;
		}
		for (String playerOnColony : colony.getPlayersOnColony()) {
			playersHandler.getPlayer(playerOnColony).setPayWithResources(new PayWith(Map.of(Resource.CREDIT, 2)));
		}
		colony.trade(player.getName());
	}

	private void miranda(Player player) {
		Colony colony = getColony("miranda");
		//TODO SSE
		colony.trade(player.getName());
	}

	private void pluto(Player player) {
		Colony colony = getColony("pluto");
		//TODO SSE
		colony.trade(player.getName());
	}

	private void titan(Player player) {
		Colony colony = getColony("titan");
		//TODO SSE
		colony.trade(player.getName());
	}

	private void triton(Player player) {
		Colony colony = getColony("triton");
		switch (colony.getColonyLevel()) {
		case 2:
			player.setPayWithResources(new PayWith(Map.of(Resource.TITAN, 1)));
			break;
		case 3:
			player.setPayWithResources(new PayWith(Map.of(Resource.TITAN, 1)));
			break;
		case 4:
			player.setPayWithResources(new PayWith(Map.of(Resource.TITAN, 2)));
			break;
		case 5:
			player.setPayWithResources(new PayWith(Map.of(Resource.TITAN, 3)));
			break;
		case 6:
			player.setPayWithResources(new PayWith(Map.of(Resource.TITAN, 4)));
			break;
		case 7:
			player.setPayWithResources(new PayWith(Map.of(Resource.TITAN, 5)));
			break;
		}
		for (String playerOnColony : colony.getPlayersOnColony()) {
			playersHandler.getPlayer(playerOnColony).setPayWithResources(new PayWith(Map.of(Resource.TITAN, 1)));
		}
		colony.trade(player.getName());
	}

}
