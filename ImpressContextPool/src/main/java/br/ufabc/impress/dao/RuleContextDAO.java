package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.RuleContext;

public class RuleContextDAO extends GenericDAO<RuleContext> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public RuleContextDAO() {
		super(RuleContext.class);
	}

	public void delete(RuleContext obj) {
		super.delete(obj.getId(), RuleContext.class);
	}

	public List<RuleContext> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<RuleContext> query = builder.createQuery(RuleContext.class);
			Root<RuleContext> variableRoot = query.from(RuleContext.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<RuleContext> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
