package br.ufabc.impress.rai;

import java.util.List;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceTypeFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.model.ResourceType;
import br.ufabc.impress.mqtt.MqttSubscribe;

public class MqttIvan {

	ResourceFacade resourceFacade;
	ResourceTypeFacade resourceTypeFacade;

	public void connect() {

		
		//List<Resource> list = this.getResourceFacade().listAll();
		List<ResourceType> list = this.getResourceTypeFacade().listAll();

		for (int i = 0; i < list.size(); i++) {

				MqttSubscribe ms = new MqttSubscribe("tcp://localhost:1883", "impress" + i, "/ivan/" +list.get(i).getId());
				Thread t = new Thread(ms);
				t.start();

		}

	}

	private ResourceFacade getResourceFacade() {

		if (resourceFacade == null) {
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}
	private ResourceTypeFacade getResourceTypeFacade() {

		if (resourceTypeFacade == null) {
			resourceTypeFacade = new ResourceTypeFacade();
		}
		return resourceTypeFacade;
	}

}
