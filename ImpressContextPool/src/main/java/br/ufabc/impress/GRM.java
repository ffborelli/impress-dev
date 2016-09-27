package br.ufabc.impress;

import java.io.IOException;
import java.util.Random;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.mqtt.SplitMessageMqtt;

public class GRM implements Runnable, MqttCallback {
	
	private ResourceFacade resourceFacade;

	private String uri;
	private String clientId;
	private String topic;
	
	private MqttClient client;
	private MqttConnectOptions options;

	public GRM(String uri, String clientId, String topic) {
		this.clientId = clientId;
		this.uri = uri;
		this.topic = topic;
	}

	@Override
	public void run() {

		this.subscribe(uri, clientId, topic);
	}

	private void subscribe(String uri, String clientId, String topic) {

		System.out.println("Subscribing topic " + topic);

		try {

			Random random = new Random();
			String id = clientId+random.nextInt(2000);
			client = new MqttClient(uri, id);
			client.setCallback(this);

			options = new MqttConnectOptions();
			options.setUserName("admin");
			options.setPassword("password".toCharArray());
			options.setKeepAliveInterval(1000);
			options.setConnectionTimeout(10000);

			client.connect(options);

			// client.subscribe("impress/temperature");
			client.subscribe(topic);

			try {
				System.in.read();

			} catch (IOException e) {
				// If we can't read we'll just exit
				System.out.println("Error subscribing ....");
				new Throwable("Error subscribing receive message", e);

			}

			client.disconnect();

		} catch (MqttException e) {
			// e.printStackTrace();
			System.out.println("Error subscribing MQTT" + e.getMessage() + " "
					+ e.getLocalizedMessage());
			new Throwable("Error subscribing MQTT", e);
		}

	}

	public void connectionLost(Throwable msg) {
		System.out.println("Connection Lost " + clientId + " "+ msg.getStackTrace().toString());
		//new Throwable("Connection Lost", msg);
		this.subscribe(uri, clientId, topic);
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
//		try {
//			System.out.println("MQTT S: message delivered -->"
//					+ arg0.getMessage().toString());
////			DemoUtil demoUtil = new DemoUtil();
////			demoUtil.saveLog("MQTT S: message delivered -->"
////					+ arg0.getMessage().toString());
//		} catch (MqttException e) {
//			// TODO Auto-generated catch block
//			// e.printStac, int portkTrace();
//			System.out.println("Error subscribing MQTT" + e.getMessage() + " "
//					+ e.getLocalizedMessage());
//			new Throwable("Error subscribing MQTT", e);
//		}
	}

	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		
		System.out.println("GRM : message arrived --> topic:\t" + topic
				+ "  message:\t" + new String(message.getPayload()));
		
		if (new String(message.getPayload()).equalsIgnoreCase("ON")){
			
			Resource r = this.getResourceFacade().find(7);
			r.setReserved(true);
			this.getResourceFacade().update(r);
			
			r = this.getResourceFacade().find(8);
			r.setReserved(true);
			this.getResourceFacade().update(r);
			
			r = this.getResourceFacade().find(9);
			r.setReserved(true);
			this.getResourceFacade().update(r);
			
			r = this.getResourceFacade().find(10);
			r.setReserved(true);
			this.getResourceFacade().update(r);
			
			
			
		}
		else if (new String(message.getPayload()).equalsIgnoreCase("OFF")) {
			
			Resource r = this.getResourceFacade().find(7);
			r.setReserved(false);
			this.getResourceFacade().update(r);
			
			r = this.getResourceFacade().find(8);
			r.setReserved(false);
			this.getResourceFacade().update(r);
			
			r = this.getResourceFacade().find(9);
			r.setReserved(false);
			this.getResourceFacade().update(r);
			
			r = this.getResourceFacade().find(10);
			r.setReserved(false);
			this.getResourceFacade().update(r);
			
		}
		else{}

	}
	
	private ResourceFacade getResourceFacade(){
		if (resourceFacade == null){
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}

}
