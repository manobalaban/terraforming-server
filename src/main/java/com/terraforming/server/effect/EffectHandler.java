package com.terraforming.server.effect;

import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.aplication.PlayersHandler;
import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.initialize.CorporationsHandler;
import com.terraforming.server.model.CardEffect;
import com.terraforming.server.model.Corporation;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

public class EffectHandler {
	private static EffectHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	private CorporationsHandler corporationsHandler = CorporationsHandler.getInstance();
	
	private EffectHandler() {}

	public static EffectHandler getInstance() {
		if(instance == null) {
			instance = new EffectHandler();
		}
		return instance;
	}
	
	public void checkCorpPlayedEffect(String corpId) {
		for(Player player : playersHandler.getPlayers()) {
			for(CardEffect effect : player.getEffects()) {
				if(effect.getTypes().contains(EffectType.CARD_PLAY)) {
					onCorporationsEffect(effect.getId(), corporationsHandler.getCorporation(corpId), player);
				}
			}
		}
	}
	
	public PayOption checkPayForResearchEffect(Player player) {
		int cardPrice = 3;
		Map<Resource, Integer> resourcesToPlay = new HashMap<Resource, Integer>();
		resourcesToPlay.put(Resource.CREDIT, 1);
		for(CardEffect effect : player.getEffects()) {
			if(effect.getTypes().contains(EffectType.RESEARCH_PRICE)) {
				cardPrice = onCardBuyEffect(effect.getId());
			}
			if(effect.getTypes().contains(EffectType.PAY_FOR_CARD)) {
				onPayForCardEffect(effect.getId(), resourcesToPlay);
			}
		}
		int sum = 0;
		for(Map.Entry<Resource, Integer> resourceWithValue : resourcesToPlay.entrySet()) {
			for(Map.Entry<Resource, Integer> resourceWithQuantity : player.getResources().entrySet()) {
				if(resourceWithValue.getKey() == resourceWithQuantity.getKey()) {
					sum += resourceWithValue.getValue() * resourceWithQuantity.getValue();
				}
			}
		}
		int amount = player.getCardsToBuy().size() * cardPrice;
		return new PayOption(sum >= amount, amount, resourcesToPlay);
		
	}
	
	
	private void onCorporationsEffect(String effectId, Corporation corp, Player effectOwner) {
		switch(effectId) {
			case "c12":
				CorporationEffects.c12(corp, effectOwner);
				break;
		}
	}
	
	private int onCardBuyEffect(String effectId) {
		int cardPrice = 3;
		switch (effectId) {
		case "c25":
			cardPrice = CorporationEffects.c25();
			break;
		case "c31":
			cardPrice = CorporationEffects.c31();
			break;
		}
		return cardPrice;
	}
	
	private void onPayForCardEffect(String effectId, Map<Resource, Integer> resources) {
		switch (effectId) {
		case "c3":
			CorporationEffects.c3(resources);
			break;
		}
	}

}
