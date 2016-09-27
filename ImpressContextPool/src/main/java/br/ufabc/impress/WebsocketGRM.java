package br.ufabc.impress;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.json.stream.JsonParser;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;

import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.util.JsonUtil;

public class WebsocketGRM implements Runnable {

	private WebSocketClient cc;
	private URI uri;
	private ResourceFacade resourceFacade;

	public WebsocketGRM(String address) {

		try {
			uri = new URI(address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cc = new WebSocketClient(uri) {

			@Override
			public void onMessage(String message) {
				System.out.println("Received: " + message);
				this.checkNotification(message);

			}

			@Override
			public void onOpen(ServerHandshake handshake) {
				System.out.println("Open connection");
			}

			@Override
			public void onClose(int code, String reason, boolean remote) {
				System.out.println("Connection closed");
			}

			@Override
			public void onError(Exception ex) {
				ex.printStackTrace();
			}

			private void checkNotification(String message) {

				JSONArray array = null;

				try {
					array = new JSONArray(message);

					// It is from reserve a resource
					if (Integer.parseInt(array.get(0).toString()) == 0) {

						if (Integer.parseInt(array.get(1).toString()) == 1) {
							JSONObject loc = array.getJSONObject(2);
							JSONArray r = loc.getJSONArray("resource ID");

							for (int i = 0; i < r.length(); i++) {
								// System.out.println(r.get(i));
								// we need to get the information about the
								// resource

								String json = null;
								try {
									json = JsonUtil.readJsonFromUrl(r.get(i)
											.toString());
									json = json
											.replace("<JSON>{\"Thing\":", "");
									json = json.replace("}</JSON>", "");

									System.out.println(json);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								System.out.println(json.toString());

								JSONArray rArr = new JSONArray(json);

								System.out.println(rArr.getString(0));

							}
						} 
						//notification 
						else if (Integer.parseInt(array.get(1).toString()) == 2) {
							JSONObject loc = array.getJSONObject(2);
							String resource = loc.getString("resource specification ID");
							
							System.out.println("GRM " + resource);
							
							//Resource r = this.getResourceFacade().findByUid(uid);
							//r.setReserved(true);
							//this.getResourceFacade().update(r);
							
							
						}
					}

					// System.out.println(loc.get("resource ID"));

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			private ResourceFacade getResourceFacade() {
				if (resourceFacade == null) {
					resourceFacade = new ResourceFacade();
				}
				return resourceFacade;
			}
		};
	}

	public void run() {
		cc.connect();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		cc.send("[2, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", "
//				+ " \"application criticality\": 100, "
//				+ " \"application name\": \"IMPReSS Demo App\", "
//				+ " \"description\": \"The application controls lights in order to save energy.\", "
//				+ " \"resources\": [ "
//				+ "         {\"resource specification ID\" : \"1\",  "
//				+ "           \"access scheme\": \"Shared\",  "
//				+ "           \"significance\": \"Obligatory\", "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#> "
//				+ "                      SELECT ?resource   "
//				+ "                      WHERE { ?resource a rm:ultrasonic ; "
//				+ "                                        rm:associatedTo <urn:Row1> .}\"  "
//				+ "         },  "
//				+ "         {\"resource specification ID\" : \"2\",   "
//				+ "           \"access scheme\": \"Shared\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource    "
//				+ "                      WHERE { ?resource a rm:ultrasonic ;   "
//				+ "                                        rm:associatedTo <urn:Row2>.}\"  "
//				+ "         },  "
//				+ "         {\"resource specification ID\" : \"3\",   "
//				+ "           \"access scheme\": \"Shared\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource    "
//				+ "                      WHERE { ?resource a rm:ultrasonic ;   "
//				+ "                                       rm:associatedTo <urn:Row3>.}\"  "
//				+ "         },  "
//				+ "         {\"resource specification ID\" : \"4\",   "
//				+ "           \"access scheme\": \"Shared\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource    "
//				+ "                      WHERE { ?resource a rm:ultrasonic ;   "
//				+ "                                        rm:associatedTo <urn:Row4>. }\"  "
//				+ "         },  "
//				+ "         {\"resource specification ID\" : \"5\",   "
//				+ "           \"access scheme\": \"Shared\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource    "
//				+ "                      WHERE { ?resource a rm:thermometer ;   "
//				+ "                                        rm:associatedTo <urn:Wall1>. }\"  "
//				+ "         },  "
//				+ "         {\"resource specification ID\" : \"6\",   "
//				+ "           \"access scheme\": \"Shared\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource    "
//				+ "                      WHERE { ?resource a rm:thermometer ;   "
//				+ "                                        rm:associatedTo <urn:Wall2>. }\"  "
//				+ "         },  "
//				+ "         { \"resource specification ID\" : \"7\",   "
//				+ "           \"access scheme\": \"Exclusive\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource   "
//				+ "                      WHERE { ?resource a rm:PhilipsHue ;   "
//				+ "                                        rm:associatedTo <urn:Row1>.}\"  "
//				+ "         },  "
//				+ "         { \"resource specification ID\" : \"8\",   "
//				+ "           \"access scheme\": \"Exclusive\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource   "
//				+ "                      WHERE { ?resource a rm:PhilipsHue ;   "
//				+ "                                        rm:associatedTo <urn:Row2>.}\"  "
//				+ "         },  "
//				+ "         { \"resource specification ID\" : \"9\",   "
//				+ "           \"access scheme\": \"Exclusive\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource   "
//				+ "                      WHERE { ?resource a rm:PhilipsHue ;   "
//				+ "                                        rm:associatedTo <urn:Row3>.}\"  "
//				+ "         },  "
//				+ "         { \"resource specification ID\" : \"10\",   "
//				+ "           \"access scheme\": \"Exclusive\",   "
//				+ "           \"significance\": \"Obligatory\",  "
//				+ "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   "
//				+ "                      SELECT ?resource   "
//				+ "                      WHERE { ?resource a rm:PhilipsHue ;   "
//				+ "                                        rm:associatedTo <urn:Row4>.}\"  "
//				+ "         }  " + "      ]}  " + "]");


		cc.send("[2, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", " +
				  " \"application criticality\": 100, " +
				  " \"application name\": \"IMPReSS Demo App\", "   +
				  " \"description\": \"The application controls lights in order to save energy.\", "  +
				  " \"resources\": [ " +
				  "         {\"resource specification ID\" : \"1\",  "  +
				  "           \"access scheme\": \"Shared\",  " +
				  "           \"significance\": \"Obligatory\", " +
				  "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#> "  +
				  "                      SELECT ?resource   " +
				  "                      WHERE { ?resource a rm:KinectSensor .}\"  " +
				  "         },  " +
				  "         {\"resource specification ID\" : \"2\",   " +
				  "           \"access scheme\": \"Shared\",   " +
				  "           \"significance\": \"Obligatory\",  " +
				  "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
				  "                      SELECT ?resource    " +
				  "                      WHERE { ?resource a rm:ultrasonic .}\"  " +
				  "         },  " +
				  "         {\"resource specification ID\" : \"3\",   " +
				  "           \"access scheme\": \"Shared\",   " +
				  "           \"significance\": \"Obligatory\",  " +
				  "           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
				  "                      SELECT ?resource    " +
				  "                      WHERE { ?resource a rm:LightingSystem .}\"  " +
				   "         }  " +
				  "      ]}  " +
				  "]" );
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		cc.send("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"1\", \"resource count\": 2}]");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		cc.send("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"2\", \"resource count\": 4}]");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		cc.send("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"3\", \"resource count\": 4}]");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// cc.close();

	}

	public void closeConnection() {
		cc.close();
	}

	public void sendMessage(String text) {
		cc.send(text);
	}

}
