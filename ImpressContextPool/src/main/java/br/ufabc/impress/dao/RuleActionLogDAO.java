package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.RuleActionLog;

public class RuleActionLogDAO extends GenericDAO<RuleActionLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public RuleActionLogDAO() {
		super(RuleActionLog.class);
	}

	public void delete(RuleActionLog obj) {
		super.delete(obj.getId(), RuleActionLog.class);
	}

	public List<RuleActionLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<RuleActionLog> query = builder.createQuery(RuleActionLog.class);
			Root<RuleActionLog> variableRoot = query.from(RuleActionLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<RuleActionLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
