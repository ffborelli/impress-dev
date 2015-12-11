package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.FusionLog;

public class FusionLogDAO extends GenericDAO<FusionLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FusionLogDAO() {
		super(FusionLog.class);
	}

	public void delete(FusionLog obj) {
		super.delete(obj.getId(), FusionLog.class);
	}

	public List<FusionLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FusionLog> query = builder.createQuery(FusionLog.class);
			Root<FusionLog> variableRoot = query.from(FusionLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<FusionLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
