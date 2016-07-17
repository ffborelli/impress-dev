package br.ufabc.impress;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.model.Resource;

public class CopyOfWebsocketGRM implements Runnable {

	private WebSocketClient cc;
	private URI uri;
	private ResourceFacade resourceFacade;

	public CopyOfWebsocketGRM(String address) {
		

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
			
			private void checkNotification(String msg){
				//At first, we need to undertand if it is a notification
				String input = null;
				if (msg.startsWith("[0,2,")){
					//we need to discover which resource release the reserve
					input = msg;
					
					input = msg.substring(5, msg.length() - 1);
					
					int resource = 0;
					
					Resource r = this.getResourceFacade().find(resource);
					
					if (r.isReserved() == false){
						r.setReserved(true);
						this.getResourceFacade().update(r);
					}
					
				}
				
				//return input;
			}
			
			private ResourceFacade getResourceFacade(){
				if (resourceFacade == null){
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
		"                      WHERE { ?resource a rm:SmartPlug ; "  +
		"                                        .}\"  " +
		"         },  " +
		"         {\"resource specification ID\" : \"2\",   " +
		"           \"access scheme\": \"Shared\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource    " +
		"                      WHERE { ?resource a rm:OccupancySensor ;   " +
		"                                        rm:associatedTo <urn:row2inClassRoom10>.}\"  " +
		"         },  " +
		"         {\"resource specification ID\" : \"3\",   " +
		"           \"access scheme\": \"Shared\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource    " +
		"                      WHERE { ?resource a rm:OccupancySensor ;   " +
		"                                       rm:associatedTo <urn:row3inClassRoom10>.}\"  " +
		"         },  " +
		"         {\"resource specification ID\" : \"4\",   " +
		"           \"access scheme\": \"Shared\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource    " +
		"                      WHERE { ?resource a rm:OccupancySensor ;   " +
		"                                        rm:associatedTo <urn:row4inClassRoom10>. }\"  " +
		"         },  " +
		"         { \"resource specification ID\" : \"5\",   " +
		"           \"access scheme\": \"Exclusive\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource   " +
		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
		"                                        rm:associatedTo <urn:row1inClassRoom10>.}\"  " +
		"         },  " +
		"         { \"resource specification ID\" : \"6\",   " +
		"           \"access scheme\": \"Exclusive\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource   " +
		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
		"                                        rm:associatedTo <urn:row2inClassRoom10>.}\"  " +
		"         },  " +	
		"         { \"resource specification ID\" : \"7\",   " +
		"           \"access scheme\": \"Exclusive\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource   " +
		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
		"                                        rm:associatedTo <urn:row3inClassRoom10>.}\"  " +
		"         },  " +
		"         { \"resource specification ID\" : \"8\",   " +
		"           \"access scheme\": \"Exclusive\",   " +
		"           \"significance\": \"Obligatory\",  " +
		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
		"                      SELECT ?resource   " +
		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
		"                                        rm:associatedTo <urn:row4inClassRoom10>.}\"  " +
		"         }  " +
		"      ]}  " +
		"]" );
      
//      "[2,0,{\"application ID\":\"urn:06bcd6c2-528b-40d0-9201-59c82ed07705admin\",\"application criticality\":301,"
//      + "\"application name\":\"webcontroller\",\"description\":\"webcontroller description\",\"resources\":[{\"resource specification ID\":1,\"significance\":3,\"access scheme\":\"exclusive\",\"query\":\"PREFIX rm: <http://purl.oclc.org/impress/resource#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?resource WHERE {?resource a rm:LightingSystem .}\"}]}] DEBUG:grmServerProtocolLRM:Received message[2, 0, {u'application ID': u'urn:06bcd6c2-528b-40d0-9201-59c82ed07705admin', u'application criticality': 301, u'description': u'webcontroller description', u'resources': [{u'query': u'PREFIX rm: <http://purl.oclc.org/impress/resource#> PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> SELECT ?resource WHERE {?resource a rm:LightingSystem .}\'
//    	  , u\'access scheme\': u\'exclusive\', u\'resource specification ID\': 1, u'significance': 3}], u'application name': u'webcontroller'}]
      
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        cc.send("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"1\", \"resource count\": 10}]");
      
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //cc.close();
	    
	}

	public void closeConnection() {
		cc.close();
	}

	public void sendMessage(String text) {
		cc.send(text);
	}
	
}
