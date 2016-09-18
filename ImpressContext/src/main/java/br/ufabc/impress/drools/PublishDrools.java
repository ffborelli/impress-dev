package br.ufabc.impress.drools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.lowagie.text.SplitCharacter;

import br.ufabc.impress.Param;
import br.ufabc.impress.RestFull;
import br.ufabc.impress.facade.FusionRuleLogFacade;
import br.ufabc.impress.facade.ResourceActionTypeFacade;
import br.ufabc.impress.facade.ResourceFacade;
import br.ufabc.impress.facade.ResourceLogFacade;
import br.ufabc.impress.facade.RuleActionLogFacade;
import br.ufabc.impress.facade.RuleFacade;
import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceActionType;
import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.model.Rule;
import br.ufabc.impress.model.RuleActionLog;
import br.ufabc.impress.mqtt.MqttPublish;
import br.ufabc.impress.util.EvalUtil;

public class PublishDrools {

	ArrayList<String> messages;

	private ResourceFacade resourceFacade;
	private ResourceLogFacade resourceLogFacade;
	private ResourceActionTypeFacade resourceActionTypeFacade;
	private RuleFacade ruleFacade;
	private RuleActionLogFacade ruleActionLogFacade;
	private FusionRuleLogFacade fusionRuleLogFacade;

	public PublishDrools(ArrayList<String> messages) {
		this.messages = messages;
	}

	/*
	 * protocol operation/resource/action/resource_action_type/rule
	 * 
	 * 0 --> operation 1 --> resource 2 --> action 3 --> resource_action_type 4
	 * --> rule 5--> rule_action_log
	 */
	public void publish() {

		if (Param.mqtt_ivan == true) {
			
			System.out.println("SENDING MESSAGE TO IVAN");
			//if (messages.isEmpty() == false) {

				for (int i = 0; i < messages.size(); i++) {

					//if (!messages.get(i).equalsIgnoreCase("-1")) {

						String fields[] = messages.get(i).split(";");

						//String value = fields[0] + ";" + fields[1] + ";" + fields[fields.length - 2] + ";" + fields[fields.length - 1]; 
						
						String id_resource_str[] = fields[1].split("=");
						
						int id_resource = Integer.parseInt(id_resource_str[1]);
						
						Resource r;
						MqttPublish m;

						ResourceLog lr = new ResourceLog();

						//lr.setResourceLogValue(value);
						lr.setResourceLogValue(messages.get(i));
						r = this.getResourceFacade().find(id_resource);

						//DANGER
						ResourceActionType rat = this
								.getResourceActionTypeFacade().find(1);

						
						Rule rule = this.getRuleFacade().find(id_resource);


						RuleActionLog ral = new RuleActionLog();
						ral.setCreationDate(new Timestamp(new Date().getTime()));
						ral.setResourceActionType(rat);
						ral.setRule(rule);
						ral.setResource(r);
						ral.setTracker(false);

						this.getRuleActionLogFacade().create(ral);

						FusionRuleLog frl = new FusionRuleLog();
						frl.setCreationDate(new Timestamp(new Date().getTime()));
						frl.setRuleActionLog(ral);

						this.getFusionRuleLogFacade().create(frl);

						lr.setResource(r);
						lr.setCreationDate(new Timestamp(new Date().getTime()));

						this.getResourceLogFacade().create(lr);
						
						m = new MqttPublish(r.getMqttAddress(), "ivan"+r.getId(), "/ivan/demo", messages.get(i));
						//m = new MqttPublish(r.getMqttAddress(), "ivan"+r.getId(), "/ivan/demo", value);
						Thread t = new Thread(m);
						t.start();
					//}
				//}

			}
		} else {

			if (messages.isEmpty() == false) {

				// LogResourceFacade logResourceFacade = new
				// LogResourceFacade();

				for (int i = 0; i < messages.size(); i++) {

					if (!messages.get(i).equalsIgnoreCase("-1")) {

						String fields[] = messages.get(i).split(";");

						// Resource r = new Resource();
						// r.setId(Integer.parseInt(fields[1]));
						// System.out.println("SEND " + messages.get(i) + " " +
						// fields[0] + " " + fields[1] + " "+ fields[2] );

						Resource r;
						// MqttPublish m;

						RestFull rest;

						ResourceLog lr = new ResourceLog();
						if (fields[0].equals("4")) {
							// lr.setLogResourceValue(fields[1]);
							lr.setResourceLogValue(fields[1]);
							r = this.getResourceFacade().find(
									Param.sensor_android);

							// m = new MqttPublish(Param.address, "id1",
							// "demo/android", messages.get(i));

						} else {
							lr.setResourceLogValue(fields[2]);
							r = this.getResourceFacade().find(
									Integer.parseInt(fields[1]));
							// System.out.println("RAL");
							// m = new MqttPublish(Param.address, "id1",
							// Param.topic_publish, messages.get(i));

							ResourceActionType rat = this
									.getResourceActionTypeFacade().find(
											Integer.parseInt(fields[3]));

							Rule rule = this.getRuleFacade().find(
									Integer.parseInt(fields[4]));

							// Resource resource =
							// this.getResourceFacade().find(
							// Integer.parseInt(fields[4]));

							RuleActionLog ral = new RuleActionLog();
							ral.setCreationDate(new Timestamp(new Date()
									.getTime()));
							ral.setResourceActionType(rat);
							ral.setRule(rule);
							ral.setResource(r);
							ral.setTracker(false);

							this.getRuleActionLogFacade().create(ral);

							FusionRuleLog frl = new FusionRuleLog();
							frl.setCreationDate(new Timestamp(new Date()
									.getTime()));
							frl.setRuleActionLog(ral);

							this.getFusionRuleLogFacade().create(frl);

							if (r.isReserved() == false) {

								rest = new RestFull();

								try {
									// auth
									String url = Param.address_rai_rest
											+ r.getUid() + "/authorize_access ";
									rest.sendPost(url);

									if (fields[2].equalsIgnoreCase("0")) {

										// act
										rest.sendGet(Param.address_rai_rest
												+ r.getUid() + "/turnOff");
									} else {
										rest.sendGet(Param.address_rai_rest
												+ r.getUid() + "/turnOn");

										// rest.sendPut(Param.address_rai_rest
										// + r.getUid() + "/rgb");

									}

								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						}
						lr.setResource(r);
						lr.setCreationDate(new Timestamp(new Date().getTime()));

						this.getResourceLogFacade().create(lr);

						// Thread thread = new Thread(m);
						// thread.start();
					}
				}
			}
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

	private ResourceActionTypeFacade getResourceActionTypeFacade() {
		if (resourceActionTypeFacade == null) {
			resourceActionTypeFacade = new ResourceActionTypeFacade();
		}
		return resourceActionTypeFacade;
	}

	private RuleFacade getRuleFacade() {
		if (ruleFacade == null) {
			ruleFacade = new RuleFacade();
		}
		return ruleFacade;
	}

	private RuleActionLogFacade getRuleActionLogFacade() {
		if (ruleActionLogFacade == null) {
			ruleActionLogFacade = new RuleActionLogFacade();
		}
		return ruleActionLogFacade;
	}

	private FusionRuleLogFacade getFusionRuleLogFacade() {
		if (fusionRuleLogFacade == null) {
			fusionRuleLogFacade = new FusionRuleLogFacade();
		}
		return fusionRuleLogFacade;
	}
}
