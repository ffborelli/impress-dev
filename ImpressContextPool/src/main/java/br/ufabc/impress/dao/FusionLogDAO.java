package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.FusionLog;

public class FusionLogDAO extends GenericDAO<FusionLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
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
	
	public FusionLog find(FusionLog fl) {
		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<FusionLog> query = builder.createQuery(FusionLog.class);
			Root<FusionLog> variableRoot = query.from(FusionLog.class);
			query.select(variableRoot);
			
			ArrayList<Predicate> predicates = new ArrayList<Predicate>();
			
			Expression creationDate = (Expression) variableRoot.get("creationDate");

			Expression fusion = (Expression) variableRoot.get("fusion");
			
			Expression fusionLogValue = (Expression) variableRoot.get("fusionLogValue");
			
			predicates.add(getCriteriaBuilder().equal(creationDate, fl.getCreationDate()));
			
			predicates.add(getCriteriaBuilder().equal(fusion, fl.getFusion()));
			
			predicates.add(getCriteriaBuilder().equal(fusionLogValue, fl.getFusionLogValue()));
			
			query.where(predicates.toArray(new Predicate[] {}));

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			FusionLog r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getSingleResult();
			
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
