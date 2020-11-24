package com.terraforming.server.initialize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.terraforming.server.constans.CardResource;
import com.terraforming.server.constans.EffectType;
import com.terraforming.server.constans.Resource;
import com.terraforming.server.constans.Tag;
import com.terraforming.server.model.CardAction;
import com.terraforming.server.model.CardEffect;
import com.terraforming.server.model.Corporation;
import com.terraforming.server.model.OnCardCollected;

public class CorporationsInitializer {

	public static Map<String, Corporation> initCorporations() {
		Map<String, Corporation> corporations = new HashMap<String, Corporation>();
		corporations.put("c1", new Corporation("c1", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 57), new CardEffect("c1", Arrays.asList(EffectType.CARD_PLAY, EffectType.STANDARD_PROJECT))));
		corporations.put("c2", new Corporation("c2", Arrays.asList(Tag.PLANT), Map.of(Resource.CREDIT, 36, Resource.PLANT_PROD, 2, Resource.PLANT, 3), new CardEffect("c2", Arrays.asList(EffectType.USING_PLANT))));
		corporations.put("c3", new Corporation("c3", Arrays.asList(Tag.SPACE), Map.of(Resource.CREDIT, 42, Resource.HEAT_PROD, 3), new CardEffect("c3", Arrays.asList(EffectType.PAY_INTENTION))));
		corporations.put("c4", new Corporation("c4", Arrays.asList(Tag.BUILDING, Tag.BUILDING), Map.of(Resource.CREDIT, 30, Resource.STEEL, 5, Resource.STEEL_PROD, 1), new CardEffect("c4", Arrays.asList(EffectType.PLACE_TILE))));
		corporations.put("c5", new Corporation("c5", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 30, Resource.STEEL, 20), new CardEffect("c5", Arrays.asList(EffectType.CARD_PLAY))));
		corporations.put("c6", new Corporation("c6", Arrays.asList(Tag.SCIENCE), Map.of(Resource.CREDIT, 40), new CardEffect("c6", Arrays.asList(EffectType.CARD_PLAY_INTENTION))));
		corporations.put("c7", new Corporation("c7", Arrays.asList(Tag.SPACE), Map.of(Resource.CREDIT, 23, Resource.TITAN, 10), new CardEffect("c7", Arrays.asList(EffectType.CARD_PLAY_INTENTION, EffectType.TRADING))));
		corporations.put("c8", new Corporation("c8", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 40), new CardEffect("c8", Arrays.asList(EffectType.PLACE_TILE), true)));
		corporations.put("c9", new Corporation("c9", Arrays.asList(Tag.ENERGY), Map.of(Resource.CREDIT, 48, Resource.ENERGY_PROD, 1), new CardEffect("c9", Arrays.asList(EffectType.CARD_PLAY_INTENTION, EffectType.STANDARD_PROJECT))));
		corporations.put("c10", new Corporation("c10", Arrays.asList(Tag.EARTH), Map.of(Resource.CREDIT, 40), new CardAction("c10")));
		corporations.put("c11", new Corporation("c11", Arrays.asList(Tag.EARTH), Map.of(Resource.CREDIT, 60), new CardEffect("c11", Arrays.asList(EffectType.CARD_PLAY_INTENTION))));
		corporations.put("c12", new Corporation("c12", Arrays.asList(Tag.JUVIAN), Map.of(Resource.CREDIT, 42, Resource.TITAN_PROD, 1, Resource.CREDIT_PROD, 1), new CardEffect("c12", Arrays.asList(EffectType.CARD_PLAY), true)));
		corporations.put("c13", new Corporation("c13", Arrays.asList(Tag.VENUS, Tag.PLANT), Map.of(Resource.CREDIT, 47, Resource.PLANT_PROD, 1), new CardEffect("c13", Arrays.asList(EffectType.GLOBAL_PARAMETER_MODIFY), true)));
		corporations.put("c14", new Corporation("c14", Arrays.asList(Tag.VENUS), Map.of(Resource.CREDIT, 42), new CardAction("c14"), new OnCardCollected("c14", 0, CardResource.FLOATER)));
		corporations.put("c15", new Corporation("c15", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 35, Resource.STEEL_PROD, 1, Resource.STEEL, 1), new CardEffect("c15", Arrays.asList(EffectType.CHANGE_PRODUCTION))));
		corporations.put("c16", new Corporation("c16", Arrays.asList(Tag.VENUS), Map.of(Resource.CREDIT, 50), new CardEffect("c16", Arrays.asList(EffectType.CARD_PLAY_INTENTION))));
		corporations.put("c17", new Corporation("c17", Arrays.asList(Tag.MICROBE), Map.of(Resource.CREDIT, 48), new CardAction("c17")));
		corporations.put("c18", new Corporation("c18", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 44, Resource.CREDIT_PROD, 3), new CardEffect("c18", Arrays.asList(EffectType.CARD_PLAY_INTENTION))));
		corporations.put("c19", new Corporation("c19", Arrays.asList(Tag.EARTH, Tag.SPACE), Map.of(Resource.CREDIT, 38, Resource.TITAN_PROD, 1), new CardEffect("c19", Arrays.asList(EffectType.CARD_PLAY_INTENTION))));
		corporations.put("c20", new Corporation("c20", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 47), new CardAction("c20")));
		corporations.put("c21", new Corporation("c21", Arrays.asList(Tag.EARTH), Map.of(Resource.CREDIT, 37), new CardEffect("c21", Arrays.asList(EffectType.CARD_PLAY_INTENTION))));
		corporations.put("c22", new Corporation("c22", Arrays.asList(Tag.EARTH), Map.of(Resource.CREDIT, 48), new CardEffect("c22", Arrays.asList(EffectType.CARD_PLAY))));
		corporations.put("c23", new Corporation("c23", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 40), new CardEffect("c23", Arrays.asList(EffectType.CARD_PLAY))));
		corporations.put("c24", new Corporation("c24", Arrays.asList(Tag.ANIMAL), Map.of(Resource.CREDIT, 45, Resource.CREDIT_PROD, 2), new CardEffect("c24", Arrays.asList(EffectType.CARD_PLAY)), new OnCardCollected("c24", 1, CardResource.ANIMAL)));
		corporations.put("c25", new Corporation("c25", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 50, Resource.CREDIT_PROD, 5, Resource.TITAN, 5), new CardEffect("c25", Arrays.asList(EffectType.RESEARCH_PRICE))));
		corporations.put("c26", new Corporation("c26", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 45), new CardEffect("c26", Arrays.asList(EffectType.BUY_COLONIE), true)));
		corporations.put("c27", new Corporation("c27", Arrays.asList(Tag.JUVIAN), Map.of(Resource.CREDIT, 48), new CardEffect("c27", Arrays.asList(EffectType.USING_HEAT)),new CardAction("c27"), new OnCardCollected("c27", 0, CardResource.FLOATER)));
		corporations.put("c28", new Corporation("c28", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 54), new CardEffect("c28", Arrays.asList(EffectType.PLACE_TILE), true)));
		corporations.put("c29", new Corporation("c29", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 53), new CardEffect("c29", Arrays.asList(EffectType.PRODUCTION)), new OnCardCollected("c29", 0, CardResource.PRESERVATION)));
		corporations.put("c30", new Corporation("c30", Arrays.asList(Tag.WILD), Map.of(Resource.CREDIT, 36), new CardAction("c30")));
		corporations.put("c31", new Corporation("c31", Arrays.asList(Tag.SCIENCE, Tag.EARTH), Map.of(Resource.CREDIT, 14), new CardEffect("c31", Arrays.asList(EffectType.RESEARCH_PRICE))));
		corporations.put("c32", new Corporation("c32", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 40, Resource.STEEL_PROD, 1, Resource.TITAN_PROD, 1), new CardAction("c32")));
		corporations.put("c33", new Corporation("c33", Arrays.asList(Tag.ENERGY, Tag.BUILDING), Map.of(Resource.CREDIT, 37, Resource.STEEL_PROD, 1), new CardAction("c33")));
		corporations.put("c34", new Corporation("c34", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 48, Resource.CREDIT_PROD, 4), new CardEffect("c34", Arrays.asList(EffectType.CHANGE_PRODUCTION, EffectType.CHANGE_RESOURCE), true)));
		corporations.put("c35", new Corporation("c35", Arrays.asList(Tag.BUILDING), Map.of(Resource.CREDIT, 47), new CardEffect("c35", Arrays.asList(EffectType.PLACE_TILE), true)));
		corporations.put("c36", new Corporation("c36", Arrays.asList(Tag.NONE), Map.of(Resource.CREDIT, 40, Resource.STEEL, 10), new CardEffect("c36", Arrays.asList(EffectType.PLACE_TILE)), new CardAction("c36")));
		corporations.put("c37", new Corporation("c37", Arrays.asList(Tag.MICROBE, Tag.BUILDING), Map.of(Resource.CREDIT, 38, Resource.STEEL_PROD, 1), new CardEffect("c37", Arrays.asList(EffectType.CARD_PLAY)), new OnCardCollected("c37", 1, CardResource.MICROBE)));
		corporations.put("c38", new Corporation("c38", Arrays.asList(Tag.MICROBE), Map.of(Resource.CREDIT, 44), new CardEffect("c38", Arrays.asList(EffectType.CARD_PLAY), true)));
		return corporations;
	}
}
