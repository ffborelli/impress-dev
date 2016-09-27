package br.ufabc.impress.mqtt;

import java.util.concurrent.BlockingQueue;

public class ProcessMqttQueue implements Runnable {
	private final BlockingQueue<String> queue;
	private String name;

	public String getNome() {
		return name;
	}

	public ProcessMqttQueue(BlockingQueue<String> q, String name) {
		this.queue = q;
		this.name = name;
	}

	public void run() {
		// Thread.currentThread().setPriority(2);
		while (true) {
			if (queue.isEmpty() == false) {
				try {
					String message = queue.take();
					System.out.println("Running " + name + " - " + message);
					
					SplitMessageMqtt smm = new SplitMessageMqtt(message);
					smm.applyProtocolsRules();
					
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}
}