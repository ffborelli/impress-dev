//package br.ufabc.impress;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class JSONTest {
//	
//	public static void main(String[] args) {
//		///impress/observation/iotentity/8f267135-6477-3a59-aa5f-0f28572e261f {"About":"8f267135-6477-3a59-aa5f-0f28572e261f","Properties":[{"IoTStateObservation":[{"Value":"false","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getPresence"},{"IoTStateObservation":[{"Value":"0","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getNumberOfPeople"},{"IoTStateObservation":[{"Value":"[]","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getCoordinates"},{"IoTStateObservation":[{"Value":"true","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getIsOn"},{"IoTStateObservation":[{"Value":"0","PhenomenonTime":"2016-03-04T16:47:52.542Z","ResultTime":"2016-03-04T16:47:52.552Z"}],"About":"8f267135-6477-3a59-aa5f-0f28572e261f:Kinect:getTiltAngle"}]}
//		//String json = "{\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"1\", \"resource count\": 2}";
//		//String json = "{\"Name\":\"11F024-INSTANT_POWER\",\"Meta\":{{\"property\":\"impress:networkType\",\"Value\":\"Plugwise\"},{\"property\":\"geo:point\",\"Value\":\"40.0 865.0\"}},\"Properties\":{{\"Name\":\"Power Consumed\",\"UnitOfMeasurement\":{\"property\":\"W\",\"TypeOf\":{\"Power Consumed\"}},\"About\":\"2808dd61-3034-3651-8ebc-482b9b34e998:Meter:getPower\",\"TypeOf\":{\"impress:Meter:getPower\"},\"DataType\":\"Double\"},{\"Name\":\"Relay status\",\"UnitOfMeasurement\":{\"TypeOf\":{\"Relay status\"}},\"About\":\"2808dd61-3034-3651-8ebc-482b9b34e998:Meter:getOnOff\",\"TypeOf\":{\"impress:Meter:getOnOff\"},\"DataType\":\"Boolean\"}},\"About\":\"2808dd61-3034-3651-8ebc-482b9b34e998\",\"TypeOf\":{\"Meter\"}}";
//		String json = "[0,2,{\"resource specification ID\": \"identifier\", \"resource ID\": null, \"old resource ID\" : \"http://purl.oclc.org/impress/rai/occupancysensor54\"}]";
//		System.out.println(checkNotification(json));
//		JSONObject obj = null;
//		try {
//			obj  = new JSONObject(json);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		JSONObject jsonObject = (JSONObject) obj;
//		try {
//			//String name = (String) jsonObject.get("application ID");
//			String name = (String) jsonObject.get("resource specification ID");
//			System.out.println(name);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static String checkNotification(String msg){
//		//At first, we need to undertand if it is a notification
//		String input = null;
//		if (msg.startsWith("[0,2,")){
//			//we need to discover which resource release the reserve
//			input = msg;
//			
//			input = msg.substring(5, msg.length() - 1);
//			
//			
//		}
//		
//		return input;
//	}
//
//}
