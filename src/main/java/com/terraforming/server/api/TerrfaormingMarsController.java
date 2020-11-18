package com.terraforming.server.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.terraforming.server.aplication.AwardsHandler;
import com.terraforming.server.aplication.MilestonesHandler;
import com.terraforming.server.aplication.PhaseHandler;
import com.terraforming.server.aplication.PlayersHandler;
import com.terraforming.server.effect.EffectHandler;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TerrfaormingMarsController {
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	private EffectHandler effectHandler = EffectHandler.getInstance();
	private PhaseHandler phaseHandler = PhaseHandler.getIntance();
	private MilestonesHandler milestonesHandler = MilestonesHandler.getInstance();
	private AwardsHandler awardsHandler = AwardsHandler.getInstance();
	
	private static List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();
	private final String UPDATE_ALL_PLAYER = "updateAllPlayer";
	private final String NEXT_PHASE = "nextPhase";

	@GetMapping("/players")
	public ResponseEntity<List<Player>> getPlayers(@RequestParam(required = false) String name) {
		if(name != null) {
			playersHandler.chosePlayer(name);
			sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		}
		return new ResponseEntity<List<Player>>(playersHandler.getPlayers(), HttpStatus.OK);
	}
	
	@PutMapping("/choseFirstTenCards")
	public ResponseEntity<Player> choseFirstTenCards(@RequestBody Player player) {
		boolean ready = playersHandler.choseFirstTenCard(player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		if(ready) {
			sendEvent(NEXT_PHASE, "phase", phaseHandler.nextPhase());
		}
		return new ResponseEntity<Player>(playersHandler.getPlayer(player.getName()), HttpStatus.OK);
	}
	
	@PutMapping("/choseCorporation")
	public ResponseEntity<String> choseCorporation(@RequestBody Player player) {
		effectHandler.checkCorpPlayedEffect(player);
		//FIRST ACTION: c6 c8 c14 c16 c21 c22 c23 c26 c35 c36 c38
		boolean ready = playersHandler.choseCorporation(player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		if(ready) {
			sendEvent(NEXT_PHASE, "phase", phaseHandler.nextPhase());
		}
		return new ResponseEntity<String>(player.getCorporation(), HttpStatus.OK);
	}
	
	@PutMapping("/researchIntention")
	public ResponseEntity<PayOption> researchIntention(@RequestBody Player player) {
		return new ResponseEntity<PayOption>(effectHandler.checkPayForResearchEffect(player), HttpStatus.OK);
	}
	
	@PutMapping("/actualResearch")
	public ResponseEntity<Player> actualResearch(@RequestBody Player player) {
		boolean ready = playersHandler.research(player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		if(ready) {
			sendEvent(NEXT_PHASE, "phase",phaseHandler.nextPhase());
		}
		return new ResponseEntity<Player>(playersHandler.getPlayer(player.getName()), HttpStatus.OK);
	}
	
	@PutMapping("/action/milestoneIntention/{milestone}")
	public ResponseEntity<PayOption> milestoneIntention(@PathVariable String milestone, @RequestBody Player player) {
		return new ResponseEntity<PayOption>(effectHandler.checkPayForMilestone(milestone, player), HttpStatus.OK);
	}
	
	@PutMapping("/action/milestone/{milestone}")
	public ResponseEntity<Player> milestone(@PathVariable String milestone, @RequestBody Player player) {
		milestonesHandler.setMilestone(milestone, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		return new ResponseEntity<Player>(playersHandler.getPlayer(player.getName()), HttpStatus.OK);
	}
	
	@PutMapping("/action/awardIntention")
	public ResponseEntity<PayOption> awardIntention(@RequestBody Player player) {
		return new ResponseEntity<PayOption>(effectHandler.checkPayForAward(player), HttpStatus.OK);
	}
	
	@PutMapping("/action/award/{award}")
	public ResponseEntity<Player> award(@PathVariable String award, @RequestBody Player player) {
		awardsHandler.setAward(award, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		return new ResponseEntity<Player>(playersHandler.getPlayer(player.getName()), HttpStatus.OK);
	}
	
	
	//SSE
	
	@CrossOrigin
	@RequestMapping(value = "/subscribeUsers", consumes = MediaType.ALL_VALUE)
	public SseEmitter subscribe() {
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
		try {
			sseEmitter.send(SseEmitter.event().name("INIT").data("INIT"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

		emitters.add(sseEmitter);
		return sseEmitter;
	}
	
	public void sendEvent(String eventName, String dataName, Object data) {
		String formattedData = new JSONObject().put(dataName, data).toString();
		for (SseEmitter emitter : emitters) {
			try {
				emitter.send(SseEmitter.event().name(eventName).data(formattedData));
			} catch (IOException e) {
				emitters.remove(emitter);
			}
		}
	}
}