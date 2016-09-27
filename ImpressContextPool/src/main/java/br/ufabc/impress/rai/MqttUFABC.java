package br.ufabc.impress.rai;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.mqtt.MqttSubscribe;

public class MqttUFABC {
	
	public void connect(){
		
	
		ResourceRAI resourceRAI = new ResourceRAI();
		
		resourceRAI = new ResourceRAI();
		resourceRAI.setTopic("/impress/observation/iotentity/1");
		resourceRAI.setType("UltrasonicSensor,OccupancySensor");
		resourceRAI.setUid("1");
		Main.resourceRAIarr.add(resourceRAI);

		resourceRAI = new ResourceRAI();
		resourceRAI.setTopic("/impress/observation/iotentity/2");
		resourceRAI.setType("UltrasonicSensor,OccupancySensor");
		resourceRAI.setUid("2");
		Main.resourceRAIarr.add(resourceRAI);

		resourceRAI = new ResourceRAI();
		resourceRAI.setTopic("/impress/observation/iotentity/3");
		resourceRAI.setType("UltrasonicSensor,OccupancySensor");
		resourceRAI.setUid("3");
		Main.resourceRAIarr.add(resourceRAI);

		resourceRAI = new ResourceRAI();
		resourceRAI.setTopic("/impress/observation/iotentity/4");
		resourceRAI.setType("UltrasonicSensor,OccupancySensor");
		resourceRAI.setUid("4");
		Main.resourceRAIarr.add(resourceRAI);

		for (int i = 0; i < Main.resourceRAIarr.size(); i++) {

			MqttSubscribe ms = new MqttSubscribe(Param.address_ufabc_mqtt,
					"impress" + i, Main.resourceRAIarr.get(i).getTopic());
			Thread t = new Thread(ms);
			t.start();
		}
	}

}
