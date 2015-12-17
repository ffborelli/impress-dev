package jp.takida.mqtt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

public class Modelling implements Runnable, MqttCallback {

    private final int threadId;

    private final int timeBetweenEvents;

    private final int numberOfEvents;

    private final Random random = new Random();

    private MqttClient client;

    private final int idSensor;

    private MqttConnectOptions options;

    private final CountDownLatch latch;
    ThreadCounter counter = new ThreadCounter();

    // public Modelling(int timeBetweenEvents, int numberOfEvents, CountDownLatch latch) {
    public Modelling(int TimeBetweenEvents, int idSensor, int threadId, int numberOfEvents, CountDownLatch latch) {
        this.numberOfEvents = numberOfEvents;
        this.timeBetweenEvents = TimeBetweenEvents;
        this.threadId = threadId;
        this.latch = latch;
        this.idSensor = idSensor;

    }

    @Override
    public void run() {
        System.out.println("Thread id: " + threadId + " Sensor tipo: " + idSensor);
        random.setSeed(threadId);
        try {
            this.publish();
        } catch (IOException ex) {
            Logger.getLogger(Modelling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getMessageFromSensor(int idResource) {

        String m;
        m = null;
        //temperature
        if (idResource == 5 || idResource == 6) {
            m = "1;" + idResource + ";" + String.valueOf(random.nextInt(50));
            //r = idResource;
        } //android
        else if (idResource == 13) {
            m = "4;" + String.valueOf(random.nextInt(3));

        } //presence
        else if (idResource == 16) {
            m = "1;" + idResource + ";" + String.valueOf(random.nextInt(16));

        } else {
        }

        return m;

    }

    private void connectMQTT() {
        try {
            client = new MqttClient(Param.address, "DeviceNo_" + threadId);
            client.setCallback(this);
            options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("password".toCharArray());
            client.connect(options);
        } catch (MqttException e) {
            Logger.getLogger(Modelling.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void writeFile(String m, String time) throws IOException {
        String[] msg = m.split(";");
        File arquivo = new File(Param.path + ".csv");
        //File arquivo = new File("teste" + ".csv");
        try (FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            m = "\""+ Param.name_experiment +"\""+";"+"\"" + msg[0] + "\"" + ";" + "\"" + msg[1] + "\"" + ";" + "\"" + msg[2] + "\"" + ";" + "\"" + time + "\"";
            //devNo;msgNo;time
            bw.write(m);
            bw.newLine();
        }
    }
    public void publish() throws IOException {

        if (client == null ) {
            this.connectMQTT();
        }

        for (int i = 0; i < numberOfEvents; i++) {

            String m = this.getMessageFromSensor(idSensor);
            MqttMessage message = new MqttMessage();
            message.setPayload(m.getBytes());

            try {
                if (client == null) {
                    this.connectMQTT();
                }
                String time = new Timestamp(System.currentTimeMillis()).toString();
                client.publish(Param.topic /*+ random.nextInt(Param.number_of_topics)*/, m.getBytes(), Param.qos, false);
                writeFile(m, time);
                counter.addCounter();

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
        try {
            token.sendToken("FINISH", (int) threadId);
            client.disconnect();
        } catch (MqttException ex) {
            Logger.getLogger(Modelling.class.getName()).log(Level.SEVERE, null, ex);
        }

        latch.countDown();
        System.out.println("Contador: " + counter.getCounter());

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
        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println("Time:\t" + time
                + "  Topic:\t" + topic
                + "  Message:\t" + new String(message.getPayload())
                + "  QoS:\t" + message.getQos());

    }

}
