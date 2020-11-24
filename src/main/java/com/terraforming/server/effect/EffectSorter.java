package com.terraforming.server.effect;

import java.util.List;
import java.util.Map;

import com.terraforming.server.constans.Resource;
import com.terraforming.server.model.Corporation;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

public class EffectSorter {
	public static void onCorporationsEffect(String effectId, Corporation corp, Player effectOwner) {
		switch(effectId) {
			case "c12":
				CorporationEffects.c12(corp, effectOwner);
				break;
		}
	}
	
	public static void onCardBuyEffect(String effectId, int cardPrice) {
		cardPrice = 3;
		switch (effectId) {
		case "c25":
			cardPrice = CorporationEffects.c25();
			break;
		case "c31":
			cardPrice = CorporationEffects.c31();
			break;
		}
	}
	
	public static void onPayIntentionEffect(String effectId, Map<Resource, Integer> resources) {
		switch (effectId) {
		case "c3":
			CorporationEffects.c3(resources);
			break;
		}
	}
	
	public static void onPlantingEffect(String effectId, PayOption payOption) {
		int plantForForest = 8;
		switch (effectId) {
		case "c2":
			plantForForest = CorporationEffects.c2();
			break;
		}
		payOption.setPrice(plantForForest);
	}
	
	public static void onHeatingEffect(String effectId, Player player, PayOption payOption) {
		switch (effectId) {
		case "c27":
			CorporationEffects.c27(player, payOption);
			break;
		}
	}
	
	public static void onBuyDelegate(String effectId, int price) {
		price = 5;
	}
	
	public static void onTradeingEffetc(String effectId, Player player, List<PayOption> payOptions) {
		//TODO
	}
	
	public static void onPlaceTileEffect(String effectId) {
		//TODO
	}
	
	public static void onStandardProjectEffect(String effectId, PayOption payOption) {
		//TODO
	}
	
	public static void onChangeProduction(String effectId, Player actualPlayer) {
		//TODO
	}
}
