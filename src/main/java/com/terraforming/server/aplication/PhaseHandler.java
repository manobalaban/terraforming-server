package com.terraforming.server.aplication;

import com.terraforming.server.constans.GamePhase;

public class PhaseHandler {
	private static PhaseHandler instance = null;
	private static GamePhase actualPhase;
	
	private PhaseHandler() {}
	
	public static PhaseHandler getIntance() {
		if(instance == null) {
			actualPhase = GamePhase.ENTER;
			instance = new PhaseHandler();
		}
		return instance;
	}

	public GamePhase getActualPhase() {
		return actualPhase;
	}
	
	public GamePhase nextPhase() {
		switch (actualPhase) {
		case ENTER:
			actualPhase = GamePhase.CHOSE_CORP;
			break;
		case CHOSE_CORP:
			actualPhase = GamePhase.FIRST_RESEARCH;
			break;
		case FIRST_RESEARCH:
			actualPhase = GamePhase.ACTION;
			break;
		case ACTION:
			actualPhase = GamePhase.PRODUCTION;
			break;
		case PRODUCTION:
			actualPhase = GamePhase.TURMOIL;
			break;
		case TURMOIL:
			actualPhase = GamePhase.RESEARCH;
			break;
		case RESEARCH:
			actualPhase = GamePhase.ACTION;
			break;
		default:
			break;
		}
		return actualPhase;
	}

}
