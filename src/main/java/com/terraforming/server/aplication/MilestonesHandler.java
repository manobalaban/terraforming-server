package com.terraforming.server.aplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;
import com.terraforming.server.initialize.TerraformingMarsInitialize;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.PayWith;
import com.terraforming.server.model.Player;

public class MilestonesHandler {
	private static MilestonesHandler instance = null;
	private static Map<String, String> milestones = new HashMap<String, String>();
	private static PlayersHandler playersHandler = PlayersHandler.getInstance();
	
	private MilestonesHandler() {}
	
	public static MilestonesHandler getInstance() {
		if(instance == null) {
			milestones = TerraformingMarsInitialize.initMilestones();
			instance = new MilestonesHandler();
		}
		return instance;
	}
	
	public Map<String, String> getMilestones() {
		return milestones;
	}
	
	public void setMilestone(String milestone, Player actualPlayer) {
		Player player = playersHandler.getPlayer(actualPlayer.getName());
		player.setPayWithResources(actualPlayer.getPayingWith());
		player.setPayingWith(new PayWith());
		milestones.put(milestone, player.getName());
	}
	
	public PayOption checkPayForMilestone(String milestone, Player actualPlayer) {
		int achievedMilestones = 0;
		for(Map.Entry<String, String> stone : milestones.entrySet()) {
			if(stone.getValue() != null) {
				achievedMilestones ++;
			}
		}
		boolean milestoneAvailable = achievedMilestones < 3 ? checkMilestones(milestone, actualPlayer) : false;
		int price = 8;
		if(!milestoneAvailable) {
			return new PayOption(false);
		}
		return TerraformingMarsHandler.checkPayIntention(actualPlayer, price);
	}
	
	private boolean checkMilestones(String milestone, Player player) {
		boolean result = true;
			switch (milestone) {
			case "builder":
				result = builder(player);
				break;
			case "diversifier":
				result = diversifier(player);
				break;
			case "ecologist":
				result = ecologist(player);
				break;
			case "energizer":
				result = energizer(player);
				break;
			default:
				break;
			}
		return result;
	}
	
	private boolean builder(Player player) {
		int tagNumber = 0;
		for(Tag tag : player.getTags()) {
			if(tag == Tag.BUILDING) {
				tagNumber++;
			}
		}
		return tagNumber >= 8;
	}
	
	private boolean diversifier(Player player) {
		List<Tag> ownedTags = new CopyOnWriteArrayList<Tag>();
		for(Tag tag : player.getTags()) {
			if(!ownedTags.contains(tag)) {
				ownedTags.add(tag);
			}
		}
		return ownedTags.size() >= 9;
	}
	
	private boolean ecologist(Player player) {
		int tagNumber = 0;
		for(Tag tag : player.getTags()) {
			if(tag == Tag.PLANT || tag == Tag.MICROBE || tag == Tag.ANIMAL) {
				tagNumber++;
			}
		}
		return tagNumber >= 4;
	}
	
	private boolean energizer(Player player) {
		return player.getResources().get(Resource.ENERGY_PROD) >= 6;
	}
	
}
