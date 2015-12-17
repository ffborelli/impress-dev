package jp.takida.mqtt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribe implements MqttCallback {

    public MqttSubscribe() {
    }

    public static void main(String[] args) {
        new MqttSubscribe().subscribe();
    }

    public void subscribe() {
        try {
            //MqttClient client = new MqttClient("tcp://localhost:61613", "pahomqttpublish1");
            MqttClient client = new MqttClient(Param.address, "pahomqttpublish100");
            client.setCallback(this);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("password".toCharArray());

            client.connect(options);

            client.subscribe(Param.topic);
            try {
                System.in.read();
            } catch (IOException e) {
                // If we can't read we'll just exit
            }

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
            System.out.println("erro: " + e.getMessage().toString());
        }
    }

    public void connectionLost(Throwable msg) {
        System.out.println(msg.getMessage());
    }

    public void deliveryComplete(IMqttDeliveryToken arg0) {
        System.out.println("Delivery completed.");
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String time = new Timestamp(System.currentTimeMillis()).toString();
        System.out.println("Time:\t" + time
                + "  Topic:\t" + topic
                + "  Message:\t" + new String(message.getPayload())
                + "  QoS:\t" + message.getQos());
        String m = new String(message.getPayload());
        if(!"FINISH".equals(m) && !"START".equals(m))
          writeFile(m, time);
        
        
    }

    public void writeFile(String m, String time) throws IOException {
        String[] msg = m.split(";");
        File arquivo = new File(Param.path + ".csv");
        //File arquivo = new File("teste" + ".csv");
        try (FileWriter fw = new FileWriter(arquivo, true);
                BufferedWriter bw = new BufferedWriter(fw)) {
            m =  "\""+ Param.name_experiment +"\""+";"+ "\"" + msg[0] +  "\"" + ";" +"\"" + msg[1] +  "\"" + ";" + "\"" + msg[2] +  "\"" +";"+ "\"" +time + "\"" ; 
            //devNo;msgNo;time
            bw.write(m);
            bw.newLine();
        }
    }
}
