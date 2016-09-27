package br.ufabc.impress.drools;

import java.util.ArrayList;

public class SenderDrools {
	
	private ArrayList<String> messages = new ArrayList<String>();
	
	public void addMessage(String m){
		this.messages.add(m);
	}
	
	public void removeMessage(int index){
		this.messages.remove(index);
		//this.messages.set(index, "-1");
	}

	public ArrayList<String> getMessages() {
		return messages;
	}
	
}
