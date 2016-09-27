package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.RuleActionLog;

public class FusionRuleLogDAO extends GenericDAO<FusionRuleLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
	public FusionRuleLogDAO() {
		super(FusionRuleLog.class);
	}

	public void delete(FusionRuleLog obj) {
		super.delete(obj.getId(), FusionRuleLog.class);
	}

	public List<FusionRuleLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FusionRuleLog> query = builder.createQuery(FusionRuleLog.class);
			Root<FusionRuleLog> variableRoot = query.from(FusionRuleLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<FusionRuleLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public List<FusionRuleLog> getByRuleAction(List<RuleActionLog> list) {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FusionRuleLog> query = builder.createQuery(FusionRuleLog.class);
			Root<FusionRuleLog> variableRoot = query.from(FusionRuleLog.class);
			query.select(variableRoot);
			
			//ArrayList<Predicate> predicates = new ArrayList<Predicate>();
			
			Expression ruleActionLog = (Expression) variableRoot.get("ruleActionLog");
			
			Predicate predicate = ruleActionLog.in(list);
			
			
			//query.where(predicates.toArray(new Predicate[] {}));
			query.where(predicate);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<FusionRuleLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	private CriteriaBuilder getCriteriaBuilder(){
		if (builder == null){
		 builder = getEntityManager().getCriteriaBuilder();
		}
		return builder;
	}
	
}
