package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Action;

public class ActionDAO extends GenericDAO<Action> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ActionDAO() {
		super(Action.class);
	}

	public void delete(Action obj) {
		super.delete(obj.getId(), Action.class);
	}

	public List<Action> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Action> query = builder.createQuery(Action.class);
			Root<Action> variableRoot = query.from(Action.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Action> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
