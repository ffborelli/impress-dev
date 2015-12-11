package br.ufabc.impress.drools;

import java.util.ArrayList;

import org.drools.KnowledgeBase;
import org.drools.runtime.StatefulKnowledgeSession;

import br.ufabc.impress.model.ResourceLog;
import br.ufabc.impress.util.DroolsUtil;

public class TemplateDrools {

	public boolean requestRepository(ArrayList<Object> arr) {
		
		StatefulKnowledgeSession ksession = null;
		try {
			// load up the knowledge base
			//KnowledgeBase kbase = readKnowledgeBase();
			KnowledgeBase kbase = DroolsUtil.readKnowledgeBase("IMPReSSAgent", "ChangeSet.xml");
			
			SenderDrools s = new SenderDrools();
			
			ksession = kbase.newStatefulKnowledgeSession();

			for (int i = 0; i < arr.size();i++){
				ksession.insert(arr.get(i));
			}
			ksession.insert(s);
	        
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
		
		return true;
	}

}
