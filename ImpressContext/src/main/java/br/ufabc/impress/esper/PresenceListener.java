package br.ufabc.impress.esper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import br.ufabc.impress.Param;
import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.mqtt.MqttPublish;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class PresenceListener implements UpdateListener {
	
	
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private EvalSdpFacade evalSdpFacade;

	public void update(EventBean[] newData, EventBean[] oldData) {

		 //long startTime = System.nanoTime();  
		long startTime = System.currentTimeMillis();

		int count = 0;

		//if (newData[0].get("S5") == null) {

			//if (newData[0].get("S1") != null) {
				count = Integer.parseInt(newData[0].get("S1").toString());
			//}

//			if (newData[0].get("S2") != null) {
//				count += 2 * Integer.parseInt(newData[0].get("S2").toString());
//			}
//
//			if (newData[0].get("S3") != null) {
//				count += 4 * Integer.parseInt(newData[0].get("S3").toString());
//			}
//
//			if (newData[0].get("S4") != null) {
//				count += 8 * Integer.parseInt(newData[0].get("S4").toString());
//			}
			// else count = 1;

		//}

		System.out.println("SUM " + count);
				
		Resource r = this.getResourceFacade().find(Param.sensor_sum_presence);

		// System.out.println(newData[0].get("S1").toString());
		// System.out.println(newData[0].get("S2").toString());
		// System.out.println(newData[0].get("S3").toString());
		// System.out.println(newData[0].get("S4").toString());
		// System.out.println(newData[0].get("S5").toString());

		ResourceLog lr = new ResourceLog();
		lr.setResourceLogValue(String.valueOf(count));
		lr.setResource(r);
		lr.setCreationDate(new Timestamp(new Date().getTime()));
		
		this.getResourceLogFacade().create(lr);

		// get status of button
		//LogResourceFacade logResourceFacade = new LogResourceFacade();
		//logResourceFacade.create(lr);

		ResourceLog status = this.getResourceLogFacade().getLastByResource(Param.sensor_android);
		
		 long finishTime = System.currentTimeMillis();
		 
		 long estimatedTime = finishTime - startTime;

		if (Param.is_eval) {
			// eval time
			EvalSdp eval = new EvalSdp();
			eval.setStart(new Timestamp(new Date().getTime()));
			eval.setFinish(new Timestamp(finishTime));
			eval.setDuration(new Timestamp(estimatedTime));
			eval.setDurationMil(estimatedTime);
			eval.setName(Param.name_experiment);
			eval.setModule(Param.module_fusion_presence);
			eval.setDescription(this.getClass().toString());
			new EvalSdpFacade().create(eval);
		}

		 startTime = System.currentTimeMillis();    
			
		 Drools drools = new Drools();
		 drools.requestRepository(lr,status);
		 
		 finishTime = System.currentTimeMillis();
		 estimatedTime = finishTime - startTime;
		 
		if (Param.is_eval) {
			// eval time
			//System.out.println(Param.module_rule_presence);
			EvalSdp eval = new EvalSdp();
			eval.setStart(new Timestamp(startTime));
			eval.setFinish(new Timestamp(finishTime));
			eval.setDuration(new Timestamp(estimatedTime));
			eval.setDurationMil(estimatedTime);
			eval.setName(Param.name_experiment);
			eval.setModule(Param.module_rule_presence);
			eval.setDescription("Inference " + Param.module_rule_presence);
			new EvalSdpFacade().create(eval);
			//System.out.println(Param.module_rule_presence);
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
	
	private EvalSdpFacade getEvalSdpFacade(){
		//if (evalSdpFacade == null){
			evalSdpFacade = new EvalSdpFacade();
		//}
		return evalSdpFacade;
	}
}