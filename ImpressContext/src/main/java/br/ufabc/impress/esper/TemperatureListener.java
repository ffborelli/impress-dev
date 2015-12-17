package br.ufabc.impress.esper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import br.ufabc.impress.Param;
import br.ufabc.impress.drools.Drools;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.file.File;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.mqtt.MqttPublish;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;


public class TemperatureListener implements UpdateListener {
	
	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private EvalSdpFacade evalSdpFacade;

	public void update(EventBean[] newData, EventBean[] oldData) {

		long startTime = System.currentTimeMillis();

		ResourceLog status = this.getResourceLogFacade().getLastByResource(
				Param.sensor_android);

		Resource r = this.getResourceFacade()
				.find(Param.sensor_avg_temperature);

		ResourceLog lr = new ResourceLog();
		lr.setResourceLogValue(newData[0].get("avgT").toString());
		lr.setResource(r);
		lr.setCreationDate(new Timestamp(new Date().getTime()));

		this.getResourceLogFacade().create(lr);

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
			eval.setModule(Param.module_fusion_temperature);
			eval.setDescription(this.getClass().toString());
			//this.getEvalSdpFacade().create(eval);
			

			//name , start, finish, duration,  duration_mil, module, description
			File f = new File();
			f.write("evalSDP.txt", Param.name_experiment + ";" + String.valueOf(startTime) + ";" + String.valueOf(finishTime) + ";" + String.valueOf(estimatedTime) + ";" + String.valueOf(estimatedTime) + ";" +  Param.module_fusion_temperature + ";" + this.getClass().toString());
		}

		startTime = System.currentTimeMillis();

		Drools drools = new Drools();
		drools.requestRepository(lr, status);

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
			eval.setModule(Param.module_rule_temperature);
			eval.setDescription("Inference " + Param.module_rule_temperature);
			this.getEvalSdpFacade().create(eval);
			System.out.println(Param.module_rule_temperature);
			
			//name , start, finish, duration,  duration_mil, module, description
			File f = new File();
			f.write("evalSDP.txt", Param.name_experiment + ";" + String.valueOf(startTime) + ";" + String.valueOf(finishTime) + ";" + String.valueOf(estimatedTime) + ";" + String.valueOf(estimatedTime) + ";" +  Param.module_rule_temperature + ";" + this.getClass().toString());
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