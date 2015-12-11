/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.takida.mqtt;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author fabrizio
 */
public class Modelling implements Runnable, MqttCallback {

    private long threadId;

    private int timeBetweenEvents;

    private int numberOfEvents;

    private Random randon = new Random();

    private MqttClient client;

    private MqttConnectOptions options;
    
    private int idSensor;
    
    private final CountDownLatch latch;

    ThreadCounter counter = new ThreadCounter();
    
    //private static int num = 0;
    
    public Modelling(int timeBetweenEvents, int numberOfEvents, int idSensor, CountDownLatch latch) {
        
        //randon.setSeed(System.currentTimeMillis());
        this.numberOfEvents = numberOfEvents;
        this.timeBetweenEvents = timeBetweenEvents;
        //timeBetweenEvents = randon.nextInt(3000);
        this.idSensor = idSensor;
        this.latch = latch;

    }
    
    private int getTimeBetweenEvents(){
    
        boolean check = true;
        int time = 0;
        
        do{
            
            time = randon.nextInt(3500);
            
            if (time > 1500){
                check = false;
            }
            
        }while(check);
        
        return time;
    }

    @Override
    public void run() {
        threadId = Thread.currentThread().getId();
        //System.out.println(threadId);
        randon.setSeed(threadId);
        //Logger.getLogger().debug("Thread # " + threadId + " is doing this task");
        //timeBetweenEvents = this.getTimeBetweenEvents();
        //idSensor = this.getIdSensor();
        this.publish();
    }

    private void connectMQTT() {
        try {
            //MqttClient client = new MqttClient("tcp://localhost:1883", "pahomqttpublish19"  + threadId);
            client = new MqttClient(Param.address, "pahomqttpublish" + threadId);
            //client = new MqttClient("tcp://localhost:1883", "pahomqttpublish" + threadId);
            client.setCallback(this);

            options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("password".toCharArray());
            //options.setWill("pahodemo/test", "crashed".getBytes(), 2, true);

            client.connect(options);

        } catch (MqttException e) {
            //e.printStackTrace();
            Logger.getLogger(Modelling.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void finishConnection() {
    }
    
    private int getIdSensor(){
    
        int idResource = randon.nextInt(17);
        boolean check = true;
        int r = 0;

        while (check) {
            if (idResource == 5 || idResource == 6 || idResource == 13 || idResource == 16) {
                r = idResource;
                check = false;
            }
            else{
                idResource = randon.nextInt(17);
            }
        }

        return r;
    }

    private String getMessageFromSensor(int idResource) {

        String m = null;
        //temperature
        if (idResource == 5 || idResource == 6) {
            m = "1;" + idResource + ";" + String.valueOf(randon.nextInt(50));
                    //r = idResource;
        } 
        //android
        else if (idResource == 13) {
            m = "4;" + String.valueOf(randon.nextInt(3));

        } 
        //presence
        else if (idResource == 16) {
            m = "1;" + idResource + ";" + String.valueOf(randon.nextInt(16));

        } 
        else {
        }

        return m;

    }

    public void publish() {

        if (client == null || !client.isConnected()) {
            this.connectMQTT();
        }

        for (int i = 0; i < numberOfEvents; i++) {

            String m = this.getMessageFromSensor(idSensor);

//            int idResource = (randon.nextInt(100) % 2) + 1 ;
//            m = idResource + ";" + String.valueOf(randon.nextInt(40));
            //randon.setSeed(System.nanoTime() + threadId );


            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());
            try {
                if (client == null || !client.isConnected()) {
                    this.connectMQTT();
                }
                
                //client.publish("impress/temperature", message);
                
                if (client.isConnected()){
                
                    client.publish("impress/demo" + randon.nextInt(Param.number_of_topics), m.getBytes(), 0, false);
                    //System.out.println(num++);
                    counter.addCounter();
                    //client.publish("impress/demo", ("1;5;"+ randon.nextInt(50)).getBytes(), 0, false);
                    // client.publish("impress/demo", ("1;1;1").getBytes(), 0, false);
                }
            } catch (MqttException ex) {
                Logger.getLogger(Modelling.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(timeBetweenEvents);
            } catch (InterruptedException ex) {
                Logger.getLogger(MqttPublish.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        SendToken token = new SendToken();
        token.setId((int)threadId);
        token.setToken("FINISH");
        token.setClient(client);
        token.sendToken();

        latch.countDown(); 
        
        System.out.println(counter.getCounter());
        
                try {
            //client.subscribe("impress/temperature");
            
            
            client.disconnect();
            client.close();
        } catch (MqttException ex) {
            Logger.getLogger(Modelling.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void connectionLost(Throwable msg) {
        //System.out.println(msg.getMessage());
    }

    public void deliveryComplete(IMqttDeliveryToken arg0) {
//        try {
//            if (arg0.getMessage() != null)
//                System.out.println("Thread " + threadId +" -- Delivery completed. --> " + arg0.getMessage().toString());
//            //System.nanoTime();
//            
//        } catch (MqttException ex) {
//            Logger.getLogger(MqttPublish.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
//        String time = new Timestamp(System.currentTimeMillis()).toString();
//        System.out.println("Time:\t" + time
//                + "  Topic:\t" + topic
//                + "  Message:\t" + new String(message.getPayload())
//                + "  QoS:\t" + message.getQos());
    }

}