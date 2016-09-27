//package br.ufabc.impress.mqtt;
//
//import java.util.concurrent.BlockingQueue;
//
//class MessageSubscribe implements Runnable {
//	
//	private final BlockingQueue<String> queue;
//
//	MessageSubscribe(BlockingQueue<String> q) {
//		queue = q;
//	}
//
//	public void run() {
//		Thread.currentThread().setPriority(10);
//		try {
//			for (int i = 0; i <= 30; i++) {
//				System.out.println("Adicionando tarefa " + i);
//				queue.put("tarefa " + i);
//				Thread.sleep(100);
//			}
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
//	}
//}