package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ContextLog;

public class ContextLogDAO extends GenericDAO<ContextLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContextLogDAO() {
		super(ContextLog.class);
	}

	public void delete(ContextLog obj) {
		super.delete(obj.getId(), ContextLog.class);
	}

	public List<ContextLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ContextLog> query = builder.createQuery(ContextLog.class);
			Root<ContextLog> variableRoot = query.from(ContextLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ContextLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
