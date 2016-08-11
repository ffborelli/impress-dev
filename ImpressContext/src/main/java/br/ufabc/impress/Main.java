package br.ufabc.impress;

import java.util.ArrayList;

import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.rai.InteractionRAI;
import br.ufabc.impress.rai.MqttIvan;
import br.ufabc.impress.rai.MqttUFABC;
import br.ufabc.impress.tracker.TrackerPooling;

public class Main {

	
	/*
	 * Mudar sensores para o banco de dados
	 * Mudar fusão para banco de dados
	 * */

	public static ArrayList<ResourceRAI> resourceRAIarr = new ArrayList<ResourceRAI>();
	
	public static void main(String[] args) {
		
		//check input config
		
		for (int i = 0; i < args.length; i++){
			
			if (args[i].equalsIgnoreCase("-grm")){
				Param.grm = (args[i+1].equalsIgnoreCase("true")?true:false);
			}
			
			if (args[i].equalsIgnoreCase("-rai")){
				Param.rai = (args[i+1].equalsIgnoreCase("true")?true:false);
				new InteractionRAI().connectRAI();
			}
			
			if (args[i].equalsIgnoreCase("-tracker")){
				Param.tracker = (args[i+1].equalsIgnoreCase("true")?true:false);
			}
			
			if (args[i].equalsIgnoreCase("-esper")){
				Param.esper = (args[i+1].equalsIgnoreCase("true")?true:false);
			}
			
			if (args[i].equalsIgnoreCase("-mqtt_ivan")){
				System.out.println("START ....");
				Param.mqtt_ivan = (args[i+1].equalsIgnoreCase("true")?true:false);
				new MqttIvan().connect();
			}
			
			if (args[i].equalsIgnoreCase("-mqtt_ufabc")){
				Param.mqtt_ufabc = (args[i+1].equalsIgnoreCase("true")?true:false);
				new MqttUFABC().connect();
			}
		}

		// Context Tracker

		if (Param.tracker) {
			TrackerPooling poolingTracker = new TrackerPooling();

			Thread threadPoolingTracker = new Thread(poolingTracker);
			threadPoolingTracker.start();
		}
		
		//reset the buffer os the resource os drools
		//new Drools().resetBuffer();
		
		if (Param.esper) {
			//EsperPooling esperPooling = new EsperPooling();

			//Thread threadEsperPooling = new Thread(esperPooling);
			//threadEsperPooling.start();
			
			//at this moment, the SDP does not capture an update from a fusion
			Esper.createArrayEsper();
		}
		//get JSON FROM GRM and histerese
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

		// esper
		//Esper.startEsper();
		//Esper.createArrayEsper();
		

	}
}

