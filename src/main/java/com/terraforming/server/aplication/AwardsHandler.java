package com.terraforming.server.aplication;

import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.PayWith;
import com.terraforming.server.model.Player;

public class AwardsHandler {
	private static AwardsHandler instance = null;
	private static Map<String, Boolean> awards = new HashMap<String, Boolean>();
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	
	private AwardsHandler() {}
	
	public static AwardsHandler getInstance() {
		if(instance == null) {
			awards = TerraformingMarsInitialize.initAwards();
			instance = new AwardsHandler();
		}
		return instance;
	}

	public Map<String, Boolean> getAwards() {
		return awards;
	}
	
	public void setAward(String name, Player actualPlayer) {
		playersHandler.getPlayer(actualPlayer.getName()).setPayWithResources(actualPlayer.getPayingWith());
		playersHandler.getPlayer(actualPlayer.getName()).setPayingWith(new PayWith());
		awards.put(name, true);
	}
	
	public PayOption checkPayForAward(Player actualPlayer) {
		int establishedAwards = 0;
		for(Map.Entry<String, Boolean> entry : awards.entrySet()) {
			if(entry.getValue() == true) {
				establishedAwards ++;
			}
		}
		int price = 8 + (establishedAwards * 2);
		if(price > 20) {
			return new PayOption(false);
		}
		return TerraformingMarsHandler.checkPayIntention(actualPlayer, price);
	}

}
