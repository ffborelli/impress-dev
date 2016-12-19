package eu.com.impress.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	private static EntityManagerFactory factory = null;
	private static EntityManager em = null;

	public static EntityManager getEntityManager() {
		if (factory == null) {
			factory = Persistence
					.createEntityManagerFactory("IMPReSSWeb");
		}
		if (em == null) {
			em = factory.createEntityManager();
		}
		return em;
	}

	public static void closeConnection() {

		em.close();
	}

}
