package br.ufabc.impress.esper;

import java.util.List;

import br.ufabc.impress.facade.ContextFacade;
import br.ufabc.impress.facade.FusionFacade;
import br.ufabc.impress.model.Context;
import br.ufabc.impress.model.Fusion;

public class EsperPooling implements Runnable {

	private FusionFacade fusionFacade;
	private ContextFacade contextFacade;

	@Override
	public void run() {

		while (true) {
			
			Context context = this.getContextFacade().getLast();

			if (context.getRunning() == 2){
				
				//Esper.addEsperStream(f);
			}

			this.getContextFacade().update(context);
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			List<Fusion> listFusion = this.getFusionFacade().listAll();
//
//			for (int i = 0; i < listFusion.size(); i++) {
//				if (listFusion.get(i).isRunning() == false) {
//					Esper.addEsperStream(listFusion.get(i));
//				}
//			}
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

	}

	private FusionFacade getFusionFacade() {
		if (fusionFacade == null) {
			fusionFacade = new FusionFacade();
		}
		return fusionFacade;
	}
	
	private ContextFacade getContextFacade(){
		if (contextFacade == null){
			contextFacade = new ContextFacade();
		}
		return contextFacade;
	}

}
