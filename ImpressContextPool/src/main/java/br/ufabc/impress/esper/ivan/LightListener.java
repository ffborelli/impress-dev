package br.ufabc.impress.esper.ivan;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import br.ufabc.impress.Main;
import br.ufabc.impress.Param;
import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.FusionFacade;
import br.ufabc.impress.facade.FusionLogFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceFusionLogFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.file.File;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Fusion;
import br.ufabc.impress.model.FusionLog;
import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceFusionLog;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.model.RuleActionLog;
import br.ufabc.impress.mqtt.MqttPublish;
import br.ufabc.impress.util.EvalUtil;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class LightListener implements UpdateListener {
	
	
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private FusionLogFacade fusionLogFacade;
	private FusionFacade fusionFacade;
	private ResourceFusionLogFacade resourceFusionLogFacade;
	private EvalSdpFacade evalSdpFacade;

	public void update(EventBean[] newData, EventBean[] oldData) {
		
		System.out.println("LIGHT FUSION HAS BEEN FIRED ");

		int max_replication= Integer.parseInt(newData[0].get("maxReplication").toString());
		System.out.println("Light max replication " + max_replication);
		String value = newData[0].get("value").toString();
		String bornDate = newData[0].get("maxBornDate").toString();
				
		value = EvalUtil.setTime("P3", value, ";", "=");
				
		Resource r = this.getResourceFacade().find(Param.sensor_light);

		ResourceLog lr = new ResourceLog();
		lr.setResourceLogValue(value);
		lr.setResource(r);
		lr.setCreationDate(new Timestamp(new Date().getTime()));
		
		this.getResourceLogFacade().create(lr);
		
		Fusion f = this.getFusionFacade().find(Param.sensor_light);
		
		FusionLog fl = new FusionLog();
		fl.setCreationDate(new Timestamp(new Date().getTime()));
		fl.setFusion(f);
		fl.setFusionLogValue(max_replication+ ";"+ bornDate);
		
		this.getFusionLogFacade().create(fl);
		
		fl = this.getFusionLogFacade().find(fl);
		//System.out.println("ID " + fl.getId());
		
		ResourceFusionLog rfl = new ResourceFusionLog();
		rfl.setCreationDate(new Timestamp(new Date().getTime()));
		rfl.setFusionLog(fl);
		rfl.setResourceLog(lr);
		
		this.getResourceFusionLogFacade().create(rfl);
		
		//String resource = EvalUtil.getParameter("resource", value, ";", "=");
		String UID = EvalUtil.getParameter("UID", value, ";", "=");
		
		String p3 = EvalUtil.getParameter("P3", value, ";", "=");
		//String rep = EvalUtil.getParameter("REP", value, ";", "=");
		//String exp = EvalUtil.getParameter("EXP", value, ";", "=");
		
		//Resource res = getResourceFacade().find(Integer.parseInt(resource));
		
		EvalSdp eval = this.getEvalSdpFacade().find(Long.valueOf(UID));
		
		eval.setP3(new Timestamp(Long.valueOf(p3)));
		
		this.getEvalSdpFacade().update(eval);
			
		Drools drools = new Drools();
		drools.requestRepository(lr,value);

	}
	
	private ResourceFacade getResourceFacade(){
		if (resourceFacade == null){
			resourceFacade = new ResourceFacade();
		}
		return resourceFacade;
	}
	
	private ResourceLogFacade getResourceLogFacade(){
		if (resourceLogFacade == null){
			resourceLogFacade = new ResourceLogFacade();
		}
		return resourceLogFacade;
	}
	
	private FusionLogFacade getFusionLogFacade(){
		if (fusionLogFacade == null){
			fusionLogFacade = new FusionLogFacade();
		}
		return fusionLogFacade;
	} 
	
	private EvalSdpFacade getEvalSdpFacade(){
		if (evalSdpFacade == null){
			evalSdpFacade = new EvalSdpFacade();
		}
		return evalSdpFacade;
	}
	
	private FusionFacade getFusionFacade(){
		if (fusionFacade == null){
			fusionFacade = new FusionFacade();
		}
		return  fusionFacade;
	}
	
	private ResourceFusionLogFacade getResourceFusionLogFacade(){
		if (resourceFusionLogFacade == null){
			resourceFusionLogFacade = new ResourceFusionLogFacade();
		}
		return resourceFusionLogFacade;
	}
}