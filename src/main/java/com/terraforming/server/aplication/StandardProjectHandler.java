package com.terraforming.server.aplication;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.PayWith;
import com.terraforming.server.model.Player;

public class StandardProjectHandler {
	private static StandardProjectHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	private CardsHandler cardsHandler = CardsHandler.getInstance();
	private GlobalParameterHandler globalParameterHandler = GlobalParameterHandler.getInstance();
	
	private StandardProjectHandler() {}
	
	public static StandardProjectHandler getInstance() {
		if(instance == null) {
			instance = new StandardProjectHandler();
		}
		return instance;
	}
	
	public PayOption checkStandardProjectEffect(String projectName, Player actualPlayer) {
		int price = 0;
		switch (projectName) {
		case "powerPlant":
			price = 11;
			break;
		case "asteroid":
			price = 14;
			break;
		case "aquifer":
			price = 18;
			break;
		case "greenery":
			price = 23;
			break;
		case "city":
			price = 25;
			break;
		case "airScrapping":
			price = 15;
			break;
		case "buyColony":
			price = 17;
			break;
		}
		PayOption payOption = TerraformingMarsHandler.checkPayIntention(actualPlayer, price);
		TerraformingMarsHandler.getTriggeredEffects(EffectType.STANDARD_PROJECT, actualPlayer.getName()).forEach(effect -> EffectSorter.onStandardProjectEffect(actualPlayer.getName(), payOption));
		return payOption;
	}
	
	public void standardProject(String projectName, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setPayWithResources(actualPlayer.getPayingWith());
		player.setPayingWith(new PayWith());
		switch (projectName) {
		case "sell_patents":
			sellPatients(actualPlayer);
			break;
		case "powerPlant":
			player.setPayWithResources(new PayWith(Map.of(Resource.ENERGY_PROD, 1)));
			TerraformingMarsHandler.getTriggeredEffects(EffectType.CHANGE_PRODUCTION, actualPlayer.getName()).forEach(effect -> EffectSorter.onChangeProduction(effect.getId(), player));
			break;
		case "asteroid":
			globalParameterHandler.increaseTemperature(true, actualPlayer);
			break;
		case "aquifer":
			globalParameterHandler.increaseOceans(true, actualPlayer);
			break;
		case "greenery":
			globalParameterHandler.increaseOxygen(true, actualPlayer);
			//TODO SSE
			break;
		case "city":
			player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT_PROD, 1)));
			TerraformingMarsHandler.getTriggeredEffects(EffectType.CHANGE_PRODUCTION, actualPlayer.getName()).forEach(effect -> EffectSorter.onChangeProduction(effect.getId(), player));
			//TODO SSE
			break;
		case "airScrapping":
			globalParameterHandler.increaseVenus(true, actualPlayer);
			break;
		case "buyColony":
			//TODO SSE
			break;
		}
	}
	
	private void sellPatients(Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setPayWithResources(new PayWith(Map.of(Resource.CREDIT, actualPlayer.getDrawnCards().size())));
		for(String cardId : actualPlayer.getDrawnCards()) {
			cardsHandler.burnCard(cardId);
			player.pullCardFromHand(cardId);
		}
		player.setDrawnCards(new CopyOnWriteArrayList<String>());
	}	
}
