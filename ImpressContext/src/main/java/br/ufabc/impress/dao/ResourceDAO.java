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

import br.ufabc.impress.model.Resource;

public class ResourceDAO extends GenericDAO<Resource> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
	public ResourceDAO() {
		super(Resource.class);
	}

	public void delete(Resource obj) {
		super.delete(obj.getId(), Resource.class);
	}

	public List<Resource> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Resource> query = builder.createQuery(Resource.class);
			Root<Resource> variableRoot = query.from(Resource.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Resource> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public Resource findByUid(String uid) {

		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Resource> query = builder.createQuery(Resource.class);
			Root<Resource> variableRoot = query.from(Resource.class);
			query.select(variableRoot);
			
			ArrayList<Predicate> predicates = new ArrayList<Predicate>();
						
			Expression sensorActuatorUid = (Expression) variableRoot.get("uid");

			predicates.add(getCriteriaBuilder().equal(sensorActuatorUid, uid));

			
			query.where(predicates.toArray(new Predicate[] {}));
			
			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			Resource r = getEntityManager().createQuery(query)
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
