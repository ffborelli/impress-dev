package br.ufabc.impress.esper;

import java.sql.Timestamp;
import java.util.Date;

import br.ufabc.impress.Param;
import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class StatusListener implements UpdateListener {
	
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private EvalSdpFacade evalSdpFacade;

	public void update(EventBean[] newData, EventBean[] oldData) {

		 System.out.println("ESPER : status -->" + newData[0].get("status") );
		 
		 //long startTime = System.nanoTime();  
		 long startTime = System.currentTimeMillis();

		 //Resource r = new Resource();
		 //r.setId(Param.sensor_android);
		 		 
		 Resource r = this.getResourceFacade().find(Param.sensor_android);
 
		 ResourceLog lr = new ResourceLog();
		 lr.setResourceLogValue(newData[0].get("status").toString());
		 lr.setResource(r);
		 lr.setCreationDate(new Timestamp(new Date().getTime()));
		 
		 this.getResourceLogFacade().create(lr);
		 
		 //get status of button
//		 LogResourceFacade logResourceFacade = new LogResourceFacade();
//		 LogResource status = logResourceFacade.getLastByResource(Param.sensor_android);
		 
		 //status of sensors
		 ResourceLog status = this.getResourceLogFacade().find(Param.sensor_sum_presence);


		// long finishTime = System.nanoTime();
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
			eval.setModule(Param.module_fusion_status);
			eval.setDescription(this.getClass().toString());
			this.getEvalSdpFacade().create(eval);
		}
		 
	     startTime = System.currentTimeMillis();
			
		 Drools drools = new Drools();
		 drools.requestRepository(lr,status);
		 
		 //long finishTime = System.nanoTime();  
		 finishTime = System.currentTimeMillis();
		 
		 estimatedTime = finishTime - startTime;
		 
		if (Param.is_eval) {
			// eval time
			EvalSdp eval = new EvalSdp();
			eval.setStart(new Timestamp(startTime));
			eval.setFinish(new Timestamp(finishTime));
			eval.setDuration(new Timestamp(estimatedTime));
			eval.setDurationMil(estimatedTime);
			eval.setName(Param.name_experiment);
			eval.setModule(Param.module_rule_status);
			eval.setDescription("Inference " + Param.module_rule_status);
			this.getEvalSdpFacade().create(eval);
			//System.out.println(Param.module_rule_status);
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