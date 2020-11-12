package com.terraforming.server.model;

public class CardAction {
	private String id;
	private boolean done = false;
	
	public CardAction() {}
	
	public CardAction(String id) {
		this.id = id;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getId() {
		return id;
	}
}
