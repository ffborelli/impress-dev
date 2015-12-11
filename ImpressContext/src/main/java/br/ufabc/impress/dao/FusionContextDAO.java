package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.FusionContext;

public class FusionContextDAO extends GenericDAO<FusionContext> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FusionContextDAO() {
		super(FusionContext.class);
	}

	public void delete(FusionContext obj) {
		super.delete(obj.getId(), FusionContext.class);
	}

	public List<FusionContext> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FusionContext> query = builder.createQuery(FusionContext.class);
			Root<FusionContext> variableRoot = query.from(FusionContext.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<FusionContext> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
