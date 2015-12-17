/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.takida.mqtt;

/**
 *
 * @author fabrizio
 */
import java.util.concurrent.Semaphore;

public class ThreadCounter {

    static int counter;
    private final Semaphore semaforo = new Semaphore(1);

    public int getCounter() {
        return counter;
    }

    public void addCounter() {
        try {
            semaforo.acquire();
            counter += 1;
        } catch (InterruptedException e) {
        } finally {
            semaforo.release();
        }
    }

}
