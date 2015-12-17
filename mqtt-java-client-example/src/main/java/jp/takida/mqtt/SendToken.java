/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.takida.mqtt;

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
public class SendToken implements MqttCallback {
    
    private MqttClient client;
    private MqttConnectOptions options;
    
   public void sendToken(String token, int id){
   
        try {
            //MqttClient client = new MqttClient("tcp://localhost:1883", "pahomqttpublish19"  + threadId);
            client = new MqttClient(Param.address, "tokensender"+id);
            //client = new MqttClient("tcp://localhost:1883", "pahomqttpublish" + threadId);
            client.setCallback(this);

            options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("password".toCharArray());
            //options.setWill("pahodemo/test", "crashed".getBytes(), 2, true);

            client.connect(options);
            
            client.publish(Param.topic, token.getBytes(), 0, false);
            // client.publish("impress/demo0", token.getBytes(), 1, true);

        } catch (MqttException e) {
            //e.printStackTrace();
            Logger.getLogger(SendToken.class.getName()).log(Level.SEVERE, null, e);
        }
   }

    @Override
    public void connectionLost(Throwable cause) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
