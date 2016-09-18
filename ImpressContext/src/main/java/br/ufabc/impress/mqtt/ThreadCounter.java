package br.ufabc.impress.mqtt;

import java.util.concurrent.Semaphore;

public class ThreadCounter {

	static int counter;
	private Semaphore semaforo = new Semaphore(1);

	
	static int counter_in;
	private Semaphore semaforo_in = new Semaphore(1);
	
	public int getCounter() {
		return counter;
	}
//
//	public static void setCounter(int counter) {
//		counter = counter;
//	}
	
	public void addCounter(){
	
		try {
	        semaforo.acquire();
	        counter +=1;
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        semaforo.release();
	    }
	}
	
	public int getCounterIn() {
		return counter_in;
	}
//
//	public static void setCounter(int counter) {
//		counter = counter;
//	}
	
	public void addCounterIn(){
	
		try {
	        semaforo_in.acquire();
	        counter_in +=1;
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        semaforo_in.release();
	    }
	}

}
