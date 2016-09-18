package br.ufabc.impress.esper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.facade.ContextFacade;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.FusionFacade;
import br.ufabc.impress.facade.FusionLogFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceFusionLogFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.file.File;
import br.ufabc.impress.model.Context;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Fusion;
import br.ufabc.impress.model.FusionLog;
import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceFusionLog;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.model.RuleActionLog;
import br.ufabc.impress.mqtt.MqttPublish;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class PresenceListener2 implements UpdateListener {

	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private FusionLogFacade fusionLogFacade;
	private FusionFacade fusionFacade;
	private ResourceFusionLogFacade resourceFusionLogFacade;
	private EvalSdpFacade evalSdpFacade;

	private ContextFacade contextFacade;

	public void update(EventBean[] newData, EventBean[] oldData) {

		// long startTime = System.nanoTime();
		// long startTime = System.currentTimeMillis();

		int count = 0;

		count = Integer.parseInt(newData[0].get("S1").toString());

		System.out.println("Ultrasonic 2 " + count);

		Resource r = this.getResourceFacade().find(2);

		ResourceLog lr = new ResourceLog();
		lr.setResourceLogValue(String.valueOf(count));
		lr.setResource(r);
		lr.setCreationDate(new Timestamp(new Date().getTime()));

		this.getResourceLogFacade().create(lr);

		Fusion f = this.getFusionFacade().find(Param.fusion_presence);

		FusionLog fl = new FusionLog();
		fl.setCreationDate(new Timestamp(new Date().getTime()));
		fl.setFusion(f);
		fl.setFusionLogValue(String.valueOf(count));

		this.getFusionLogFacade().create(fl);

		fl = this.getFusionLogFacade().find(fl);
		// System.out.println("ID " + fl.getId());

		ResourceFusionLog rfl = new ResourceFusionLog();
		rfl.setCreationDate(new Timestamp(new Date().getTime()));
		rfl.setFusionLog(fl);
		rfl.setResourceLog(lr);

		this.getResourceFusionLogFacade().create(rfl);

		Context c = this.getContextFacade().getLast();
		
		if (c.getRunning() == 2) {

			String v = null;
			
			ResourceLog kinect = this.getResourceLogFacade().getLastByResource(14);
			
			int p = Integer.parseInt(kinect.getResourceLogValue()); 
			
			if (p > 0 && lr.getResourceLogValue().equalsIgnoreCase("1")){
				v = "1";
			}
			else {
				v = "0";
			}
			
			Resource newResource = this.getResourceFacade().find(17);
			
			ResourceLog newResourceLog = new ResourceLog();
			newResourceLog.setResource(newResource);
			newResourceLog.setResourceLogValue(v);
			newResourceLog.setCreationDate(new Timestamp(new Date().getTime()));
			
			this.getResourceLogFacade().create(newResourceLog);

			Drools drools = new Drools();
			drools.requestRepository(newResourceLog);
		} else {

			Drools drools = new Drools();
			drools.requestRepository(lr);
		}

	}

	private ResourceFacade getResourceFacade() {
		if (resourceFacade == null) {
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}

	private ResourceLogFacade getResourceLogFacade() {
		if (resourceLogFacade == null) {
			resourceLogFacade = new ResourceLogFacade();
		}
		return resourceLogFacade;
	}

	private FusionLogFacade getFusionLogFacade() {
		if (fusionLogFacade == null) {
			fusionLogFacade = new FusionLogFacade();
		}
		return fusionLogFacade;
	}

	private EvalSdpFacade getEvalSdpFacade() {
		if (evalSdpFacade == null) {
			evalSdpFacade = new EvalSdpFacade();
		}
		return evalSdpFacade;
	}

	private FusionFacade getFusionFacade() {
		if (fusionFacade == null) {
			fusionFacade = new FusionFacade();
		}
		return fusionFacade;
	}

	private ResourceFusionLogFacade getResourceFusionLogFacade() {
		if (resourceFusionLogFacade == null) {
			resourceFusionLogFacade = new ResourceFusionLogFacade();
		}
		return resourceFusionLogFacade;
	}

	private ContextFacade getContextFacade() {
		if (contextFacade == null) {
			contextFacade = new ContextFacade();
		}
		return contextFacade;
	}
}