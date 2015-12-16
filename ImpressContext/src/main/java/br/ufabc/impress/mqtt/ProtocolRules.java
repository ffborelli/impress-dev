package br.ufabc.impress.mqtt;

import java.sql.Timestamp;
import java.util.Date;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.file.File;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.util.NumberUtil;

/*
 * 
 * Describe the protocol and converting in code programming
 * */
public class ProtocolRules  implements Runnable{

	public static final String del = ";";
	public static int num = 0;
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;

	public ProtocolRules(String message) {
		this.message = message;
	}

	private String message;
	ThreadCounter count = new ThreadCounter();
	
	@Override
	public void run() {
		this.applyProtocolsRules();
	}
		
	private boolean applyProtocolsRules() {

		if (message.equalsIgnoreCase("START")) {

			if (Param.is_eval) {
				Main.startTime = System.currentTimeMillis();

				// eval time
//				EvalSdp eval = new EvalSdp();
//				eval.setStart(new Timestamp(Main.startTime));
//				eval.setName(Param.name_experiment);
//				eval.setModule("START TIME");
//				eval.setDescription("START TIME with " + Param.number_of_topics + " topics , broker " + Param.address);
//				new EvalSdpFacade().create(eval);
			
				//name , start, finish, duration,  duration_mil, module, description, 
				File f = new File();
				f.write("evalSDP.txt", Param.name_experiment + ";" + String.valueOf(Main.startTime) + ";" + ";" + ";" + ";" +  "START TIME" + ";" +"START TIME with " + Param.number_of_topics + " topics , broker " + Param.address);
				System.out.println("START");
			}

		} else if (message.equalsIgnoreCase("FINISH")) {
			count.addCounter();
			//System.out.println("FINISH " + count.getCounter());
			if (count.getCounter() == Param.number_of_devices) {
				if (Param.is_eval) {

					Main.finishTime = System.currentTimeMillis();

					// eval time
					EvalSdp eval = new EvalSdp();
					eval.setStart(new Timestamp(Main.startTime));
					eval.setFinish(new Timestamp(Main.finishTime));
					eval.setDuration(new Timestamp(Main.finishTime - Main.startTime));
					eval.setDurationMil(Main.finishTime - Main.startTime);
					eval.setName(Param.name_experiment);
					eval.setModule("FINISH TIME");
					eval.setDescription("FINISH TIME with " + Param.number_of_topics + " topics , broker " + Param.address);
					new EvalSdpFacade().create(eval);
					System.out.println("FINISH");
					
					//name , start, finish, duration,  duration_mil, module, description, 
					File f = new File();
					f.write("evalSDP.txt", Param.name_experiment + ";" + String.valueOf(Main.startTime) + ";" + String.valueOf(Main.finishTime) + ";" + ";" + ";" +  "FINISH TIME" + ";" + "FINISH TIME with " + Param.number_of_topics + " topics , broker " + Param.address);

					System.exit(0);
				}
			}

		} else { 

			String m[] = message.split(del);
			System.out.println("teste : "+ num++);
			NumberUtil nu = new NumberUtil();

			String r = null;
			// if m[0] is a number
			if (nu.isNumeric(m[0])) {
				// change status of demo
				if (m[0].trim().equalsIgnoreCase("4")) {

					ResourceLog lr = new ResourceLog();
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					if (m[1].trim().equalsIgnoreCase("0")) {

						// lr.setLogResourceValue("0");
						lr.setResourceLogValue("0");

					} else if (m[1].trim().equalsIgnoreCase("1")) {
						// ON
						// r = "ON";
						// lr.setLogResourceValue("1");
						lr.setResourceLogValue("1");

					}

					else if (m[1].trim().equalsIgnoreCase("2")) {
						// AUTO
						// lr.setLogResourceValue("2");
						lr.setResourceLogValue("2");
					} else {
						// ERROR
					}

					Resource res = getResourceFacade().find(Param.sensor_android);
					lr.setResource(res);

					//this.getResourceLogFacade().create(lr);

					Esper.addEventEsper(lr);
				}
				// send message to mobile
				else if (m[0].trim().equalsIgnoreCase("3")) {

				}
				// actuator
				else if (m[0].trim().equalsIgnoreCase("2")) {

				}
				// sensor
				else if (m[0].trim().equalsIgnoreCase("1")) {
					String idResource = m[1];
					String value = m[2];

					ResourceLog lr = new ResourceLog();
					lr.setResourceLogValue(value);
					lr.setCreationDate(new Timestamp(new Date().getTime()));

					Resource res = getResourceFacade().find(Integer.parseInt(idResource));
					lr.setResource(res);

					//this.getResourceLogFacade().create(lr);
					
					//id_resource_fk, resource_log_value, creation_date 
					File f = new File();
					f.write("resourceLog.txt",idResource + ";" + value + ";" );


					Esper.addEventEsper(lr);

				}

				else {
					// This option is not valid
				}
			}
		}

		return true;
	}

	public void sendMessage(String msg) {
		try {
			// MqttPublish pub = new MqttPublish(Param.uri, Param.port,
			// Param.clientId+11, "impress/action", "1");
			MqttPublish pub = new MqttPublish(Param.address, "impre" + 11, Param.topic, msg);
			// pub.publish(Param.uri, Param.port, Param.clientId+11,
			// "impress/temperature", "1");
			Thread thread = new Thread(pub);
			thread.start();
		} catch (Throwable th) {
			new Throwable("Drools Error Request Repository", th);
		}
	}

	private ResourceFacade getResourceFacade() {
		// if (resourceFacade == null) {
		resourceFacade = new ResourceFacade();
		// }
		return resourceFacade;
	}

	private ResourceLogFacade getResourceLogFacade() {
		// if (resourceLogFacade == null) {
		resourceLogFacade = new ResourceLogFacade();
		// }
		return resourceLogFacade;
	}



}