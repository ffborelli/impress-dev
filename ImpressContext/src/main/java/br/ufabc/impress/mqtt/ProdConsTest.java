package br.ufabc.impress.mqtt;

import org.apache.http.impl.conn.PoolingClientConnectionManager;

import br.ufabc.impress.tracker.TrackerPooling;
import br.ufabc.impress.tracker.TrackerContext;

public class ProdConsTest {
	public static void main(String[] args) {
//		new Runnable() {
//			@Override
//			public void run() {
//				//final int bufferSize = 10;
//				Pilha pilha = new Pilha();
//				Produtor p = new Produtor(pilha, bufferSize);
//				Consumidor c = new Consumidor(pilha);
//				p.init();
//				c.init();
//				while (true) {
//					if (pilha.get() == 0) { // -----------> 2
//						synchronized (p) {
//							System.out
//									.println("************************* Incializa Produção *************************");
//							p.notify();
//						}
//					}
//					//if (pilha.get() == bufferSize) { // -------------> 3
//						synchronized (c) {
//							//System.out.println("************************* Incializa Consumo *************************");
//							c.notify();
//						}
//					//}
//				}
//			}
//		}.run();
		
		//TrackerContext t = new TrackerContext();
		//t.tracker(null);
		
		TrackerPooling p = new TrackerPooling();
		Thread thread= new Thread(p);
		thread.start();
		
	}
}
