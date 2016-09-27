package br.ufabc.impress.rai;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceTypeFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.model.ResourceType;
import br.ufabc.impress.mqtt.MqttPublish;
import br.ufabc.impress.mqtt.ProcessMqttQueue;
import br.ufabc.impress.mqtt.MqttSubscribe;
import br.ufabc.impress.mqtt.MqttPublishModel;

public class MqttIvan {

	ResourceFacade resourceFacade;
	ResourceTypeFacade resourceTypeFacade;
	private BlockingQueue<String> messageMqttSubscribeQueue = new LinkedBlockingQueue<String>();
	private static BlockingQueue<MqttPublishModel> messageMqttPublishQueue = new LinkedBlockingQueue<MqttPublishModel>();
	
	public static boolean putMessageMqttPublish(MqttPublishModel m){
		try {
			messageMqttPublishQueue.put(m);
			return true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static MqttPublishModel takeTaskPublishMqtt() {
		MqttPublishModel r = null;
		try {
			r = messageMqttPublishQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public void connect() {

		//BlockingQueue<String> messageMqttQueue = new LinkedBlockingQueue<String>();
		ExecutorService threadPool = Executors.newFixedThreadPool(60);
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		//MessageMqtt p = new MessageMqtt(messageMqttQueue);
		//threadPool.execute(new MessageMqtt(messageMqttQueue));
		//threadPool.execute(new MessageMqtt(messageMqttQueue));

		for (int i = 0; i < 3; i++){
			threadPool.execute(new ProcessMqttQueue(messageMqttSubscribeQueue, "PROCESS"+String.valueOf(i)));
		}
		
		//List<Resource> list = this.getResourceFacade().listAll();
		List<ResourceType> list = this.getResourceTypeFacade().listAll();
		//System.out.println("List Size -->" + list.size());
		for (int i = 0; i < list.size(); i++) {

//				MqttSubscribe ms = new MqttSubscribe("tcp://localhost:1883", "impress" + i, "/ivan/" +list.get(i).getId(),messageMqttQueue);
//				Thread t = new Thread(ms);
//				t.start();
			
			threadPool.execute(new MqttSubscribe("tcp://localhost:1883", "impress" + i, "/ivan/" +list.get(i).getId(),messageMqttSubscribeQueue));
			threadPool.execute(new MqttPublish("PUBLISH"+i,messageMqttPublishQueue));
		}
		
		threadPool.execute(new MqttSubscribe("tcp://localhost:1883", "impressMax", "/ivan/demo",messageMqttSubscribeQueue));


	}

	private ResourceFacade getResourceFacade() {

		if (resourceFacade == null) {
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}
	private ResourceTypeFacade getResourceTypeFacade() {

		if (resourceTypeFacade == null) {
			resourceTypeFacade = new ResourceTypeFacade();
		}
		return resourceTypeFacade;
	}

}
