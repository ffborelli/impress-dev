package eu.com.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.com.impress.model.ResourceFusionLog;

public class ResourceFusionLogDAO extends GenericDAO<ResourceFusionLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceFusionLogDAO() {
		super(ResourceFusionLog.class);
	}

	public void delete(ResourceFusionLog obj) {
		super.delete(obj.getId(), ResourceFusionLog.class);
	}

	public List<ResourceFusionLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceFusionLog> query = builder.createQuery(ResourceFusionLog.class);
			Root<ResourceFusionLog> variableRoot = query.from(ResourceFusionLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceFusionLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
