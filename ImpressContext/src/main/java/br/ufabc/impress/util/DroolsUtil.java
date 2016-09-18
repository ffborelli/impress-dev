package br.ufabc.impress.util;

import org.drools.KnowledgeBase;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;

public class DroolsUtil {
	
	/**
	 * Load KnowledgeBase using KnowledgeAgent configured with accompanying
	 * changeset xml
	 * 
	 * @return A KnowledgeBase
	 */
	public static KnowledgeBase readKnowledgeBase(String agentName, String changesetName) {

		// Resource scanning is not on by default, it's a service and must be
		// started, the same is for notification. This can be done via the
		// ResourceFactory.
		// http://docs.jboss.org/jbpm/v5.1/javadocs/org/drools/agent/KnowledgeAgentFactory.html
		// ResourceFactory.getResourceChangeNotifierService().start();
		// ResourceFactory.getResourceChangeScannerService().start();

		// ResourceChangeScannerConfiguration sconf = ResourceFactory
		// .getResourceChangeScannerService()
		// .newResourceChangeScannerConfiguration();
		// sconf.setProperty("drools.resource.scanner.interval", "2");
		//
		// ResourceFactory.getResourceChangeScannerService().configure(sconf);
		// ResourceFactory.getResourceChangeScannerService().start();
		// ResourceFactory.getResourceChangeNotifierService().start();

//		KnowledgeAgent kagent = KnowledgeAgentFactory
//				.newKnowledgeAgent("IMPReSSAgent");
		
		KnowledgeAgent kagent = KnowledgeAgentFactory
				.newKnowledgeAgent(agentName);
		
//		Resource changeset = ResourceFactory
//				.newClassPathResource("temperature-changeset.xml");
		
		Resource changeset = ResourceFactory
				.newClassPathResource(changesetName);
		
		kagent.applyChangeSet(changeset);
		KnowledgeBase kbase = kagent.getKnowledgeBase();
		kagent.dispose();
		return kbase;
	}
	
//    private static KnowledgeBase readKnowledgeBase() {
//		KnowledgeAgent kagent = KnowledgeAgentFactory.newKnowledgeAgent("Agent");       
//		Resource changeset = ResourceFactory.newClassPathResource("ChangeSet.xml");
//		kagent.applyChangeSet(changeset);
//		KnowledgeBase kbase = kagent.getKnowledgeBase();
//		kagent.dispose();
//		return kbase;
//	}

}
