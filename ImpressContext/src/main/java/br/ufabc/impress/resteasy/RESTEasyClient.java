package br.ufabc.impress.resteasy;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;


public class RESTEasyClient {

//	public static void main(String[] args) {

//		Student st = new Student("Captain", "Hook", 10, 12);
//
//		try {
//			ResteasyClient client = new ResteasyClientBuilder().build();
//
//			ResteasyWebTarget target = client
//					.target("http://localhost:8080/restapi/rest/xmlServices/send");
//
//			Response response = target.request().post(
//					Entity.entity(st, "application/xml"));
//
//			if (response.getStatus() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ response.getStatus());
//			}
//
//			System.out.println("Server response : \n");
//			System.out.println(response.readEntity(String.class));
//			
//			response.close();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}


		
	//}

}
