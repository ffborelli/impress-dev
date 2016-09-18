package br.ufabc.impress.esper;

import br.ufabc.impress.Param;
import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.mqtt.MqttPublish;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;


public class DemoListener implements UpdateListener {

	public void update(EventBean[] newData, EventBean[] oldData) {
		// System.out.println("Temperature received: " +
		// newData[0].getUnderlying());
		// System.out.println("ESPER : listener received -->" +
		// newData[0].get("value"));


	}
}