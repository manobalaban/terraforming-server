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
import com.terraforming.server.aplication.CardsHandler;
import com.terraforming.server.aplication.ColoniesHandler;
import com.terraforming.server.aplication.GlobalParameterHandler;
import com.terraforming.server.aplication.MilestonesHandler;
import com.terraforming.server.aplication.PhaseHandler;
import com.terraforming.server.aplication.PlayersHandler;
import com.terraforming.server.aplication.StandardProjectHandler;
import com.terraforming.server.aplication.TerraformingMarsHandler;
import com.terraforming.server.aplication.TileHandler;
import com.terraforming.server.aplication.TurmoilHandler;
import com.terraforming.server.model.GameData;
import com.terraforming.server.model.PayOption;
import com.terraforming.server.model.Player;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TerrfaormingMarsController {
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	private CardsHandler cardsHandler = CardsHandler.getInstance();
	private PhaseHandler phaseHandler = PhaseHandler.getIntance();
	private MilestonesHandler milestonesHandler = MilestonesHandler.getInstance();
	private AwardsHandler awardsHandler = AwardsHandler.getInstance();
	private TileHandler tileHandler = TileHandler.getInstance();
	private GlobalParameterHandler globalParameterHandler = GlobalParameterHandler.getInstance();
	private ColoniesHandler coloniesHandler = ColoniesHandler.getInstance();
	private TurmoilHandler turmoilHandler = TurmoilHandler.getInstance();
	private StandardProjectHandler standardProjectHandler = StandardProjectHandler.getInstance();
	
	private static List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();
	private final String UPDATE_ALL_PLAYER = "updateAllPlayer";
	private final String UPDATE_TABLE = "updateTable";

	@GetMapping("/players")
	public ResponseEntity<List<Player>> getPlayers(@RequestParam(required = false) String name) {
		if(name != null) {
			playersHandler.chosePlayer(name);
			sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		}
		return new ResponseEntity<List<Player>>(playersHandler.getPlayers(), HttpStatus.OK);
	}
	
	@GetMapping("/table")
	public ResponseEntity<GameData> getTiles() {
		return new ResponseEntity<GameData>(TerraformingMarsHandler.getGameData(), HttpStatus.OK);
	}
	
	@PutMapping("/choseFirstTenCards")
	public ResponseEntity<Player> choseFirstTenCards(@RequestBody Player player) {
		boolean ready = cardsHandler.choseFirstTenCard(player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		if(ready) {
			phaseHandler.nextPhase();
			sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		}
		return new ResponseEntity<Player>(playersHandler.getPlayer(player.getName()), HttpStatus.OK);
	}
	
	@PutMapping("/choseCorporation")
	public ResponseEntity<String> choseCorporation(@RequestBody Player player) {
		cardsHandler.checkCorpPlayedEffect(player);
		//FIRST ACTION: c6 c8 c14 c16 c21 c22 c23 c26 c35 c36 c38
		boolean ready = cardsHandler.choseCorporation(player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		if(ready) {
			phaseHandler.nextPhase();
			sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		}
		return new ResponseEntity<String>(player.getCorporation(), HttpStatus.OK);
	}
	
	@PutMapping("/research")
	public ResponseEntity<PayOption> researchIntention(@RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<PayOption>(cardsHandler.checkPayForResearchEffect(player), HttpStatus.OK);
		}
		boolean ready = cardsHandler.research(player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		if(ready) {
			phaseHandler.nextPhase();
			sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		}
		return new ResponseEntity<PayOption>(HttpStatus.OK);
	}
	
	@PutMapping("/action/milestone/{milestone}")
	public ResponseEntity<PayOption> milestone(@PathVariable String milestone, @RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<PayOption>(milestonesHandler.checkPayForMilestone(milestone, player), HttpStatus.OK);
		}
		milestonesHandler.setMilestone(milestone, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<PayOption>(HttpStatus.OK);
	}
	
	@PutMapping("/action/award/{award}")
	public ResponseEntity<PayOption> award(@PathVariable String award, @RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<PayOption>(awardsHandler.checkPayForAward(player), HttpStatus.OK);
		}
		awardsHandler.setAward(award, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<PayOption>(HttpStatus.OK);
	}
	
	@PutMapping("/action/planting/{coordinate}")
	public ResponseEntity<PayOption> planting(@PathVariable String coordinate, @RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<PayOption>(tileHandler.checkPlantingEffect(player), HttpStatus.OK);
		}
		tileHandler.planting(coordinate, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<PayOption>(HttpStatus.OK); 
	}
	
	@PutMapping("/action/increaseTemperature")
	public ResponseEntity<PayOption> increaseTemperature(@RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<PayOption>(globalParameterHandler.checkIncreaseTemperatureWithHeatEffect(player), HttpStatus.OK);
		}
		globalParameterHandler.increaseTemperature(true, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<PayOption>(HttpStatus.OK);
	}
	
	@PutMapping("/action/trade/{colony}")
	public ResponseEntity<List<PayOption>> trade(@PathVariable String colony, @RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<List<PayOption>>(coloniesHandler.checkTradeEffect(player, colony), HttpStatus.OK);
		}
		coloniesHandler.trade(player, colony);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<List<PayOption>>(HttpStatus.OK);
	}
	
	@PutMapping("/action/buyDelegate/{party}")
	public ResponseEntity<List<PayOption>> buyDelegate(@PathVariable String party, @RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<List<PayOption>>(turmoilHandler.checkBuyDelegateEffect(player), HttpStatus.OK);
		}
		turmoilHandler.buyDelegate(party, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<List<PayOption>>(HttpStatus.OK);
	}
	
	@PutMapping("/action/standardProject/{projectName}")
	public ResponseEntity<PayOption> standardProject(@PathVariable String projectName, @RequestBody Player player) {
		if(player.getPayingWith() == null) {
			return new ResponseEntity<PayOption>(standardProjectHandler.checkStandardProjectEffect(projectName, player), HttpStatus.OK);
		}
		standardProjectHandler.standardProject(projectName, player);
		sendEvent(UPDATE_ALL_PLAYER, "allPlayerData", playersHandler.getPlayers());
		sendEvent(UPDATE_TABLE, "tableData", TerraformingMarsHandler.getGameData());
		return new ResponseEntity<PayOption>(HttpStatus.OK);
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