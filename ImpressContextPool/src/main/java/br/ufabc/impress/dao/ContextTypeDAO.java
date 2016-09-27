package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.ContextType;

public class ContextTypeDAO extends GenericDAO<ContextType> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContextTypeDAO() {
		super(ContextType.class);
	}

	public void delete(ContextType obj) {
		super.delete(obj.getId(), ContextType.class);
	}

	public List<ContextType> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ContextType> query = builder.createQuery(ContextType.class);
			Root<ContextType> variableRoot = query.from(ContextType.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ContextType> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
