package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ResourceType;

public class ResourceTypeDAO extends GenericDAO<ResourceType> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceTypeDAO() {
		super(ResourceType.class);
	}

	public void delete(ResourceType obj) {
		super.delete(obj.getId(), ResourceType.class);
	}

	public List<ResourceType> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceType> query = builder.createQuery(ResourceType.class);
			Root<ResourceType> variableRoot = query.from(ResourceType.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceType> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
