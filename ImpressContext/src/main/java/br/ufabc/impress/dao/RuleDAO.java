package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Rule;

public class RuleDAO extends GenericDAO<Rule> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public RuleDAO() {
		super(Rule.class);
	}

	public void delete(Rule obj) {
		super.delete(obj.getId(), Rule.class);
	}

	public List<Rule> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Rule> query = builder.createQuery(Rule.class);
			Root<Rule> variableRoot = query.from(Rule.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Rule> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
