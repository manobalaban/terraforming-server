package com.terraforming.server.aplication;

import java.util.Map;

import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.effect.EffectSorter;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

public class GlobalParameterHandler {
	private static GlobalParameterHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	
	private static int oceans = 0;
	private static int temperature = -30;
	private static double oxygen = 0;
	private static int venus = 0;
	
	private GlobalParameterHandler() {}
	
	public static GlobalParameterHandler getInstance() {
		if(instance == null) {
			instance = new GlobalParameterHandler();
		}
		return instance;
	}

	public int getOceans() {
		return oceans;
	}

	public int getTemperature() {
		return temperature;
	}

	public double getOxygen() {
		return oxygen;
	}

	public int getVenus() {
		return venus;
	}
	
	public boolean increaseOceans(boolean increase, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		boolean possible = false;
		if(increase && oceans < 21) {
			oceans++;
			player.increaseTr(1);
			//TODO ocean title placing is still missing
			possible = true;
		} else if(!increase && oceans > 0) {
			oceans--;
			possible = true;
		}
		return possible;
	}
	
	public boolean increaseTemperature(boolean increase, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		boolean possible = false;
		if(increase && temperature < 9) {
			temperature++;
			if(temperature == -24 || temperature == -20) {
				player.setResources(Map.of(Resource.HEAT_PROD, 1));
			}
			if(temperature == 0) {
				increaseOceans(true, player);
			}
			player.increaseTr(1);
			possible = true;
		} else if(!increase && temperature > -30) {
			temperature--;
			possible = true;
		}
		return possible;
	}
	
	public boolean increaseOxygen(boolean increase, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		boolean possible = false;
		if(increase && oxygen < 15) {
			oxygen += 0.5;
			if(oxygen == 4) {
				increaseTemperature(true, player);
			}
			player.increaseTr(1);
			possible = true;
		} else if(!increase && oxygen > 0) {
			oxygen -= 0.5;
			possible = true;
		}
		return possible;
	}
	
	public boolean increaseVenus(boolean increase, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		boolean possible = false;
		if(increase && venus < 30) {
			venus ++;
			if(venus == 4) {
				player.addCardsToHand(CardsHandler.getCardsFromDeck(1));
			}
			if(venus == 8) {
				player.increaseTr(1);
			}
			if(venus > 15 && venus < 30 && (venus - 16) % 2 == 0) {
				//TODO SSE for getting the resource type
			}
			if(venus == 30) {
				//TODO SSE for getting the resource type
			}
			player.increaseTr(1);
			possible = true;
		} else if(!increase && venus > 0) {
			venus ++;
			possible = true;
		}
		return possible;
	}
	
	public PayOption checkIncreaseTemperatureWithHeatEffect(Player actualPlayer) {
		PayOption result = new PayOption(false, 8);
		result.putResourcesWithValue(Map.of(Resource.HEAT, 1));
		TerraformingMarsHandler.getTriggeredEffects(EffectType.USING_HEAT, actualPlayer.getName()).forEach(effect -> EffectSorter.onHeatingEffect(effect.getId(), actualPlayer, result));
		result.setPossible(TerraformingMarsHandler.checkPlayerPayIntention(result, actualPlayer));
		return result;
	}
	
}
