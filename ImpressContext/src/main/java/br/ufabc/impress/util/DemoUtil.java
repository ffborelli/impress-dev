//package eu.com.impress.util;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//import eu.com.impress.dao.DemoLogDAO;
//import eu.com.impress.facade.DemoLogFacade;
//import eu.com.impress.model.DemoLog;
//
//public class DemoUtil {
//	
//	private DemoLogFacade demoLogFacade;
//	
//	public void saveLog(String text){
//	
//		if (demoLogFacade == null)
//			demoLogFacade = new DemoLogFacade();
//		
//		DemoLog demo = new DemoLog();
//		demo.setLog(text);
//		demo.setDataInsert(new Timestamp(new Date().getTime()));
//		
//		demoLogFacade.create(demo);
//	}
//
//}
