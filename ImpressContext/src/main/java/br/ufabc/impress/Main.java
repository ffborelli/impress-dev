package br.ufabc.impress;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.esper.EsperPooling;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.mqtt.MqttSubscribe;
import br.ufabc.impress.tracker.TrackerPooling;
import br.ufabc.impress.util.ContextUtil;
import br.ufabc.impress.util.JsonUtil;

public class Main {


	public static ArrayList<ResourceRAI> resourceRAIarr = new ArrayList<ResourceRAI>();

	public static void main(String[] args) {

		// Context Tracker

		if (Param.tracker) {
			TrackerPooling poolingTracker = new TrackerPooling();

			Thread threadPoolingTracker = new Thread(poolingTracker);
			threadPoolingTracker.start();
		}
		
		//reset the buffer os the resource os drools
		new Drools().resetBuffer();
		
		if (Param.esper) {
			EsperPooling esperPooling = new EsperPooling();

			Thread threadEsperPooling = new Thread(esperPooling);
			threadEsperPooling.start();
		}
		//get JSON FROM GRM e histerese
		//we need to open a websocket connection to interact with GRM
		if (Param.grm) {
						
			WebsocketGRM ws = new WebsocketGRM(Param.address_grm);
			// ws.run();
			Thread tws = new Thread(ws);
			tws.start();
			
			ws.closeConnection();
			
			GRM ms = new GRM("tcp://192.168.1.222:1883",
					"impress12234", "/IMPRESS/hacktopic");
			Thread t = new Thread(ms);
			t.start();
		}

		if (Param.rai) {

			ResourceRAI resourceRAI = new ResourceRAI();

			 resourceRAI.setTopic("/impress/observation/iotentity/be8e2ef4-72e9-38b2-a5b2-cb13ab0de545");
			 resourceRAI.setType("Thermometer");
			 resourceRAI.setUid("be8e2ef4-72e9-38b2-a5b2-cb13ab0de545");
			 resourceRAIarr.add(resourceRAI);
			
			 resourceRAI = new ResourceRAI();
			 resourceRAI.setTopic("/impress/observation/iotentity/8b5cff92-0a0a-3176-94d8-3723e7c2babc");
			 resourceRAI.setType("Thermometer");
			 resourceRAI.setUid("8b5cff92-0a0a-3176-94d8-3723e7c2babc");
			 resourceRAIarr.add(resourceRAI);

			// resourceRAI = new ResourceRAI();
			// resourceRAI.setTopic("/impress/observation/iotentity/8b5cff92-0a0a-3176-94d8-3723e7c2babc");
			// resourceRAI.setType("OccupancySensor");
			// resourceRAI.setUid("c377563b-31a8-3881-909d-34066f46dba9");
			// resourceRAIarr.add(resourceRAI);

			 resourceRAI = new ResourceRAI();
			 resourceRAI.setTopic("/impress/observation/iotentity/6a38e67f-c9fb-3b2c-8428-070612ef4caf");
			 resourceRAI.setType("KinectSensor,OccupancySensor");
			 resourceRAI.setUid("6a38e67f-c9fb-3b2c-8428-070612ef4caf");
			 resourceRAIarr.add(resourceRAI);
			 
			 resourceRAI = new ResourceRAI();
			 resourceRAI.setTopic("/impress/observation/iotentity/9a6bde10-789a-3b27-bc7f-5fce9ff7dcb5");
			 resourceRAI.setType("KinectSensor,OccupancySensor");
			 resourceRAI.setUid("9a6bde10-789a-3b27-bc7f-5fce9ff7dcb5");
			 resourceRAIarr.add(resourceRAI);
			
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
			resourceRAIarr.add(resourceRAI);

			resourceRAI = new ResourceRAI();
			resourceRAI.setTopic("/impress/observation/iotentity/2");
			resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			resourceRAI.setUid("2");
			resourceRAIarr.add(resourceRAI);

			 resourceRAI = new ResourceRAI();
			 resourceRAI.setTopic("/impress/observation/iotentity/3");
			 resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			 resourceRAI.setUid("3");
			 resourceRAIarr.add(resourceRAI);

			resourceRAI = new ResourceRAI();
			resourceRAI.setTopic("/impress/observation/iotentity/4");
			resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			resourceRAI.setUid("4");
			resourceRAIarr.add(resourceRAI);

			for (int i = 0; i < resourceRAIarr.size(); i++) {

				MqttSubscribe ms = new MqttSubscribe(Param.address_rai_mqtt,
						"impress" + i, resourceRAIarr.get(i).getTopic());
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

		if (Param.mqtt_ufabc){
			
			ResourceRAI resourceRAI = new ResourceRAI();
			
			resourceRAI = new ResourceRAI();
			resourceRAI.setTopic("/impress/observation/iotentity/1");
			resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			resourceRAI.setUid("1");
			resourceRAIarr.add(resourceRAI);

			resourceRAI = new ResourceRAI();
			resourceRAI.setTopic("/impress/observation/iotentity/2");
			resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			resourceRAI.setUid("2");
			resourceRAIarr.add(resourceRAI);

			 resourceRAI = new ResourceRAI();
			 resourceRAI.setTopic("/impress/observation/iotentity/3");
			 resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			 resourceRAI.setUid("3");
			 resourceRAIarr.add(resourceRAI);

			resourceRAI = new ResourceRAI();
			resourceRAI.setTopic("/impress/observation/iotentity/4");
			resourceRAI.setType("UltrasonicSensor,OccupancySensor");
			resourceRAI.setUid("4");
			resourceRAIarr.add(resourceRAI);

			for (int i = 0; i < resourceRAIarr.size(); i++) {

				MqttSubscribe ms = new MqttSubscribe(Param.address_ufabc_mqtt,
						"impress" + i, resourceRAIarr.get(i).getTopic());
				Thread t = new Thread(ms);
				t.start();
			}
		}
		
		// esper
		Esper.startEsper();
		//Esper08042016.startEsper();



		// Plugwise
		// 0021b7d9-c33a-3d60-8dd6-c181a9b35aae

		// RestFull rest = new RestFull();
		// try {
		// //http://130.192.86.221:8888/f061b5da-7ddd-3510-95fc-15d61e870f26/authorize_access
		// rest.sendPost();
		// rest.sendGet("http://130.192.86.221:8888/411cf463-e5bb-303d-901b-2287a92fdd63/turnOff");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}

// /impress/observation/iotentity/8f267135-6477-3a59-aa5f-0f28572e261f
// {"About":"8f267135-6477-3a59-aa5f-0f28572e261f","Properties":[{"IoTStateObservation":[{"Value":"false","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getPresence"},{"IoTStateObservation":[{"Value":"0","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getNumberOfPeople"},{"IoTStateObservation":[{"Value":"[]","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getCoordinates"},{"IoTStateObservation":[{"Value":"true","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getIsOn"},{"IoTStateObservation":[{"Value":"0","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getTiltAngle"}]}

// /deviceCategories/consumption/realTime
// {"id":3,"name":"Appliance","location":"Stage","consumption":{"Wh":2218.0,"BRL":0.73194003,"timestamp":"2015-01-01T12:30:00Z"}}

