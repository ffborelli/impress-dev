/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.takida.mqtt;

import java.sql.Timestamp;
import java.util.Random;
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
public class StressEsper implements Runnable, MqttCallback {

    private long threadId;

    private int timeBetweenEvents;

    private int numberOfEvents;

    private Random randon = new Random();

    private MqttClient client;

    private MqttConnectOptions options;

    public StressEsper(int timeBetweenEvents, int numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
        this.timeBetweenEvents = timeBetweenEvents;
    }

    @Override
    public void run() {
        threadId = Thread.currentThread().getId();
        System.out.println(threadId);
        //Logger.getLogger().debug("Thread # " + threadId + " is doing this task");
        this.publish();
    }

    private void connectMQTT() {
        try {
            //MqttClient client = new MqttClient("tcp://localhost:1883", "pahomqttpublish19"  + threadId);
            //client = new MqttClient("tcp://impressufabc.cloudapp.net:1883", "pahomqttpublish" + threadId);
            client = new MqttClient("tcp://localhost:1883", "pahomqttpublish" + threadId);
            client.setCallback(this);

            options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("password".toCharArray());
            //options.setWill("pahodemo/test", "crashed".getBytes(), 2, true);

            client.connect(options);

        } catch (MqttException e) {
            //e.printStackTrace();
            Logger.getLogger(StressEsper.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void finishConnection() {
    }

    public void publish() {

        if (client == null) {
            this.connectMQTT();
        }

        for (int i = 0; i < numberOfEvents; i++) {

            String m = null;

//            int idResource = (randon.nextInt(100) % 2) + 1 ;
//            m = idResource + ";" + String.valueOf(randon.nextInt(40));
            randon.setSeed(System.nanoTime() + threadId );
            int idResource = randon.nextInt(17) ;
            boolean check = true;

            while (check) {
                if (idResource == 5 || idResource == 6) {
                    m = "1;" + idResource + ";" + String.valueOf(randon.nextInt(30));
                    check = false;
                } else if (idResource == 13) {
                    m = "1;" + idResource + ";" + String.valueOf(randon.nextInt(1));
                    check = false;
                } else if (idResource == 16) {
                    m = "1;" + idResource + ";" + String.valueOf(randon.nextInt(8) + 1);
                    check = false;
                }
                else{
                    idResource = randon.nextInt(17) ;
                }
            }

            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());
            try {
                if (client == null) {
                    this.connectMQTT();
                }
                //client.publish("impress/temperature", message);
                client.publish("impress/demo", m.getBytes(), 0, false);
                //client.publish("impress/demo", ("1;5;"+ randon.nextInt(50)).getBytes(), 0, false);
                // client.publish("impress/demo", ("1;1;1").getBytes(), 0, false);
            } catch (MqttException ex) {
                Logger.getLogger(StressEsper.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(timeBetweenEvents);
            } catch (InterruptedException ex) {
                Logger.getLogger(MqttPublish.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        try {
            //client.subscribe("impress/temperature");
            client.disconnect();
        } catch (MqttException ex) {
            Logger.getLogger(StressEsper.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
