package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Resource;
import br.ufabc.impress.model.ResourceLog;

public class ResourceLogDAO extends GenericDAO<ResourceLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
	public ResourceLogDAO() {
		super(ResourceLog.class);
	}

	public void delete(ResourceLog obj) {
		super.delete(obj.getId(), ResourceLog.class);
	}

	public List<ResourceLog> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<ResourceLog> query = builder.createQuery(ResourceLog.class);
			Root<ResourceLog> variableRoot = query.from(ResourceLog.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<ResourceLog> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public ResourceLog getLastByResource(int resourceID){
		
		
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ResourceLog> query = builder.createQuery(ResourceLog.class);
		Root<ResourceLog> variableRoot = query.from(ResourceLog.class);
		query.select(variableRoot);
		
		Join<ResourceLog, Resource> resource = variableRoot.join("resource");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		Expression expressionResource = (Expression) resource
				.get("id");

		
		predicates.add(getCriteriaBuilder().equal(expressionResource, resourceID));
		
		query.where(predicates.toArray(new Predicate[] {}));
		
		query.orderBy(builder.desc(variableRoot.get("creationDate")));
		
		TypedQuery<ResourceLog> listQuery = getEntityManager().createQuery(query).setHint("javax.persistence.cache.storeMode", "REFRESH");
//		listQuery.setFirstResult(startIndex);
		//We want only the last value
		listQuery.setMaxResults(1);

		// retorna a lista paginada
		//LogResource r = listQuery.getSingleResult();
		List<ResourceLog> r = listQuery.getResultList();

		return r.get(0);
		
	}
	
	private CriteriaBuilder getCriteriaBuilder(){
		if (builder == null){
		 builder = getEntityManager().getCriteriaBuilder();
		}
		return builder;
	}
	
}
