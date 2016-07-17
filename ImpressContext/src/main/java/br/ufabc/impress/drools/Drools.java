package br.ufabc.impress.drools;

import java.util.ArrayList;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.util.DroolsUtil;

public class Drools {
	
//	private static ArrayList<String> buffer = new ArrayList<String>();
	
	private static int buffer[] = new int[16];

	public boolean requestRepository(Object o) {
		
		StatefulKnowledgeSession ksession = null;
		try {
			// load up the knowledge base
			//KnowledgeBase kbase = readKnowledgeBase();
			KnowledgeBase kbase = DroolsUtil.readKnowledgeBase("IMPReSSAgent", "ChangeSet.xml");
			
			SenderDrools s = new SenderDrools();
			
			ksession = kbase.newStatefulKnowledgeSession();
	        ksession.insert(o);
	        ksession.insert(s);
	        //ksession.insert(status);
	        
	        int rules = ksession.fireAllRules();
	        
	        System.out.println("Drools: Fired " + rules + " rules. ");
	        
	        int size = s.getMessages().size();
	        
	        for (int j = 0; j < size; j++) {
	        	String r = s.getMessages().get(j);
	        	String fields[] = r.split(";");
    		
	        	String actuator = fields[1];
	        	String v = fields[2];
	        	//System.out.println(s.getMessages().size() + "ID " + Integer.parseInt(actuator) + "-->Buffer: --> " + buffer[Integer.parseInt(actuator)] + " Drools  :" + Integer.parseInt(v));
	        	if (buffer[Integer.parseInt(actuator)] == Integer.parseInt(v) ){
	        		s.removeMessage(j);
	        		
	        	}
	        	else{
	        		buffer[Integer.parseInt(actuator)] = Integer.parseInt(v);
	        	}
	        }
	        
//	        //check if status of sensor has changed - old
//	        
//	    	for (int j = 0; j < s.getMessages().size(); j++) {
//	    		
//	    		String r1 = s.getMessages().get(j);
//	    		String fields1[] = r1.split(";");
//	    		
//	    		String actuator1 = fields1[1];
//	    		String v1 = fields1[2];
//	    			    		
//	    		
//	    		for (int i = 0; i < buffer.size(); i++) {
//	    			
//		    		String r2 = s.getMessages().get(i);
//		    		String fields2[] = r2.split(";");
//		    		
//		    		String v2 = fields2[2];
//		    		String actuator2 = fields2[1];
//	    			
//	    			if (actuator1.equalsIgnoreCase(actuator2)){
//	    				//it is the same value --> remove from messages
//	    				if (v1.equalsIgnoreCase(v2)){
//	    					s.getMessages().remove(j);	
//	    				}
//	    				
//		    			else{
//		    				//the status has changed
//		    				buffer.remove(i);
//		    				buffer.add(s.getMessages().get(j));
//		   				
//		    			}
//	    			}
//
//	    		}
//	    	}
	
	    	PublishDrools pu = new PublishDrools(s.getMessages());
	    	pu.publish();
	    	
			
		} finally {
			if (ksession != null) {
				ksession.dispose();
				
			}
		}
		
		return false;
	}
	
	public void resetBuffer(){
		for (int i = 0; i < buffer.length ; i++){
			buffer[i] = -1;
		}
	}
	
	public boolean requestRepository(ArrayList<Object> o) {
		
		StatefulKnowledgeSession ksession = null;
		try {
			// load up the knowledge base
			//KnowledgeBase kbase = readKnowledgeBase();
			KnowledgeBase kbase = DroolsUtil.readKnowledgeBase("IMPReSSAgent", "ChangeSet.xml");
			
			SenderDrools s = new SenderDrools();
			
			ksession = kbase.newStatefulKnowledgeSession();
			for (int i = 0; i < o.size(); i++){
				ksession.insert(o.get(i));
			}
	        ksession.insert(s);
	        //ksession.insert(status);
	        
	        int rules = ksession.fireAllRules();
	        
	        System.out.println("Drools: Fired " + rules + " rules. ");
	        
	        int size = s.getMessages().size();
	        
	        for (int j = 0; j < size; j++) {
	        	String r = s.getMessages().get(j);
	        	String fields[] = r.split(";");
    		
	        	String actuator = fields[1];
	        	String v = fields[2];
	        	//System.out.println(s.getMessages().size() + "ID " + Integer.parseInt(actuator) + "-->Buffer: --> " + buffer[Integer.parseInt(actuator)] + " Drools  :" + Integer.parseInt(v));
	        	if (buffer[Integer.parseInt(actuator)] == Integer.parseInt(v) ){
	        		s.removeMessage(j);
	        		
	        	}
	        	else{
	        		buffer[Integer.parseInt(actuator)] = Integer.parseInt(v);
	        	}
	        }
	        
//	        //check if status of sensor has changed - old
//	        
//	    	for (int j = 0; j < s.getMessages().size(); j++) {
//	    		
//	    		String r1 = s.getMessages().get(j);
//	    		String fields1[] = r1.split(";");
//	    		
//	    		String actuator1 = fields1[1];
//	    		String v1 = fields1[2];
//	    			    		
//	    		
//	    		for (int i = 0; i < buffer.size(); i++) {
//	    			
//		    		String r2 = s.getMessages().get(i);
//		    		String fields2[] = r2.split(";");
//		    		
//		    		String v2 = fields2[2];
//		    		String actuator2 = fields2[1];
//	    			
//	    			if (actuator1.equalsIgnoreCase(actuator2)){
//	    				//it is the same value --> remove from messages
//	    				if (v1.equalsIgnoreCase(v2)){
//	    					s.getMessages().remove(j);	
//	    				}
//	    				
//		    			else{
//		    				//the status has changed
//		    				buffer.remove(i);
//		    				buffer.add(s.getMessages().get(j));
//		   				
//		    			}
//	    			}
//
//	    		}
//	    	}
	
	    	PublishDrools pu = new PublishDrools(s.getMessages());
	    	pu.publish();
	    	
			
		} finally {
			if (ksession != null) {
				ksession.dispose();
				
			}
		}
		
		return false;
	}
	
	/**
	 * Load KnowledgeBase using KnowledgeAgent configured with accompanying
	 * changeset xml
	 * 
	 * @return A KnowledgeBase
	 */
//	private static KnowledgeBase readKnowledgeBase() {
//
//		// Resource scanning is not on by default, it's a service and must be
//		// started, the same is for notification. This can be done via the
//		// ResourceFactory.
//		// http://docs.jboss.org/jbpm/v5.1/javadocs/org/drools/agent/KnowledgeAgentFactory.html
//		// ResourceFactory.getResourceChangeNotifierService().start();
//		// ResourceFactory.getResourceChangeScannerService().start();
//
//		// ResourceChangeScannerConfiguration sconf = ResourceFactory
//		// .getResourceChangeScannerService()
//		// .newResourceChangeScannerIntegerConfiguration();
//		// sconf.setProperty("drools.resource.scanner.interval", "2");
//		//
//		// ResourceFactory.getResourceChangeScannerService().configure(sconf);
//		// ResourceFactory.getResourceChangeScannerService().start();
//		// ResourceFactory.getResourceChangeNotifierService().start();
//
//		KnowledgeAgent kagent = KnowledgeAgentFactory
//				.newKnowledgeAgent("IMPReSSAgent");
//		Resource changeset = ResourceFactory
//				.newClassPathResource("temperature-changeset.xml");
//		kagent.applyChangeSet(changeset);
//		KnowledgeBase kbase = kagent.getKnowledgeBase();
//		kagent.dispose();
//		return kbase;
//	}

}
