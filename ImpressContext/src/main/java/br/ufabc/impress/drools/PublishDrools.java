package br.ufabc.impress.drools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import br.ufabc.impress.Param;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.mqtt.MqttPublish;

public class PublishDrools {

	ArrayList<String> messages;
	
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;

	public PublishDrools(ArrayList<String> messages) {
		this.messages = messages;
	}

	public void publish() {

		if (messages.isEmpty() == false) {

			//LogResourceFacade logResourceFacade = new LogResourceFacade();

			for (int i = 0; i < messages.size(); i++) {

				String fields[] = messages.get(i).split(";");

				//Resource r = new Resource();
				//r.setId(Integer.parseInt(fields[1]));
				//System.out.println("SEND " + messages.get(i) + " " + fields[0] + " " + fields[1] + " "+ fields[2] );
				
				
				Resource r;
				MqttPublish m;

				ResourceLog lr = new ResourceLog();
				if (fields[0].equals("4")) {
					//lr.setLogResourceValue(fields[1]);
					lr.setResourceLogValue(fields[1]);
					r = this.getResourceFacade().find(Param.sensor_android);
					
					m = new MqttPublish(Param.address, "id1", "demo/android",
							messages.get(i));
					
				}
				else {
					lr.setResourceLogValue(fields[2]);
					r = this.getResourceFacade().find(Integer.parseInt(fields[1]));
					
					m = new MqttPublish(Param.address, "id1",
							Param.topic_publish, messages.get(i));
				}
				lr.setResource(r);
				lr.setCreationDate(new Timestamp(new Date().getTime()));

				this.getResourceLogFacade().create(lr);

				Thread thread = new Thread(m);
				thread.start();
			}
		}

	}
	
	private ResourceFacade getResourceFacade(){
		//if (resourceFacade == null){
			resourceFacade = new ResourceFacade();
		//}
		return resourceFacade;
	}
	
	private ResourceLogFacade getResourceLogFacade(){
		//if (resourceLogFacade == null){
			resourceLogFacade = new ResourceLogFacade();
		//}
		return resourceLogFacade;
	}

}
