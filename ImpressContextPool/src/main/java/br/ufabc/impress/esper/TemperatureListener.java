package br.ufabc.impress.esper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceFusionLog;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.mqtt.MqttPublish;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;


public class TemperatureListener implements UpdateListener {
	
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private FusionLogFacade fusionLogFacade;
	private FusionFacade fusionFacade;
	private ResourceFusionLogFacade resourceFusionLogFacade;
	private EvalSdpFacade evalSdpFacade;

	public void update(EventBean[] newData, EventBean[] oldData) {

		System.out.println("Temperature Listener");
		
//		ResourceLog status = this.getResourceLogFacade().getLastByResource(
//				Param.sensor_android);
//
		Resource r = this.getResourceFacade()
				.find(Param.sensor_avg_temperature);
		
		ResourceLog lr = new ResourceLog();
		lr.setResourceLogValue(newData[0].get("avgT").toString());
		lr.setResource(r);
		lr.setCreationDate(new Timestamp(new Date().getTime()));
		
		this.getResourceLogFacade().create(lr);
		
		Fusion f = this.getFusionFacade().find(Param.fusion_temperature);
		
		FusionLog fl = new FusionLog();
		fl.setCreationDate(new Timestamp(new Date().getTime()));
		fl.setFusion(f);
		fl.setFusionLogValue(String.valueOf(newData[0].get("avgT").toString()));
		
		this.getFusionLogFacade().create(fl);
		
		fl = this.getFusionLogFacade().find(fl);
		System.out.println("Temperature " + newData[0].get("avgT").toString());
		
		ResourceFusionLog rfl = new ResourceFusionLog();
		rfl.setCreationDate(new Timestamp(new Date().getTime()));
		rfl.setFusionLog(fl);
		rfl.setResourceLog(lr);
		
		this.getResourceFusionLogFacade().create(rfl);

		Drools drools = new Drools();
		drools.requestRepository(lr);

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