
package br.ufabc.impress.mongo.manager;

import com.google.gson.Gson;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
- *
- * @author Fabrizio
- */
public class Tester {


  public static void main(String arg[]) throws UnknownHostException {
		DBManger db = DBManger.getObj("jeb", "localhost", 27017);// ("mention name of db","mongourl",port number)
		
		Map params = new HashMap();
		params.put("test", 2);
		params.put("createdate", "1435125301890");
		String json = new Gson().toJson(params);
		
//		params.put("test", 1);
//		String id = db.addDefault("test", json);
//		System.out.println(id);
		
		String id2 = db.addDefaultMap("test", params);
		System.out.println(id2);
		
//		String id3 = db.getAll("test");
//		System.out.println(id3);
//		
//		String id4 = db.getByCondition("test", params);
//		System.out.println(id4);
//		
//		boolean id5 = db.modifyByCondition("test", json, params);
//		System.out.println(id5);
		
//		List<Boolean> id6 = db.modifyNotOveride("test", json,"558a4635440ac07cab8dd837");
//		System.out.println(id6);

   }
}
