package br.ufabc.impress.drools;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.util.DroolsUtil;

public class Drools {

	public boolean requestRepository(Object o, ResourceLog status) {
		
		StatefulKnowledgeSession ksession = null;
		try {
			// load up the knowledge base
			//KnowledgeBase kbase = readKnowledgeBase();
			KnowledgeBase kbase = DroolsUtil.readKnowledgeBase("IMPReSSAgent", "ChangeSet.xml");
			
			SenderDrools s = new SenderDrools();
			
			ksession = kbase.newStatefulKnowledgeSession();
	        ksession.insert(o);
	        ksession.insert(s);
	        ksession.insert(status);
	        
	        int rules = ksession.fireAllRules();
	        
	        System.out.println("Drools: Fired " + rules + " rules. ");
	        
	        PublishDrools pu = new PublishDrools(s.getMessages());
	        pu.publish();
			
			// JOptionPane.showMessageDialog(null, message, "Result",
			// JOptionPane.INFORMATION_MESSAGE);
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
