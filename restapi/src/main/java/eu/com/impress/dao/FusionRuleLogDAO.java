package eu.com.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.com.impress.model.FusionRuleLog;

public class FusionRuleLogDAO extends GenericDAO<FusionRuleLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
}
