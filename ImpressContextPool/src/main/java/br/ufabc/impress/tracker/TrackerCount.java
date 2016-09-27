package br.ufabc.impress.tracker;

import java.util.List;

import br.ufabc.impress.facade.ContextFacade;
import br.ufabc.impress.model.Context;

public class TrackerCount implements  Runnable{

	private ContextFacade contextFacade;
	@Override
	public void run() {
		
		while(true){
			
			List<Context> listContext = this.getContextFacade().listAll();
			
			for (int i = 0; i < listContext.size(); i++){
				for (int j = 0; j < listContext.size(); j++){
					if (listContext.get(i).getContextSequence().equalsIgnoreCase(listContext.get(j).getContextSequence())){
						listContext.get(j).setContextCount(-1);
						int c = listContext.get(i).getContextCount();
						if (c > -1){
							listContext.get(i).setContextCount(c++);
						}
					}
						
				}
			}
			
			for (int i = 0; i < listContext.size(); i++){
				Context c = listContext.get(i);
				if (c.getContextCount() > 0){
					this.getContextFacade().update(c);
				}
				else {
					this.getContextFacade().delete(c);
				}
			}

		}
		
	}

	private ContextFacade getContextFacade(){
		if (contextFacade == null){
			contextFacade = new ContextFacade();
		} 
		return contextFacade;
	}
	
}
