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

		ResourceRAI resourceRAI;
		//List<Resource> list = this.getResourceFacade().listAll();
		List<ResourceType> list = this.getResourceTypeFacade().listAll();

		for (int i = 0; i < list.size(); i++) {

			//if (resourceList.get(i).isReserved() == true) {

				//resourceRAI = new ResourceRAI();

				//resourceRAI.setTopic(list.get(i).getMqttTopic());
				//resourceRAI.setType(list.get(i).getResourceType().getDescription());
				//resourceRAI.setUid(String.valueOf(list.get(i).getId()));
				//Main.resourceRAIarr.add(resourceRAI);

				MqttSubscribe ms = new MqttSubscribe("tcp://localhost:1883", "impress" + i, "/ivan/" +list.get(i).getId());
				Thread t = new Thread(ms);
				t.start();
			//}

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
