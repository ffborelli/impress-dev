package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ResourceContext;

public class ResourceContextDAO extends GenericDAO<ResourceContext> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceContextDAO() {
		super(ResourceContext.class);
	}

	public void delete(ResourceContext obj) {
		super.delete(obj.getId(), ResourceContext.class);
	}

	public List<ResourceContext> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceContext> query = builder.createQuery(ResourceContext.class);
			Root<ResourceContext> variableRoot = query.from(ResourceContext.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceContext> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
