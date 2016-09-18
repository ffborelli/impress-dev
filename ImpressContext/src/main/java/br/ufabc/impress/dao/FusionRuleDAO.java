package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.FusionRule;

public class FusionRuleDAO extends GenericDAO<FusionRule> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FusionRuleDAO() {
		super(FusionRule.class);
	}

	public void delete(FusionRule obj) {
		super.delete(obj.getId(), FusionRule.class);
	}

	public List<FusionRule> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FusionRule> query = builder.createQuery(FusionRule.class);
			Root<FusionRule> variableRoot = query.from(FusionRule.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<FusionRule> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
