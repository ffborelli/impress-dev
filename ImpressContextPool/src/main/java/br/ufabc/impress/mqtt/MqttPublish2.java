package br.ufabc.impress.mqtt;

import java.sql.Timestamp;
import java.util.Random;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import br.ufabc.impress.mqtt.*;

public class MqttPublish2 implements MqttCallback, Runnable {
	private String uri;
	private String clientId;
	private String topic;
	private String messageSend;
	
	private long threadId;

	public MqttPublish2(String uri, String clientId, String topic,
			String messageSend) {
		this.clientId = clientId;
		this.uri = uri;
		this.topic = topic;
		this.messageSend = messageSend;
	}

	@Override
	public void run() {
		threadId = Thread.currentThread().getId();
		this.publish(uri, clientId, topic, messageSend);

	}

	public void publish(String uri, String clientId, String topic,
			String messageSend) {

		MqttClient client = null;
		MqttConnectOptions options = null;
		if (client == null){
			try{
				client = new MqttClient(uri, clientId+threadId);
				client.setCallback(this);

				options = new MqttConnectOptions();
				options.setUserName("admin");
				options.setPassword("password".toCharArray());
				
				// options.setWill("impress/action", "crashed".getBytes(), 2, true);
				
				client.connect(options);
				
			}
			catch(Exception e){
				//Random randomGenerator = new Random();
				//client = new MqttClient(uri + ":" + port, clientId+randomGenerator.nextInt(1000));
				System.out.println("ERROR PUBLISH CLIENT");
			}
		}

		//MqttMessage message = new MqttMessage();
		// message.setPayload("A single message Test".getBytes());
		// client.publish("pahodemo/test", message);

		//message.setPayload(messageSend.getBytes());
		
		try {
			//client.publish(topic, message);
			client.publish(topic, messageSend.getBytes(), 0, false);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getCause().toString() + " " + e.getMessage()
					+ " " + e.getLocalizedMessage() + " "
					+ e.getStackTrace().toString());
		}

//		String time = new Timestamp(System.currentTimeMillis()).toString();
//		System.out.println("MQTT P: message published --> time:\t" + time
//				+ "  topic:\t" + topic + "  message:\t" + messageSend);
//		DemoUtil demoUtil = new DemoUtil();
//		demoUtil.saveLog("MQTT P: message published --> topic:\t" + topic
//				+ "  message:\t" + messageSend);
		try {
			client.disconnect();
		} catch (Exception e) {
			System.out.println(e.getCause().toString() + " " + e.getMessage()
					+ " " + e.getLocalizedMessage() + " "
					+ e.getStackTrace().toString());
		}

	}

	public void connectionLost(Throwable msg) {
		System.out.println(msg.getMessage());
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {

		//System.out.println("MQTT P: message delivered.");
	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
//		String time = new Timestamp(System.currentTimeMillis()).toString();
//		System.out.println("Time:\t" + time + "  Topic:\t" + topic
//				+ "  Message:\t" + new String(message.getPayload())
//				+ "  QoS:\t" + message.getQos());

//		DemoUtil demoUtil = new DemoUtil();
//		demoUtil.saveLog("CallBack:P" + "  Topic:\t" + topic + "  Message:\t"
//				+ new String(message.getPayload()));
	}

}
