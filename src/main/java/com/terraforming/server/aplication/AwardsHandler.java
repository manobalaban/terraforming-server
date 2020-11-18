package com.terraforming.server.aplication;

import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.initialize.TerraformingMarsInitialize;
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
		playersHandler.pay(actualPlayer);
		awards.put(name, true);
	}
	
	public int checkAwards() {
		int establishedAwards = 0;
		for(Map.Entry<String, Boolean> entry : awards.entrySet()) {
			if(entry.getValue() == true) {
				establishedAwards ++;
			}
		}
		return 8 + (establishedAwards * 2);
	}

}
