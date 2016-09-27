package br.ufabc.impress.mqtt;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class testeThread {
	public static void main(String[] args) {
		BlockingQueue<String> q = new LinkedBlockingQueue<String>();
		// ExecutorService threadPool = Executors.newFixedThreadPool(3);
		ExecutorService threadPool = Executors.newCachedThreadPool();
		MessageMqtt p = new MessageMqtt(q);
		threadPool.execute(new MessageMqtt(q));
		threadPool.execute(new MessageMqtt(q));
		threadPool.execute(new Consumer(q, "primeira"));
		threadPool.execute(new Consumer(q, "segunda "));
		// threadPool.execute(new Consumer(q, "terceira"));
		// threadPool.execute(new Consumer(q, "quarta  "));
		// threadPool.execute(new Consumer(q, "quinta  "));
		threadPool.shutdown();
	}
}

class MessageMqtt implements Runnable {
	private final BlockingQueue<String> queue;

	MessageMqtt(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		Thread.currentThread().setPriority(10);
		try {
			for (int i = 0; i <= 30; i++) {
				System.out.println("Adicionando tarefa " + i);
				queue.put("tarefa " + i);
				Thread.sleep(100);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	private final BlockingQueue<String> queue;
	private String nome;

	public String getNome() {
		return nome;
	}

	Consumer(BlockingQueue<String> q, String nome) {
		this.queue = q;
		this.nome = nome;
	}

	public void run() {
		// Thread.currentThread().setPriority(2);
		while (true) {
			if (queue.isEmpty() == false) {
				try {
					System.out.println("Executando " + nome + " - "
							+ queue.take());
					Thread.sleep(500); // Simula delay real do consumidor
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		}
		// System.out.println("terminando thread " + nome);
	}
}