//package br.ufabc.impress;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//
//public class EnergySafer {
//
//	public static void main(String[] args) {
//
//		try {
//            // open websocket
//            //final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://192.168.1.101:10011"));
//            //final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://172.31.127.31:10011"));
//            
//			//final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("wss://impressufabc.cloudapp.net:10010/"));
//            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://130.188.58.101:10011/"));
//            
//            
//            // add listener
//            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
//                public void handleMessage(String message) {
//                    System.out.println("Receive: " + message);
//                }
//            });
//
//            // send message to websocket
//            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");
//            //clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");
//            //[0, 0, {"application ID": "7d1331c0-cf29-4d52-8089-9e0418dcc634", "resource specification ID": "1", "resource count" : 2}]
//            //clientEndPoint.sendMessage("[ 0, 0, {\"application ID\": \"7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"1\", \"resource count\" : 2} ]");
//            
//            //You need to first register your application. In the registration message you have the application description which contains also the resource specifications.
//            
//            //The registration messages should look as follows. There is one specification for occupancy sensor and lighting system resources for each row in the classroom 10:
//            
////            clientEndPoint.sendMessage("[2, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", " +
////            		" \"application criticality\": 100, " +
////            		" \"application name\": \"Energy saver app\", "   +
////            		" \"description\": \"The application controls lights in order to save energy.\", "  +
////            		" \"resources\": [ " +
////            		"         {\"resource specification ID\" : \"1\",  "  +
////            		"           \"access scheme\": \"Shared\",  " +
////            		"           \"significance\": \"Obligatory\", " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#> "  +
////            		"                      SELECT ?resource   " +
////            		"                      WHERE { ?resource a rm:OccupancySensor ; "  +
////            		"                                        rm:associatedTo <urn:row1inClassRoom10>.}\"  " +
////            		"         },  " +
////            		"         {\"resource specification ID\" : \"2\",   " +
////            		"           \"access scheme\": \"Shared\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource    " +
////            		"                      WHERE { ?resource a rm:OccupancySensor ;   " +
////            		"                                        rm:associatedTo <urn:row2inClassRoom10>.}\"  " +
////            		"         },  " +
////            		"         {\"resource specification ID\" : \"3\",   " +
////            		"           \"access scheme\": \"Shared\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource    " +
////            		"                      WHERE { ?resource a rm:OccupancySensor ;   " +
////            		"                                       rm:associatedTo <urn:row3inClassRoom10>.}\"  " +
////            		"         },  " +
////            		"         {\"resource specification ID\" : \"4\",   " +
////            		"           \"access scheme\": \"Shared\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource    " +
////            		"                      WHERE { ?resource a rm:OccupancySensor ;   " +
////            		"                                        rm:associatedTo <urn:row4inClassRoom10>. }\"  " +
////            		"         },  " +
////            		"         { \"resource specification ID\" : \"5\",   " +
////            		"           \"access scheme\": \"Exclusive\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource   " +
////            		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
////            		"                                        rm:associatedTo <urn:row1inClassRoom10>.}\"  " +
////            		"         },  " +
////            		"         { \"resource specification ID\" : \"6\",   " +
////            		"           \"access scheme\": \"Exclusive\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource   " +
////            		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
////            		"                                        rm:associatedTo <urn:row2inClassRoom10>.}\"  " +
////            		"         },  " +	
////            		"         { \"resource specification ID\" : \"7\",   " +
////            		"           \"access scheme\": \"Exclusive\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource   " +
////            		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
////            		"                                        rm:associatedTo <urn:row3inClassRoom10>.}\"  " +
////            		"         },  " +
////            		"         { \"resource specification ID\" : \"8\",   " +
////            		"           \"access scheme\": \"Exclusive\",   " +
////            		"           \"significance\": \"Obligatory\",  " +
////            		"           \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#>   " +
////            		"                      SELECT ?resource   " +
////            		"                      WHERE { ?resource a rm:PhilipsHue ;   " +
////            		"                                        rm:associatedTo <urn:row4inClassRoom10>.}\"  " +
////            		"         }  " +
////            		"      ]}  " +
////            		"]" );
//            
//            //Then in order the actually reserve resources you need to make a reservation messages for each specification. For example if you want to reserve two resources matching the specification one you sent the following message to the GRM:
//          
//            //clientEndPoint.sendMessage("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"1\", \"resource count\": 2}]" );
//            
//            //Or to reserve one Lighting system resource associated to the row 4 you sent the following message
//            
//            //clientEndPoint.sendMessage("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"8\", \"resource count\": 1}]");
//            
//            
//            
//            //clientEndPoint.sendMessage("[2, 0, {\"application ID\": \"7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"application criticality\": 100, \"application name\": \"Energy saver app\", \"description\": \"The application controls lights in order to save energy.\", \"resources\": [ {\"resource specification ID\" : \"1\",  \"access scheme\": \"Shared\", \"significance\": \"Obligatory\", \"query\" : \"PREFIX rm: <http://purl.oclc.org/impress/resource#> SELECT ?resource  WHERE { ?resource a rm:OccupancySensor ; rm:associatedTo <urn:row1inClassRoom10>.}\"  }  ]} ]" );
//            
//            clientEndPoint.sendMessage("[0, 0, {\"application ID\": \"urn:7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"5\", \"resource count\": 2}]");
//            //clientEndPoint.sendMessage("[0, 0, {\"application ID\": \"7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"1\", \"resource count\": 2}]");
//            
//            //clientEndPoint.sendMessage("[0, 0, {\"application ID\": \"7d1331c0-cf29-4d52-8089-9e0418dcc634\", \"resource specification ID\": \"5\", \"resource count\": 1}]");
//            
//            // wait 5 seconds for messages from websocket
//            Thread.sleep(1000);
//            
////            <JSON>
////            {"Thing":[{"raiAddress":"http://130.192.86.221:8888/321f92ef-60a3-3129-b67d-c05fea201f2e","mqttBrokerIP":"tcp://130.192.85.32","mqttBrokerPort":"1883","mqtttopics":[{"type":"metadata", "topic":"/impress/metadata/321f92ef-60a3-3129-b67d-c05fea201f2e"},{"type":"observation", "topic":"/impress/observation/321f92ef-60a3-3129-b67d-c05fea201f2e"}]}]}
////            </JSON>
//            
//
//        } catch (InterruptedException ex) {
//            System.err.println("InterruptedException exception: " + ex.getMessage());
//        } catch (URISyntaxException ex) {
//            System.err.println("URISyntaxException exception: " + ex.getMessage());
//        }
//
//	}
//}
