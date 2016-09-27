package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ResourceFusion;

public class ResourceFusionDAO extends GenericDAO<ResourceFusion> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ResourceFusionDAO() {
		super(ResourceFusion.class);
	}

	public void delete(ResourceFusion obj) {
		super.delete(obj.getId(), ResourceFusion.class);
	}

	public List<ResourceFusion> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceFusion> query = builder.createQuery(ResourceFusion.class);
			Root<ResourceFusion> variableRoot = query.from(ResourceFusion.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceFusion> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
