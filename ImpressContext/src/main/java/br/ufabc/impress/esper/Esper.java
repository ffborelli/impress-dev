package br.ufabc.impress.esper;

import java.util.ArrayList;

import br.ufabc.impress.Param;
import br.ufabc.impress.facade.FusionFacade;
import br.ufabc.impress.model.EsperStatement;
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

	//private static ArrayList<EsperStatement> arrCepStatement;

//	public static boolean createConfigEsper() {
//
//		List<Fusion> fusionList = getFusionFacade().listAll();
//
//		// if (cepStr.equalsIgnoreCase("") || cepStr == null)
//		// cepStr = fusionList.get(0).getCep();
//
//		// if (esperType.equalsIgnoreCase("") || esperType == null)
//		// esperType = fusionList.get(0).getEventType();
//
//		return true;
//	}

//	public static boolean createArrayEsper() {
//
//		arrCepStatement = new ArrayList<EsperStatement>();
//
//		List<Fusion> listFusion = getFusionFacade().listAll();
//
//		for (int i = 0; i < listFusion.size(); i++) {
//
//			cepStatement = cepAdm.createEPL(listFusion.get(i).getDescription()
//					.toString());
//			cepStatement.addListener(new DemoListener());
//
//			// if (listFusion.get(i).getId() == 7 || listFusion.get(i).getId()
//			// == 6){
//			// cepStatement.addListener(new TemperatureTimeListener());
//			// }
//			// else{
//			// cepStatement.addListener(new TemperatureListener());
//			// }
//
//			EsperStatement es = new EsperStatement();
//			es.setIdFusion(listFusion.get(i).getId());
//			es.setCepStatement(cepStatement);
//
//			arrCepStatement.add(es);
//			//
//			// DemoUtil demoUtil = new DemoUtil();
//			// demoUtil.saveLog("Esper ID " + listFusion.get(i).getId()
//			// + " Started");
//			// Fusion tmp = listFusion.get(i);
//			// tmp.setIsRunning(true);
//			//
//			// getFusionFacade().update(tmp);
//		}
//
//		return true;
//	}

	// public static boolean addEsperStream(Fusion f) {
	//
	// cepStatement = cepAdm.createEPL(f.getDescription());
	// cepStatement.addListener(new TemperatureListener());
	//
	// EsperStatement es = new EsperStatement();
	//
	// es.setIdFusion(f.getId());
	// es.setCepStatement(cepStatement);
	//
	// arrCepStatement.add(es);
	//
	// DemoUtil demoUtil = new DemoUtil();
	// demoUtil.saveLog("Esper ID " + f.getId() + " Started");
	//
	// f.setIsRunning(true);
	//
	// getFusionFacade().update(f);
	//
	// return true;
	// }

	// public static int stopEsperStream(int idFusion) {
	//
	// int r = -1;
	//
	// for (int i = 0; i < arrCepStatement.size(); i++) {
	// if (arrCepStatement.get(i).getIdFusion() == idFusion) {
	// arrCepStatement.get(i).getCepStatement().stop();
	// arrCepStatement.get(i).getCepStatement().destroy();
	// System.out.println("Fusion " + idFusion +" stop");
	// r = i;
	// break;
	// }
	// }
	//
	// return r;
	// }

	// public static boolean restartEsperStream(Fusion f) {
	//
	// cepStatement = cepAdm.createEPL(f.getDescription());
	// //cepStatement.addListener(new TemperatureListener());Long
	//br.ufabc
	// if (f.getId() == 7) {
	// cepStatement.addListener(new Criteria7Listener());
	// } else if (f.getId() == 6) {
	// cepStatement.addListener(new Criteria6Listener());
	// } else if (f.getId() == 8) {
	// cepStatement.addListener(new Criteria8Listener());
	// } else if (f.getId() == 1) {
	// cepStatement.addListener(new Criteria1Listener());
	// }
	// else {
	// cepStatement.addListener(new TemperatureListener());
	// }
	//
	// // r is arrayList index
	// int r = stopEsperStream(f.getId());
	// System.out.println("R:" + r);
	// if (r >= 0) {
	// arrCepStatement.get(r).setCepStatement(cepStatement);
	// DemoUtil demoUtil = new DemoUtil();
	// demoUtil.saveLog("Esper ID " + f.getId() + " REstarted");
	// }
	//
	// f.setIsUpdated(false);
	// getFusionFacade().update(f);
	//
	// return true;//		es = new EsperStatement();
//	es.setIdFusion(2);
//	es.setCepStatement(cepStatement);
//	
//	arrCepStatement.add(es);
	// }

	// public Esper(String cepStr, String stream, String esperType, String
	// idFusion){
	// this.cepStr = cepStr;
	// this.stream=stream;
	// this.esperType=esperType;
	// this.idFusion = idFusion;
	// }

	public static void startEsper() {

		//arrCepStatement = new ArrayList<EsperStatement>();

		//Esper 1
		
		// The Configuration is meant only as an initialization-time object.
		cepConfig = new Configuration();
		cepConfig.addEventType("DemoEsperType", ResourceLog.class.getName());

		EPServiceProvider cep = EPServiceProviderManager.getProvider(
				"myCEPEngine", cepConfig);
		cepRT = cep.getEPRuntime();

		EPAdministrator cepAdm = cep.getEPAdministrator();
		ArrayList<EPStatement> array = new ArrayList<EPStatement>();
		//Temperature
		EPStatement cepStatement = cepAdm
				.createEPL("select  *, avg( cast(DemoEsperType.resourceLogValue, double) ) as avgT" +

						" from "

						+ " DemoEsperType().win:length_batch(" + Param.esper + ")  where DemoEsperType.resource.id = 5 or DemoEsperType.resource.id = 6"
						+ "  ");

		cepStatement.addListener(new TemperatureListener());
		//cepStatement.start();
		//array.add(cepStatement);


		
//		cepStatement = cepAdm
//				.createEPL("select  sum(cast(DemoEsperType.logResourceValue,int)) as sumV  , * "
//						
//						+ " from "
//
//						+ " DemoEsperType().win:time(5 sec)  "
//						+ " where DemoEsperType.resource.id = 1 or DemoEsperType.resource.id = 2 or DemoEsperType.resource.id = 3 or DemoEsperType.resource.id = 4");

		//BIT A BIT - win:length(5)
//		EPStatement cepStatement2 = cepAdm
//				.createEPL("select  DemoEsperType.resourceLogValue as S1 , * "
//
//						
//						+ " from "
//
//						//+ " DemoEsperType().win:time_batch(3 sec) "
//						+ " DemoEsperType().win:length_batch(24) "
//						+ " where DemoEsperType.resource.id = 16 ");
//
//		cepStatement2.addListener(new PresenceListener());
		//cepStatement2.start();
		//array.add(cepStatement);
		/* TEMPERATURE
		cepStatement = cepAdm
				.createEPL("select  case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 1 then 1 end as S1 , "
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 2 then 1 end as S2 , "
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 3 then 1 end as S3 , "
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 4 then 1 end as S4 , "
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 0 and DemoEsperType.resource.id = 1 and DemoEsperType.resource.id = 2 and DemoEsperType.resource.id = 3 and DemoEsperType.resource.id = 4 then 1 end as S5,"
						+ " * "
						
						+ " from "

						+ " DemoEsperType().win:time(1 sec) "
						+ " where DemoEsperType.resource.id = 1 or DemoEsperType.resource.id = 2 or DemoEsperType.resource.id = 3 or DemoEsperType.resource.id = 4");

		cepStatement.addListener(new PresenceListener());
		array.add(cepStatement);
		*/
		/*
		cepStatement = cepAdm
				.createEPL("select  case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 1 then 1 end as S1 , "
						
						+ " * "
						
						+ " from "

						+ " DemoEsperType().win:time(1 sec) "
						+ " where DemoEsperType.resource.id = 1");

		cepStatement.addListener(new PresenceListener());
		array.add(cepStatement);
		
		cepStatement = cepAdm
				.createEPL("select  "
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 2 then 1 end as S2 , "
						
						+ " * "
						
						+ " from "

						+ " DemoEsperType().win:time(1 sec) "
						+ " where DemoEsperType.resource.id = 2");

		cepStatement.addListener(new PresenceListener());
		array.add(cepStatement);
		
		cepStatement = cepAdm
				.createEPL("select "  
						
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 3 then 1 end as S3 , "
			
						+ " * "
						
						+ " from "

						+ " DemoEsperType().win:time(1 sec) "
						+ " where DemoEsperType.resource.id = 3");

		cepStatement.addListener(new PresenceListener());
		array.add(cepStatement);
		
		cepStatement = cepAdm
				.createEPL("select  "
						
						
						+ "			case when cast(DemoEsperType.logResourceValue,int) = 1 and DemoEsperType.resource.id = 4 then 1 end as S4 , "
						
						+ " * "
						
						+ " from "

						+ " DemoEsperType().win:time(1 sec) "
						+ " where DemoEsperType.resource.id = 4");

		cepStatement.addListener(new PresenceListener());
		array.add(cepStatement);
		
		*/
		//STATUS
//		cepStatement = cepAdm
//				.createEPL("select case when (cast(DemoEsperType.resourceLogValue,int) = 1 or cast(DemoEsperType.resourceLogValue,int) = 0) and DemoEsperType.resource.id = 13  "
//						+ 					"then cast(DemoEsperType.resourceLogValue,int)"
//						+ 			"end"
//						+ " as status  , * "
//						
//						+ " from "
//
//						+ " DemoEsperType().win:time(6 sec)  "
//						+ " where DemoEsperType.resource.id = 13");
//
//		cepStatement.addListener(new StatusListener());
//		array.add(cepStatement);
		
		
		
		
		
		
		
		
//		es = new EsperStatement();
//		es.setIdFusion(2);
//		es.setCepStatement(cepStatement);
//		
//		arrCepStatement.add(es);
		
		
		//-- presence sensor
//		LogResourceFacade logResourceFacade = new LogResourceFacade();
//		LogResource l = logResourceFacade.find(13);
		
		/*
		 * P1 -- pelo menos um sensor de presença ligado
		 * 
		 * P2 -- todos os sensores desligados
		 * */
		
//		cepStatement = cepAdm
//				.createEPL("select case when cast(DemoEsperType.logResourceValue,int) = 1 and (DemoEsperType.resource.id = 1 or DemoEsperType.resource.id = 2 or DemoEsperType.resource.id = 3 or DemoEsperType.resource.id = 4 )  " 
//						+ 					" then 1 "
//						+ 				"end as P1 , " +
//						
//						"case when cast(DemoEsperType.logResourceValue,int) = 0 and (DemoEsperType.resource.id = 1 and DemoEsperType.resource.id = 2 and DemoEsperType.resource.id = 3 and DemoEsperType.resource.id = 4 )  " 
//						+ 					" then 1 "
//						+ 				"end as P2 , " +
//						
//						"case when cast(DemoEsperType.logResourceValue,int) = 1 " 
//						+ 					" then DemoEsperType.id "
//						+ 				"end as T3 , " +
//						
//						"case when cast(DemoEsperType.logResourceValue,int) = 4 " 
//						+ 					" then DemoEsperType.id "
//						+ 				"end as T4  " +
//						
//						" from "
//
//						+ " DemoEsperType().win:time_batch(5 sec)  "
//						+ " where DemoEsperType.id = 1 or DemoEsperType.id = 2 or DemoEsperType.id = 3 or DemoEsperType.id = 4");
//
//		cepStatement.addListener(new DemoListener());
//		
//		es = new EsperStatement();
//		es.setIdFusion(3);
//		es.setCepStatement(cepStatement);
//		
//		arrCepStatement.add(es);

		// cepStatement = cepAdm.createEPL(stream);
		//
		// cepStatement.addListener(new TemperatureListener());

		// DemoUtil.saveLog("Esper ID " + idFusion + " Started");

		// EPStatement cepStatement2 = cepAdm.createEPL("select * from "
		// + "StockTick2(symbol='UFABC').win:length(8) "
		// + "having avg(price) > 7.0");

		// EPStatement cepStatement2 = cepAdm.createEPL(stream2);
		// cepStatement2.addListener(new TemperatureTimeListener());

		// System.out.println("Esper ID " + idFusion + " Started");
		// DemoUtil.saveLog("Esper ID " + idFusion2 + " Started");
	}

	public static void stopEsper() {

		// cepStatement.stop();
		cepAdm.stopAllStatements();
	}

	// public static void cleanDemo() {
	//
	// List<Fusion> listFusion = getFusionFacade().listAll();
	//
	// for (int i = 0; i < listFusion.size(); i++) {
	//
	// Fusion u = listFusion.get(i);
	// u.setIsRunning(false);
	// u.setIsUpdated(false);
	// getFusionFacade().update(u);
	// }
	// }

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
