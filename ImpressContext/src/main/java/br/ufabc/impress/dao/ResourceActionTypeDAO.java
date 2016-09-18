package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ResourceActionType;

public class ResourceActionTypeDAO extends GenericDAO<ResourceActionType> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceActionTypeDAO() {
		super(ResourceActionType.class);
	}

	public void delete(ResourceActionType obj) {
		super.delete(obj.getId(), ResourceActionType.class);
	}

	public List<ResourceActionType> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceActionType> query = builder.createQuery(ResourceActionType.class);
			Root<ResourceActionType> variableRoot = query.from(ResourceActionType.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceActionType> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
