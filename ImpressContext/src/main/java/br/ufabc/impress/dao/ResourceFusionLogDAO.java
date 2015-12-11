package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import br.ufabc.impress.model.FusionLog;
import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.ResourceFusionLog;

public class ResourceFusionLogDAO extends GenericDAO<ResourceFusionLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
	public ResourceFusionLogDAO() {
		super(ResourceFusionLog.class);
	}

	public void delete(ResourceFusionLog obj) {
		super.delete(obj.getId(), ResourceFusionLog.class);
	}

	public List<ResourceFusionLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceFusionLog> query = builder.createQuery(ResourceFusionLog.class);
			Root<ResourceFusionLog> variableRoot = query.from(ResourceFusionLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceFusionLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public List<ResourceFusionLog> getByFusionLog(List<FusionLog> list) {
		
		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceFusionLog> query = builder.createQuery(ResourceFusionLog.class);
			Root<ResourceFusionLog> variableRoot = query.from(ResourceFusionLog.class);
			query.select(variableRoot);
			
			Expression fusionLog = (Expression) variableRoot.get("fusionLog");
			
			Predicate predicate = fusionLog.in(list);
			
			//query.where(predicates.toArray(new Predicate[] {}));
			query.where(predicate);
			
			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceFusionLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}
		
	} 
	
	public List<ResourceFusionLog> getByFusionLog(FusionLog fusionLog) {
		
		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceFusionLog> query = builder.createQuery(ResourceFusionLog.class);
			Root<ResourceFusionLog> variableRoot = query.from(ResourceFusionLog.class);
			query.select(variableRoot);
			
			ArrayList<Predicate> predicates = new ArrayList<Predicate>();
			
			Expression fusionLogEx = (Expression) variableRoot.get("fusionLog");
			
			predicates.add(getCriteriaBuilder().equal(fusionLogEx, fusionLog));
			
			query.where(predicates.toArray(new Predicate[] {}));
			
			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceFusionLog> r = getEntityManager().createQuery(query)
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
