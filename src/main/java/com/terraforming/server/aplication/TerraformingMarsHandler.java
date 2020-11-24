package com.terraforming.server.aplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.model.CardEffect;
import com.terraforming.server.model.GameData;
import com.terraforming.server.model.OnCardCollected;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

public class TerraformingMarsHandler {
	
	private static PlayersHandler playersHandler = PlayersHandler.getInstance();
	
	public static GameData getGameData() {
		GameData gameData = new GameData();
		gameData.setActualPhase(PhaseHandler.getIntance().getActualPhase());
		gameData.setMilestones(MilestonesHandler.getInstance().getMilestones());
		gameData.setAwards(AwardsHandler.getInstance().getAwards());
		gameData.setTiles(TileHandler.getInstance().getTiles());
		gameData.setOceans(GlobalParameterHandler.getInstance().getOceans());
		gameData.setTemperature(GlobalParameterHandler.getInstance().getTemperature());
		gameData.setOxygen(GlobalParameterHandler.getInstance().getOxygen());
		gameData.setVenus(GlobalParameterHandler.getInstance().getVenus());
		gameData.setColonies(ColoniesHandler.getInstance().getColonies());
		gameData.setParties(TurmoilHandler.getInstance().getParties());
		gameData.setFreeDelegates(TurmoilHandler.getInstance().getFreeDelegates());
		gameData.setDelegates(TurmoilHandler.getInstance().getDelegates());
		gameData.setDominantParty(TurmoilHandler.getInstance().getDominantParty());
		return null;
	}

	public static List<CardEffect> getTriggeredEffects(EffectType type, String actualPlayerName) {
		List<CardEffect> result = new CopyOnWriteArrayList<CardEffect>();
		for(Player player : playersHandler.getPlayers()) {
			for(Map.Entry<String,CardEffect> effectEntry : player.getEffects().entrySet()) {
				if((effectEntry.getValue().isTriggeredByEveryne() || actualPlayerName.equals(player.getName())) && effectEntry.getValue().getTypes().contains(type)) {
					result.add(effectEntry.getValue());
				}
			}
		}
		return result;
	}
	
	public static PayOption checkPayIntention(Player actualPlayer, int price) {
		Map<Resource, Integer> resourcesToPlay = new HashMap<Resource, Integer>();
		resourcesToPlay.put(Resource.CREDIT, 1);
		int sumValue = 0;
		getTriggeredEffects(EffectType.PAY_INTENTION, actualPlayer.getName()).forEach(effect -> EffectSorter.onPayIntentionEffect(effect.getId(), resourcesToPlay));
		for(Map.Entry<Resource, Integer> resourceWithValue : resourcesToPlay.entrySet()) {
			for(Map.Entry<Resource, Integer> resourceWithQuantity : actualPlayer.getResources().entrySet()) {
				if(resourceWithValue.getKey() == resourceWithQuantity.getKey()) {
					sumValue += resourceWithValue.getValue() * resourceWithQuantity.getValue();
				}
			}
		}
		PayOption result = new PayOption(sumValue >= price, price);
		result.putResourcesWithValue(resourcesToPlay);
		return result;
	}
	
	public static boolean checkPlayerPayIntention(PayOption payOption, Player player) {
		int sum = 0;
		for(Map.Entry<Resource, Integer> resWitValEntry : payOption.getResourcesWithValue().entrySet()) {
			for(Map.Entry<Resource, Integer> playerResourceEntry : player.getResources().entrySet()) {
				if(resWitValEntry.getKey() == playerResourceEntry.getKey()) {
					sum += resWitValEntry.getValue() * playerResourceEntry.getValue();
				}
			}
		}
		for(Map.Entry<OnCardCollected, Integer> entry : payOption.getOnCardResourcesWithValue().entrySet()) {
			sum += entry.getKey().getQuantity() * payOption.getOnCardResourcesWithValue().get(entry.getKey());
		}
		return payOption.getPrice() <= sum;
	}
}
