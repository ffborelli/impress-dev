/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.takida.mqtt;

import java.util.ArrayList;
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
public class SBRC implements Runnable, MqttCallback {

    private long threadId;

    private int timeBetweenEvents;

    private int numberOfEvents;

    private Random randon = new Random();

    private MqttClient client;

    private MqttConnectOptions options;
    
    private int idSensor;
    
    private final CountDownLatch latch;

    public SBRC(int numberOfEvents, CountDownLatch latch) {
        
        //randon.setSeed(System.currentTimeMillis());
        this.numberOfEvents = numberOfEvents;
        //this.timeBetweenEvents = timeBetweenEvents;
        //timeBetweenEvents = randon.nextInt(3000);
        
         this.latch = latch;

    }


    @Override
    public void run() {
        threadId = Thread.currentThread().getId();
        System.out.println(threadId);
        randon.setSeed(threadId);
        //Logger.getLogger().debug("Thread # " + threadId + " is doing this task");
        timeBetweenEvents = 1000;
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
            Logger.getLogger(SBRC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void finishConnection() {
    }
    
   
    public void publish() {

        if (client == null) {
            this.connectMQTT();
        }
        
        ArrayList<String> arr = this.getMessages();
        
        String m = null;
        
        for (int i = 0; i < arr.size(); i++) {

            m = arr.get(i);

            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());
            try {
                if (client == null) {
                    this.connectMQTT();
                }
                
                //client.publish("impress/temperature", message);
                client.publish("impress/demo" + randon.nextInt(Param.number_of_topics), m.getBytes(), 0, false);
                //client.publish("impress/demo", ("1;5;"+ randon.nextInt(50)).getBytes(), 0, false);
                // client.publish("impress/demo", ("1;1;1").getBytes(), 0, false);
            } catch (MqttException ex) {
                Logger.getLogger(SBRC.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(timeBetweenEvents);
            } catch (InterruptedException ex) {
                Logger.getLogger(MqttPublish.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        SendToken s = new SendToken();
        try {
            //client.subscribe("impress/temperature");
            s.sendToken("FINISH" , (int)threadId);
            client.disconnect();
        } catch (MqttException ex) {
            Logger.getLogger(SBRC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        latch.countDown(); 
    }

    public void connectionLost(Throwable msg) {
        //System.out.println(msg.getMessage());
    }

    public void deliveryComplete(IMqttDeliveryToken arg0) {
        //try {
            //System.out.println("Thread " + threadId +" -- Delivery completed. --> " + arg0.getMessage().toString());
            //System.nanoTime();
            
        //} catch (MqttException ex) {
            //Logger.getLogger(MqttPublish.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
//        String time = new Timestamp(System.currentTimeMillis()).toString();
//        System.out.println("Time:\t" + time
//                + "  Topic:\t" + topic
//                + "  Message:\t" + new String(message.getPayload())
//                + "  QoS:\t" + message.getQos());
    }
    
    private ArrayList<String> getMessages(){
        
        ArrayList<String> arr = new ArrayList<>();

        //TURN ON 1 ROW
        arr.add("1;16;1");
        arr.add("1;16;1");
        arr.add("1;16;1");
        arr.add("1;16;1");
        arr.add("1;16;1");
        arr.add("1;16;1");
        arr.add("1;16;1");
        arr.add("1;16;1");
        
        //AVERAGE TEMPERATURE
        
        arr.add("1;5;30");
        arr.add("1;6;34");
        arr.add("1;6;36");
        
        return arr;
        
    }

}
