package br.ufabc.impress.drools;

import java.util.ArrayList;

public class SenderDrools {
	
	private ArrayList<String> messages = new ArrayList<String>();;
	
	public void addMessage(String m){
		this.messages.add(m);
	}

	public ArrayList<String> getMessages() {
		return messages;
	}
}
