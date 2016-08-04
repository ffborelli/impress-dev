package br.ufabc.impress.rai;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.mqtt.MqttSubscribe;

public class InteractionRAI {
	
	
	public void connectRAI(){
		
		ResourceRAI resourceRAI = new ResourceRAI();

		 resourceRAI.setTopic("/impress/observation/iotentity/be8e2ef4-72e9-38b2-a5b2-cb13ab0de545");
		 resourceRAI.setType("Thermometer");
		 resourceRAI.setUid("be8e2ef4-72e9-38b2-a5b2-cb13ab0de545");
		 Main.resourceRAIarr.add(resourceRAI);
		
		 resourceRAI = new ResourceRAI();
		 resourceRAI.setTopic("/impress/observation/iotentity/8b5cff92-0a0a-3176-94d8-3723e7c2babc");
		 resourceRAI.setType("Thermometer");
		 resourceRAI.setUid("8b5cff92-0a0a-3176-94d8-3723e7c2babc");
		 Main.resourceRAIarr.add(resourceRAI);

		// resourceRAI = new ResourceRAI();
		// resourceRAI.setTopic("/impress/observation/iotentity/8b5cff92-0a0a-3176-94d8-3723e7c2babc");
		// resourceRAI.setType("OccupancySensor");
		// resourceRAI.setUid("c377563b-31a8-3881-909d-34066f46dba9");
		// resourceRAIarr.add(resourceRAI);

		 resourceRAI = new ResourceRAI();
		 resourceRAI.setTopic("/impress/observation/iotentity/6a38e67f-c9fb-3b2c-8428-070612ef4caf");
		 resourceRAI.setType("KinectSensor,OccupancySensor");
		 resourceRAI.setUid("6a38e67f-c9fb-3b2c-8428-070612ef4caf");
		 Main.resourceRAIarr.add(resourceRAI);
		 
		 resourceRAI = new ResourceRAI();
		 resourceRAI.setTopic("/impress/observation/iotentity/9a6bde10-789a-3b27-bc7f-5fce9ff7dcb5");
		 resourceRAI.setType("KinectSensor,OccupancySensor");
		 resourceRAI.setUid("9a6bde10-789a-3b27-bc7f-5fce9ff7dcb5");
		 Main.resourceRAIarr.add(resourceRAI);
		
		// resourceRAI = new ResourceRAI();
		// resourceRAI.setTopic("/impress/observation/iotentity/0021b7d9-c33a-3d60-8dd6-c181a9b35aae");
		// resourceRAI.setType("SmartPlug");
		// resourceRAI.setUid("0021b7d9-c33a-3d60-8dd6-c181a9b35aae");
		// resourceRAIarr.add(resourceRAI);
		//

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

			MqttSubscribe ms = new MqttSubscribe(Param.address_rai_mqtt,
					"impress" + i, Main.resourceRAIarr.get(i).getTopic());
			Thread t = new Thread(ms);
			t.start();
		}
	}
	
	// temperature sensor
	// /impress/observation/iotentity/8b5cff92-0a0a-3176-94d8-3723e7c2babc
	// MqttSubscribe ms = new MqttSubscribe(Param.address_rai_mqtt,
	// "impress",
	// "/impress/observation/iotentity/be8e2ef4-72e9-38b2-a5b2-cb13ab0de545");
	// Thread t = new Thread(ms);
	// t.start();
	
	//ContextUtil.clean();

}
