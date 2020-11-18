package com.terraforming.server.effect;

import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.aplication.AwardsHandler;
import com.terraforming.server.aplication.MilestonesHandler;
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
	private MilestonesHandler milestonesHandler = MilestonesHandler.getInstance();
	private AwardsHandler awardsHandler = AwardsHandler.getInstance();
	
	private EffectHandler() {}

	public static EffectHandler getInstance() {
		if(instance == null) {
			instance = new EffectHandler();
		}
		return instance;
	}
	
	public void checkCorpPlayedEffect(Player actualPlayer) {
		for(Player player : playersHandler.getPlayers()) {
			for(CardEffect effect : player.getEffects()) {
				if(effect.getTypes().contains(EffectType.CARD_PLAY) && (effect.isTriggeredByEveryne() || actualPlayer.getName().equals(player.getName()))) {
					onCorporationsEffect(effect.getId(), corporationsHandler.getCorporation(actualPlayer.getCorporation()), player);
				}
			}
		}
	}
	
	public PayOption checkPayForResearchEffect(Player player) {
		int cardPrice = 3;
		for(CardEffect effect : player.getEffects()) {
			if(effect.getTypes().contains(EffectType.RESEARCH_PRICE)) {
				cardPrice = onCardBuyEffect(effect.getId());
			}
		}
		int price = player.getCardsToBuy().size() * cardPrice;
		return checkPayIntention(player, price);
	}
	
	public PayOption checkPayForMilestone(String milestone, Player actualPlayer) {
		boolean milestoneAvailable = milestonesHandler.checkMilestone(milestone, actualPlayer);
		int price = 8;
		if(!milestoneAvailable) {
			return new PayOption(false, 0, null);
		}
		return checkPayIntention(actualPlayer, price);
	}
	
	public PayOption checkPayForAward(Player actualPlayer) {
		int price = awardsHandler.checkAwards();
		if(price > 20) {
			return new PayOption(false, 0, null);
		}
		return checkPayIntention(actualPlayer, price);
	}
	
	private PayOption checkPayIntention(Player actualPlayer, int price) {
		Map<Resource, Integer> resourcesToPlay = new HashMap<Resource, Integer>();
		resourcesToPlay.put(Resource.CREDIT, 1);
		int sumValue = 0;
		for(Player player : playersHandler.getPlayers()) {
			for(CardEffect effect : player.getEffects()) {
				if((effect.isTriggeredByEveryne() || actualPlayer.getName().equals(player.getName())) && effect.getTypes().contains(EffectType.PAY_INTENTION)) {
					onPayIntentionEffect(effect.getId(), resourcesToPlay);
				}
			}
		}
		for(Map.Entry<Resource, Integer> resourceWithValue : resourcesToPlay.entrySet()) {
			for(Map.Entry<Resource, Integer> resourceWithQuantity : actualPlayer.getResources().entrySet()) {
				if(resourceWithValue.getKey() == resourceWithQuantity.getKey()) {
					sumValue += resourceWithValue.getValue() * resourceWithQuantity.getValue();
				}
			}
		}
		return new PayOption(sumValue >= price, price, resourcesToPlay);
	}
	
	
	
	
	//EFFECTS
	
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
	
	private void onPayIntentionEffect(String effectId, Map<Resource, Integer> resources) {
		switch (effectId) {
		case "c3":
			CorporationEffects.c3(resources);
			break;
		}
	}

}
