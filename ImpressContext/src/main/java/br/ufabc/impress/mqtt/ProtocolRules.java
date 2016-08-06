package br.ufabc.impress.mqtt;

import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.RestFull;
import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.util.NumberUtil;

/*
 * 
 * Describe the protocol to convert messages in the SDP
 * */
public class ProtocolRules implements Runnable {

	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	
	public static final String del = ";";

	public ProtocolRules(String message) {
		this.message = message;
	}

	private String message;

	@Override
	public void run() {
		this.applyProtocolsRules();
	}

	private boolean applyProtocolsRules() {

		if (Param.rai){
			
		JSONObject obj = null;
		JSONObject jsonObject = null;
		try {
			obj = new JSONObject(this.message);

			jsonObject = (JSONObject) obj;
			String uid = jsonObject.getString("About");

			// search for the type of sensor

			for (int i = 0; i < Main.resourceRAIarr.size(); i++) {

				if (Main.resourceRAIarr.get(i).getType()
						.equalsIgnoreCase("Thermometer")
						&& Main.resourceRAIarr.get(i).getUid()
								.equalsIgnoreCase(uid)) {

					// JSONObject innerObject =
					// jsonObject.getJSONObject("Properties");
					JSONArray jsonArray = jsonObject.getJSONArray("Properties");

					JSONObject objectInArray = jsonArray.getJSONObject(0);

					JSONArray IoTStateObservationArray = objectInArray
							.getJSONArray("IoTStateObservation");

					JSONObject objectInArrayValue = IoTStateObservationArray
							.getJSONObject(0);
					String value = (String) objectInArrayValue.get("Value");
					
					ResourceLog lr = new ResourceLog();
					lr.setResourceLogValue(value);
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					//Resource res = new Resource();
					//res.setUid(uid);
					
					Resource res = this.getResourceFacade().findByUid(uid);
					
					lr.setResource(res);

					Esper.addEventEsper(lr);

					System.out.println("Thermometer " + uid + " " + value);
					
					this.getResourceLogFacade().create(lr);
				//it is a kinect
				} else if (Main.resourceRAIarr.get(i).getType()
						.equalsIgnoreCase("KinectSensor,OccupancySensor")
						&& Main.resourceRAIarr.get(i).getUid()
								.equalsIgnoreCase(uid)) {

					// JSONObject innerObject =
					// jsonObject.getJSONObject("Properties");
					JSONArray jsonArray = jsonObject.getJSONArray("Properties");

					
					//Value of presence
					JSONObject objectInArray = jsonArray.getJSONObject(0);

					JSONArray IoTStateObservationArray = objectInArray
							.getJSONArray("IoTStateObservation");
					
					JSONObject objectInArrayValue = IoTStateObservationArray
								.getJSONObject(0);

					String a = (String) objectInArrayValue.get("Value");
					System.out.println("Kinect " + uid + " Presence " + a);
					
				
					//Number of people
					objectInArray = jsonArray.getJSONObject(1);

					IoTStateObservationArray = objectInArray
							.getJSONArray("IoTStateObservation");

					objectInArrayValue = IoTStateObservationArray
								.getJSONObject(0);

					String number_of_peaple = (String) objectInArrayValue.get("Value");
					System.out.println("Kinect " + uid + " Number of peaple " + number_of_peaple);
					
					
					//Coordinates
					objectInArray = jsonArray.getJSONObject(2);

					IoTStateObservationArray = objectInArray
							.getJSONArray("IoTStateObservation");
					
					objectInArrayValue = IoTStateObservationArray
								.getJSONObject(0);

					a = (String) objectInArrayValue.get("Value");
					System.out.println("Kinect " + uid + " Coordinates " + a);
					
					//isOn
					objectInArray = jsonArray.getJSONObject(3);

					IoTStateObservationArray = objectInArray
							.getJSONArray("IoTStateObservation");
					
					objectInArrayValue = IoTStateObservationArray
								.getJSONObject(0);

					a = (String) objectInArrayValue.get("Value");
					System.out.println("Kinect " + uid + " isON " + a);
					
					//angle
					objectInArray = jsonArray.getJSONObject(4);

					IoTStateObservationArray = objectInArray
							.getJSONArray("IoTStateObservation");
						
					objectInArrayValue = IoTStateObservationArray
								.getJSONObject(0);

					a = (String) objectInArrayValue.get("Value");
					System.out.println("Kinect " + uid + " Angle " + a);
					
					ResourceLog lr = new ResourceLog();
					lr.setResourceLogValue(number_of_peaple);
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					//Resource res = new Resource();
					//res.setUid(uid);
					
					Resource res = this.getResourceFacade().findByUid(uid);
					
					lr.setResource(res);

					//Esper.addEventEsper(lr);
					
					this.getResourceLogFacade().create(lr);
				}
				
				 else if (Main.resourceRAIarr.get(i).getType()
							.equalsIgnoreCase("UltrasonicSensor,OccupancySensor")
							&& Main.resourceRAIarr.get(i).getUid()
									.equalsIgnoreCase(uid)) {
					 
					String distance = jsonObject.getString("value");
					String id = jsonObject.getString("id");
					System.out.println("US " + id + " D: " + distance);
					//threshold
					
					//distance = (Integer.parseInt(distance) < Param.threshold_distance) ? "1":"0";
					
					if (Integer.parseInt(uid) == 1){
						distance = (Integer.parseInt(distance) < Param.threshold_distance1) ? "1":"0";
					}
					else if (Integer.parseInt(uid) == 2){
						distance = (Integer.parseInt(distance) < Param.threshold_distance2) ? "1":"0";
					}
					else if (Integer.parseInt(uid) == 3){
						distance = (Integer.parseInt(distance) < Param.threshold_distance3) ? "1":"0";
					}
					else if (Integer.parseInt(uid) == 4){
						distance = (Integer.parseInt(distance) < Param.threshold_distance4) ? "1":"0";
					}
					else{}
					
					//System.out.println("US " + id + " D: " + distance);
					ResourceLog lr = new ResourceLog();
					lr.setResourceLogValue(distance);
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					//Resource res = new Resource();
					//res.setUid(uid);
					//res.setId(Integer.parseInt(id));
					
					Resource res = this.getResourceFacade().findByUid(uid);

					lr.setResource(res);
					
					this.getResourceLogFacade().create(lr);
					
					Esper.addEventEsper(lr);
					
					
				 }
				
				else {

				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// try {
		// // String name = (String) jsonObject.get("application ID");
		// String name = (String) jsonObject.get("Meta");
		// System.out.println(name);
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// String msg = "appID=contextManager&priority=1&securityLevel=none";
		//
		// this.sendMessage(msg);
		
		}
		
		if (Param.mqtt_ufabc){
			
			String m[] = message.split(del);

			NumberUtil nu = new NumberUtil();

			String r = null;
			//if m[0] is a number
			if (nu.isNumeric(m[0])) {
				//change status of demo
				if (m[0].trim().equalsIgnoreCase("4")) {
					
					ResourceLog lr = new ResourceLog();
					lr.setCreationDate(new Timestamp(new Date().getTime()));
					
					if (m[1].trim().equalsIgnoreCase("0")) {
						
						lr.setResourceLogValue("0");

					} else if (m[1].trim().equalsIgnoreCase("1")) {
						// ON
						//r = "ON";
						lr.setResourceLogValue("1");
					
					}

					else if (m[1].trim().equalsIgnoreCase("2")) {
						// AUTO
						lr.setResourceLogValue("2");
					} else {
						// ERROR
					}
					
					//Resource res = new Resource();
					//res.setId(Param.sensor_android);
					Resource res = getResourceFacade().find(Param.sensor_android);
					lr.setResource(res);
					
					this.getResourceLogFacade().create(lr);
					
					Esper.addEventEsper(lr);
				}
				//send message to mobile
				else if (m[0].trim().equalsIgnoreCase("3")){
					
				}
				//actuator
				else if (m[0].trim().equalsIgnoreCase("2")){
					
				}
				//sensor
				else if (m[0].trim().equalsIgnoreCase("1")){
					String idResource = m[1];
					String value = m[2];
					
					ResourceLog lr = new ResourceLog();
					lr.setResourceLogValue(value);
					lr.setCreationDate(new Timestamp(new Date().getTime()));
					
					//Resource res = new Resource();
					//res.setId(Integer.parseInt(idResource));
					
					
					//LogResourceFacade logResourceFacade = new LogResourceFacade();
					//logResourceFacade.create(lr);
					
					Resource res = getResourceFacade().find(Integer.parseInt(idResource));
					lr.setResource(res);
					
					this.getResourceLogFacade().create(lr);
					
					Esper.addEventEsper(lr);
					//System.out.println("FUSION CREATE --> " + m[1] + " --> " + m[2]);
					//there are two sensors that change status and we have to know
					
				}
				
				else {
					//This option is not a valid option
				}
			}
			
		}
		
		if (Param.mqtt_ivan){
			String m[] = message.split(del);
			
			//String type[] = m[0].split("=");
			
			ResourceLog lr = new ResourceLog();
			lr.setResourceLogValue(message);
			lr.setCreationDate(new Timestamp(new Date().getTime()));
			
			String id[] = m[1].split("=");
			
			Resource res = getResourceFacade().find(Integer.parseInt(id[1]));
			lr.setResource(res);
			
			this.getResourceLogFacade().create(lr);
			
//			if (type[1].equalsIgnoreCase("light")){
//				
//			}else if (type[1].equalsIgnoreCase("air")){
//				
//			}
//			
//			else if (type[1].equalsIgnoreCase("traffic")){
//				
//			}
//			else if (type[1].equalsIgnoreCase("noise")){
//				
//			}
//			else if (type[1].equalsIgnoreCase("structural")){
//				
//			}
//			else if (type[1].equalsIgnoreCase("waste")){
//				
//			}
			
			
		}

		return true;
	}

//	public void sendMessage(String msg) {
//		// try {
//		//
//		// MqttPublish pub = new MqttPublish(Param.address, "impre" + 11,
//		// Param.topic, msg);
//		//
//		// Thread thread = new Thread(pub);
//		// thread.start();
//		// } catch (Throwable th) {
//		// new Throwable("Drools Error Request Repository", th);
//		// }
//
//		RestFull rest = new RestFull();
//		try {
//			// rest.sendPost(Param.address_rai_rest, msg);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	private ResourceFacade getResourceFacade() {
		 if (resourceFacade == null) {
			 resourceFacade = new ResourceFacade();
		 }
		return resourceFacade;
	}

	private ResourceLogFacade getResourceLogFacade() {
		if (resourceLogFacade == null) {
			resourceLogFacade = new ResourceLogFacade();
		}
		return resourceLogFacade;
	}
	
}