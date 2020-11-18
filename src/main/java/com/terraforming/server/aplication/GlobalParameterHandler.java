package com.terraforming.server.aplication;

import com.terraforming.server.model.Player;

public class GlobalParameterHandler {
	private static GlobalParameterHandler instance = null;
	
	private PlayersHandler playersHandler = PlayersHandler.getInstance();
	
	private static int oceans = 0;
	private static int temperature = -30;
	private static double oxygen = 0;
	private static int venus = 0;
	
	private GlobalParameterHandler() {}
	
	public static GlobalParameterHandler getInstance() {
		if(instance == null) {
			instance = new GlobalParameterHandler();
		}
		return instance;
	}

	public int getOceans() {
		return oceans;
	}

	public int getTemperature() {
		return temperature;
	}

	public double getOxygen() {
		return oxygen;
	}

	public int getVenus() {
		return venus;
	}
	
	public boolean increaseOceans(boolean increase) {
		boolean possible = false;
		if(increase && oceans < 21) {
			oceans++;
			possible = true;
		} else if(!increase && oceans > 0) {
			oceans--;
			possible = true;
		}
		return possible;
	}
	
	public boolean increaseTemperature(boolean increase, Player player) {
		boolean possible = false;
		if(increase && temperature < 9) {
			temperature++;
			if(temperature == -24 || temperature == -20) {
				playersHandler.getPlayer(player.getName()).set
			}
			possible = true;
		} else if(!increase && temperature > -30) {
			temperature--;
			possible = true;
		}
		return possible;
	}
	
	public boolean increaseOxygen(boolean increase) {
		boolean possible = false;
		if(increase && oxygen < 15) {
			oxygen += 0.5;
			possible = true;
		} else if(!increase && oxygen > 0) {
			oxygen -= 0.5;
			possible = true;
		}
		return possible;
	}
	
	public boolean increaseVenus(boolean increase) {
		boolean possible = false;
		if(increase && venus < 30) {
			venus ++;
			possible = true;
		} else if(!increase && venus > 0) {
			venus ++;
			possible = true;
		}
		return possible;
	}
	
}
