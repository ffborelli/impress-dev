package br.ufabc.impress.util;

import java.util.List;

import br.ufabc.impress.facade.ContextFacade;
import br.ufabc.impress.model.Context;

public class ContextUtil {
	
	public static void clean(){
		ContextFacade contextFacade = new ContextFacade();
		
		List<Context> listContext = contextFacade.listAll();
		
		for (int i = 0; i < listContext.size(); i++){
			if (listContext.get(i).getContextCount() == 2){
				Context c = listContext.get(i);
				c.setRunning(0);
				contextFacade.update(c);
			}
		}
	}
	

}
