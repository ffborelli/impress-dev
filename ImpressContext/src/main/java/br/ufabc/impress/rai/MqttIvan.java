package br.ufabc.impress.rai;

import java.util.List;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceRAI;
import br.ufabc.impress.mqtt.MqttSubscribe;

public class MqttIvan {

	ResourceFacade resourceFacade;

	public void connect() {

		ResourceRAI resourceRAI;
		List<Resource> resourceList = this.getResourceFacade().listAll();

		for (int i = 0; i < resourceList.size(); i++) {

			if (resourceList.get(i).isReserved() == true) {

				resourceRAI = new ResourceRAI();

				resourceRAI.setTopic(resourceList.get(i).getMqttTopic());
				resourceRAI.setType(resourceList.get(i).getResourceType()
						.getDescription());
				resourceRAI.setUid(String.valueOf(resourceList.get(i).getId()));
				Main.resourceRAIarr.add(resourceRAI);

				MqttSubscribe ms = new MqttSubscribe(resourceList.get(i)
						.getMqttAddress(), "impress" + i, Main.resourceRAIarr
						.get(i).getTopic());
				Thread t = new Thread(ms);
				t.start();
			}

		}

	}

	private ResourceFacade getResourceFacade() {

		if (resourceFacade == null) {
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}

}
