//package eu.com.impress.util;
//
////import java.io.BufferedReader;
//
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.Response;
// 
//import org.jboss.resteasy.client.jaxrs.ResteasyClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
// 
//
//public class RESTEasyClientGet {
// 
//	public static void main(String[] args) {
//		
//		try {
//				            ResteasyClient client = new ResteasyClientBuilder().build();
//				 
//				            ResteasyWebTarget target = client
//				                    .target("http://localhost:9090/RESTEasyJSONExample/rest/jsonServices/send");
//				 
//				            Response response = target.request().post(
//				                    Entity.entity(st, "application/json"));
//				 
//				            if (response.getStatus() != 200) {
//				                throw new RuntimeException("Failed : HTTP error code : "
//				                        + response.getStatus());
//				            }
//				 
//				            System.out.println("Server response : \n");
//				            System.out.println(response.readEntity(String.class));
//				 
//				            response.close();
//				 
//				        } catch (Exception e) {
//				 
//				            e.printStackTrace();
//				 
//				        }
//	  
// 
//		
// 
//	}
//	
//    private static void getExample_one()
//    {
//        ResteasyClient client = new ResteasyClientBuilder().build();
//        ResteasyWebTarget target = client.target("http://localhost:8080/RESTEasyApplication/user-management/users");
//        Response response = target.request().get();
//        //Read output in string format
//        String value = response.readEntity(String.class);
//        System.out.println(value);
//        response.close(); 
//    }
//     
//    private static void getExample_two()
//    {
//        ResteasyClient client = new ResteasyClientBuilder().build();
//        ResteasyWebTarget target = client.target("http://localhost:8080/RESTEasyApplication/user-management/users");
//        Response response = target.request().get();
//        //Read the entity
//        Users users = response.readEntity(Users.class);
//        for(User user : users.getUsers()){
//            System.out.println(user.getId());
//            System.out.println(user.getLastName());
//        }
//        response.close(); 
//    }
//     
//    private static void postExample()
//    {
//        User user = new User();
//        user.setFirstName("john");
//        user.setLastName("Maclane");
//         
//        ResteasyClient client = new ResteasyClientBuilder().build();
//        ResteasyWebTarget target = client.target("http://localhost:8080/RESTEasyApplication/user-management/users");
//        Response response = target.request().post(Entity.entity(user, "application/vnd.com.demo.user-management.user+xml;charset=UTF-8;version=1"));
//        //Read output in string format
//        System.out.println(response.getStatus());
//        response.close(); 
//    }
// 
//}