package br.ufabc.impress.mqtt;

import java.sql.Timestamp;
import java.util.Date;
import br.ufabc.impress.Param;
import br.ufabc.impress.esper.Esper;
import br.ufabc.impress.facade.EvalSdpFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.model.EvalSdp;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.rai.MqttIvan;
import br.ufabc.impress.util.EvalUtil;

/*
 * 
 * Describe the protocol to convert messages in the SDP
 * */
public class SplitMessageMqtt {

	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private EvalSdpFacade evalSdpFacade;

	public static final String del = ";";

	public SplitMessageMqtt(String message) {
		this.message = message;
	}

	private String message;

	public boolean applyProtocolsRules() {

		if (Param.mqtt_ivan) {

			if (message.equalsIgnoreCase("GETUID")) {
				Long max = new EvalSdpFacade().getMaxUid();
				// MqttPublish pu = new MqttPublish(topic, topic, "/ivan/uid",
				// "uid="+max);
				
				//MqttPublish m = new MqttPublish("tcp://localhost:1883", "ivanUid", "/ivan/uid", "uid=" + max);
				MqttPublishModel m = new MqttPublishModel("tcp://localhost:1883", "ivanUid", "/ivan/uid", "uid=" + max);
				MqttIvan.putMessageMqttPublish(m);


			} else {

//				0 -> type
//				1 --> id
//				2 --> message
//				3 --> P1
//				4 --> P2
//				5 --> P3
//				6 --> P4
//				7 --> P5
//				8 --> P6
//				9 --> P7
//				10 --> REPLICATION
//				type=structural;id=1;message=99.738754,63.90224,65.37804,17.712227,87.36452,79.891106,34.8489;P1=14735427300;P2=?;P3=?;P4=?;P5=?;P6=?;P7=?;REP=-1
				
				//String m[] = message.split(del);
				
				//String type[] = m[0].split("=");
				
				String resource = EvalUtil.getParameter("resource", message, del, "=");
				String UID = EvalUtil.getParameter("UID", message, del, "=");
				
				String p1 = EvalUtil.getParameter("P1", message, del, "=");
				String rep = EvalUtil.getParameter("REP", message, del, "=");
				String exp = EvalUtil.getParameter("EXP", message, del, "=");
				
				message = EvalUtil.setTime("P2", message, del, "=");
				String p2 = EvalUtil.getParameter("P2", message, del, "=");
				
				ResourceLog lr = new ResourceLog();
				lr.setResourceLogValue(message);
				lr.setCreationDate(new Timestamp(new Date().getTime()));
				lr.setBornDate(new Date(Long.valueOf(p1)));
				lr.setReplication(Integer.valueOf(rep));
				
				Resource res = getResourceFacade().find(Integer.parseInt(resource));
				lr.setResource(res);
				
				//this.getResourceLogFacade().create(lr);
				
				EvalSdp eval = new EvalSdp();
				eval.setExp(Integer.valueOf(exp));
				eval.setRep(Integer.valueOf(rep));
				eval.setUid(Long.valueOf(UID));
				eval.setP1(new Timestamp(Long.valueOf(p1)));
				eval.setP2(new Timestamp(Long.valueOf(p2)));
				eval.setResource(res.getId());
				eval.setResourceType(res.getResourceType().getId());
				
				this.getResourceLogFacade().create(lr);
				this.getEvalSdpFacade().create(eval);
				
				//this.getEvalSdpFacade().create(eval);
				
				//System.out.println("A");
				Esper.addEventEsper(lr);
				System.out.println("ESPER EVENT HAS BEEN CREATED --> RESOURCE ID" + res.getId());
				


			}
		}

		return true;
	}

	// public void sendMessage(String msg) {
	// // try {
	// //
	// // MqttPublish pub = new MqttPublish(Param.address, "impre" + 11,
	// // Param.topic, msg);
	// //

	// // Thread thread = new Thread(pub);
	// // thread.start();
	// // } catch (Throwable th) {
	// // new Throwable("Drools Error Request Repository", th);
	// // }
	//
	// RestFull rest = new RestFull();
	// try {
	// // rest.sendPost(Param.address_rai_rest, msg);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

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
	
	private EvalSdpFacade getEvalSdpFacade() {
		if (evalSdpFacade == null) {
			evalSdpFacade = new EvalSdpFacade();
		}
		return evalSdpFacade;
	}

}