//package br.ufabc.impress.util;
//
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityManager;
//import javax.persistence.Persistence;
//
//public class EntityManagerUtil {
//	
//	public static EntityManagerFactory emf = null;
//	public static EntityManager em = null;
//	
//	public static EntityManager getEntityManager(){
//		
//		if(emf == null){
//			emf = Persistence.createEntityManagerFactory("ImpressContext");
//		}
//		
//		if(em == null){
//			em = emf.createEntityManager();
//		}
//		
//		return em;
//		
//	}
//	
//	public static void closeConnection(){
//		
//		em.close();
//		
//	}
//	
//}
