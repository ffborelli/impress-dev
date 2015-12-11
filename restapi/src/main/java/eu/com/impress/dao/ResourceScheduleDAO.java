package eu.com.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.com.impress.model.ResourceSchedule;

public class ResourceScheduleDAO extends GenericDAO<ResourceSchedule> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceScheduleDAO() {
		super(ResourceSchedule.class);
	}

	public void delete(ResourceSchedule obj) {
		super.delete(obj.getId(), ResourceSchedule.class);
	}

	public List<ResourceSchedule> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceSchedule> query = builder.createQuery(ResourceSchedule.class);
			Root<ResourceSchedule> variableRoot = query.from(ResourceSchedule.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceSchedule> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
