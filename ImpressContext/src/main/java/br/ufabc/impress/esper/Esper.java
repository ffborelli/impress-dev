package br.ufabc.impress.esper;

import java.util.ArrayList;
import java.util.List;

import br.ufabc.impress.esper.ivan.AirListener;
import br.ufabc.impress.esper.ivan.LightListener;
import br.ufabc.impress.esper.ivan.NoiseListener;
import br.ufabc.impress.esper.ivan.StructuralListener;
import br.ufabc.impress.esper.ivan.TrafficListener;
import br.ufabc.impress.esper.ivan.WasteListener;
import br.ufabc.impress.facade.FusionFacade;
import br.ufabc.impress.model.EsperStatement;
import br.ufabc.impress.model.Fusion;
import br.ufabc.impress.model.ResourceLog;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

public class Esper {

	private static Configuration cepConfig;
	private static EPServiceProvider cep;
	private static EPRuntime cepRT;
	private static EPAdministrator cepAdm;
	private static EPStatement cepStatement;

	private static final String esperType = "DemoEsperType";

	private static FusionFacade fusionFacade;

	private static ArrayList<EsperStatement> arrCepStatement;
	
	private static ArrayList<EPStatement> array = new ArrayList<EPStatement>();
	
	private static EPStatement getListener(EPStatement cepStatement, Fusion f){
		
		
		//LIGHT FUSION
		if (f.getId() == 1){
			cepStatement.addListener(new LightListener());
		}
		//AIR FUSION
		else if (f.getId() == 2){
			cepStatement.addListener(new AirListener());
		}
		//TRAFFIC FUSION
		else if (f.getId() == 3){
			cepStatement.addListener(new TrafficListener());
		}
		//NOISE FUSION
		else if (f.getId() == 4){
			cepStatement.addListener(new NoiseListener());
		}
		//STRUCTURAL FUSION
		else if (f.getId() == 5){
			cepStatement.addListener(new StructuralListener());
		}
		//WASTE FUSION
		else if (f.getId() == 6){
			cepStatement.addListener(new WasteListener());
		}
		else {
			
		}
		
		return cepStatement;
	}
	/*
	 * 
	 * Get all avaliable fusions from database and start it 
	 * */
	public static boolean createArrayEsper() {

		arrCepStatement = new ArrayList<EsperStatement>();

		// The Configuration is meant only as an initialization-time object.
		cepConfig = new Configuration();
		cepConfig.addEventType("DemoEsperType", ResourceLog.class.getName());

		EPServiceProvider cep = EPServiceProviderManager.getProvider(
				"myCEPEngine", cepConfig);
		cepRT = cep.getEPRuntime();

		EPAdministrator cepAdm = cep.getEPAdministrator();

		List<Fusion> listFusion = getFusionFacade().listAll();

		for (int i = 0; i < listFusion.size(); i++) {

			if (listFusion.get(i).isAvaliable() == true) {

				cepStatement = cepAdm.createEPL(listFusion.get(i)
						.getFusionText().toString());
				

				cepStatement = getListener(cepStatement, listFusion.get(i));
				
				cepStatement.start();
				
				EsperStatement es = new EsperStatement();
				es.setIdFusion(listFusion.get(i).getId());
				es.setCepStatement(cepStatement);

				arrCepStatement.add(es);
			}

		}

		return true;
	}

	public static boolean addEsperStream(Fusion f) {

		cepStatement = cepAdm.createEPL(f.getFusionText());
		cepStatement = getListener(cepStatement, f);
		cepStatement.start();
		array.add(cepStatement);

		EsperStatement es = new EsperStatement();

		es.setIdFusion(f.getId());
		es.setCepStatement(cepStatement);

		//arrCepStatement.add(es);

		//f.setRunning(true);

		//getFusionFacade().update(f);

		return true;
	}

	public static int stopEsperStream(Fusion f) {

		int r = -1;

		for (int i = 0; i < arrCepStatement.size(); i++) {
			if (arrCepStatement.get(i).getIdFusion() == f.getId()) {
				arrCepStatement.get(i).getCepStatement().stop();
				arrCepStatement.get(i).getCepStatement().destroy();
				System.out.println("Fusion " + f.getId() + " has stopped");
				r = i;
				break;
			}
		}

		return r;
	}

	public static boolean restartEsperStream(Fusion f) {

		cepStatement = cepAdm.createEPL(f.getFusionText());
		cepStatement.addListener(new TemperatureListener());

		// r is arrayList index
		int r = stopEsperStream(f);
		System.out.println("Fusion Restarted:" + r);
		if (r >= 0) {
			arrCepStatement.get(r).setCepStatement(cepStatement);
		}

		return true;
	}

	public static void startEsper() {
		
		// The Configuration is meant only as an initialization-time object.
		cepConfig = new Configuration();
		cepConfig.addEventType("DemoEsperType", ResourceLog.class.getName());

		EPServiceProvider cep = EPServiceProviderManager.getProvider(
				"myCEPEngine", cepConfig);
		cepRT = cep.getEPRuntime();

		EPAdministrator cepAdm = cep.getEPAdministrator();
		//ArrayList<EPStatement> array = new ArrayList<EPStatement>();
		//Temperature
		EPStatement cepStatement = cepAdm
				.createEPL("select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT" +

						" from "

						+ " DemoEsperType().win:length_batch(3)  where DemoEsperType.resource.uid like \'be8e2ef4-72e9-38b2-a5b2-cb13ab0de545\' or "
						+ "DemoEsperType.resource.uid like \'be8e2ef4-72e9-38b2-a5b2-cb13ab0de545\' "
						+ "  ");

		cepStatement.addListener(new TemperatureListener());
		cepStatement.start();
		
		array.add(cepStatement);


		//BIT A BIT - win:length(5)
		cepStatement = cepAdm
				.createEPL("select  DemoEsperType.resourceLogValue as S1 , * "

						
						+ " from "

						//+ " DemoEsperType().win:time_batch(1 sec) "
						+ " DemoEsperType().win:length_batch(1) "
						//+ " where DemoEsperType.resource.uid like \'6a38e67f-c9fb-3b2c-8428-070612ef4caf\' ");
						+ " where DemoEsperType.resource.uid like \'1\' ");

		cepStatement.addListener(new PresenceListener1());
		cepStatement.start();
		array.add(cepStatement);
		
		//BIT A BIT - win:length(5)
		cepStatement = cepAdm
				.createEPL("select  DemoEsperType.resourceLogValue as S1 , * "

						
						+ " from "

						//+ " DemoEsperType().win:time_batch(1 sec) "
						+ " DemoEsperType().win:length_batch(1) "
						//+ " where DemoEsperType.resource.uid like \'6a38e67f-c9fb-3b2c-8428-070612ef4caf\' ");
						+ " where DemoEsperType.resource.uid like \'2\' ");

		cepStatement.addListener(new PresenceListener2());
		cepStatement.start();
		array.add(cepStatement);
		
		//BIT A BIT - win:length(5)
		cepStatement = cepAdm
				.createEPL("select  DemoEsperType.resourceLogValue as S1 , * "

						
						+ " from "

						//+ " DemoEsperType().win:time_batch(1 sec) "
						+ " DemoEsperType().win:length_batch(1) "
						//+ " where DemoEsperType.resource.uid like \'6a38e67f-c9fb-3b2c-8428-070612ef4caf\' ");
						+ " where DemoEsperType.resource.uid like \'3\' ");

		cepStatement.addListener(new PresenceListener3());
		cepStatement.start();
		array.add(cepStatement);
		
		//BIT A BIT - win:length(5)
		cepStatement = cepAdm
				.createEPL("select  DemoEsperType.resourceLogValue as S1 , * "

						
						+ " from "

						//+ " DemoEsperType().win:time_batch(1 sec) "
						+ " DemoEsperType().win:length_batch(1) "
						//+ " where DemoEsperType.resource.uid like \'6a38e67f-c9fb-3b2c-8428-070612ef4caf\' ");
						+ " where DemoEsperType.resource.uid like \'4\' ");

		cepStatement.addListener(new PresenceListener4());
		cepStatement.start();
		array.add(cepStatement);

		
////		//BIT A BIT - win:length(5)
//		cepStatement = cepAdm
//				.createEPL("select  DemoEsperType.resourceLogValue as S1 , * "
//
//						
//						+ " from "
//
//						//+ " DemoEsperType().win:time_batch(1 sec) "
//						+ " DemoEsperType().win:length_batch(3) "
//						//+ " where DemoEsperType.resource.uid like \'6a38e67f-c9fb-3b2c-8428-070612ef4caf\' ");
//						//+ " where DemoEsperType.resource.uid like \'9a6bde10-789a-3b27-bc7f-5fce9ff7dcb5\' ");
//						+ " where DemoEsperType.resource.uid like \'4\' ");
//		
//		cepStatement.addListener(new UltrasonicListener());
//		cepStatement.start();
//		array.add(cepStatement);
	
	}

	public static void stopEsper() {

		// cepStatement.stop();
		cepAdm.stopAllStatements();
	}

	 public static void cleanDemo() {
	
		List<Fusion> listFusion = getFusionFacade().listAll();

		for (int i = 0; i < listFusion.size(); i++) {

			Fusion u = listFusion.get(i);
			u.setRunning(true);
			
			getFusionFacade().update(u);
		}
	}

	public static void addEventEsper(Object t) {
		cepRT.sendEvent(t);
	}

	public Configuration getCepConfig() {
		return cepConfig;
	}

	public EPServiceProvider getCep() {
		return cep;
	}

	public EPRuntime getCepRT() {
		return cepRT;
	}

	public EPAdministrator getCepAdm() {
		return cepAdm;
	}

	public EPStatement getCepStatement() {
		return cepStatement;
	}

	public String getEsperType() {
		return esperType;
	}

	private static FusionFacade getFusionFacade() {
		if (fusionFacade == null) {
			fusionFacade = new FusionFacade();
		}

		return fusionFacade;
	}

}
