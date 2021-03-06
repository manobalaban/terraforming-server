package com.terraforming.server.effect;

import java.util.Map;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;
import com.terraforming.server.model.BasicCard;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

public class CorporationEffects {
	
	public static void c1(Player effectOwner) {
		effectOwner.getResources().put(Resource.CREDIT, effectOwner.getResources().get(Resource.CREDIT) + 4);
	}
	
	public static int c2() {
		return 7;
	}
	
	public static void c3(Map<Resource, Integer> resources) {
		resources.put(Resource.HEAT, 1);
	}
	
	public static void c12(BasicCard card, Player effectOwner) {
		for(Tag tag : card.getTags()) {
			if(tag == Tag.JOVIAN) {
				effectOwner.getResources().put(Resource.CREDIT_PROD, effectOwner.getResources().get(Resource.CREDIT_PROD) + 1);
			}
		}
	}
	
	public static int c25() {
		return 5;
	}
	
	public static void c27(Player player, PayOption payOption) {
		payOption.putOnCardResourcesWithValue(Map.of(player.getCollections().get("c27"), 2));
	}

	public static int c31() {
		return 1;
	}
}
