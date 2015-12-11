package br.ufabc.impress;

import java.sql.Timestamp;
import java.util.Date;

import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.mqtt.MqttPublish;
import br.ufabc.impress.mqtt.MqttSubscribe;
import br.ufabc.impress.mqtt.MqttSubscribe2;

public class Main {
	public static long startTime;
	public static long finishTime;

	public static void main(String[] args) {

		ResourceFacade resourceFacade = new ResourceFacade();
		Resource res = resourceFacade.find(Param.sensor_android);

		ResourceLog lr = new ResourceLog();
		lr.setResourceLogValue("2");
		lr.setCreationDate(new Timestamp(new Date().getTime()));

		// Resource res = new Resource();
		// res.setId(Param.sensor_android);
		lr.setResource(res);

		ResourceLogFacade facade = new ResourceLogFacade();
		facade.create(lr);

		for (int i = 0; i < Param.number_of_topics; i++) {
			// subscribe mqtt
			MqttSubscribe ms = new MqttSubscribe(Param.address, "impress" + i, Param.topic + i);
			
			Thread t = new Thread(ms);
			t.start();
		}
		// esper		
		Esper.startEsper();
		
//		MqttSubscribe2 ms = new MqttSubscribe2(Param.address, "impress" + 0, Param.topic + 0);
//		ms.subscribe(Param.address, "impress" + 0, Param.topic + 0);

		MqttPublish m = new MqttPublish(Param.address, "id1", Param.topic, "4;2");
		Thread tp = new Thread(m);
		tp.start();
	}
}
