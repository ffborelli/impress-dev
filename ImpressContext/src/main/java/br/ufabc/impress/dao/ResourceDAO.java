package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Resource;

public class ResourceDAO extends GenericDAO<Resource> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceDAO() {
		super(Resource.class);
	}

	public void delete(Resource obj) {
		super.delete(obj.getId(), Resource.class);
	}

	public List<Resource> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Resource> query = builder.createQuery(Resource.class);
			Root<Resource> variableRoot = query.from(Resource.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Resource> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
