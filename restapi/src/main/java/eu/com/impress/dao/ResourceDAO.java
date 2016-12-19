package eu.com.impress.dao;

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

import eu.com.impress.model.Place;
import eu.com.impress.model.Resource;
import eu.com.impress.model.ResourceType;
import eu.com.impress.util.UtilConverter;

public class ResourceDAO extends GenericDAO<Resource> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	private UtilConverter utilConverter;

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

	public List<Resource> getAllSensorsOrActuators(int resource) {

		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Resource> query = builder.createQuery(Resource.class);
			Root<Resource> variableRoot = query.from(Resource.class);
			query.select(variableRoot);
			
			ArrayList<Predicate> predicates = new ArrayList<Predicate>();
			
			Join<Resource, ResourceType> resourceType = variableRoot.join("resourceType");
			
			Expression sensorActuatorId = (Expression) resourceType.get("sensorActuator");

			predicates.add(getCriteriaBuilder().equal(sensorActuatorId, resource));
//			if (resource == 0){
//				predicates.add(getCriteriaBuilder().equal(sensorActuatorId, resource));
//			}else{
//				
//			}
			
			query.where(predicates.toArray(new Predicate[] {}));
			
			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Resource> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();

			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public Integer countResources() {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Resource> cQuery = getCriteriaBuilder().createQuery(
				Resource.class);
		Root<Place> from = cQuery.from(Place.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(Resource.class)));
		getEntityManager().createQuery(countQuery);

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.getSingleResult() + 0);

		return rowCount;

	}

	public List<Resource> findResources(int startPosition, int maxResults,
			String sortField, String sortDirections) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Resource> cQuery = getCriteriaBuilder().createQuery(
				Resource.class);
		Root<Resource> from = cQuery.from(Resource.class);
		CriteriaQuery<Resource> select = cQuery.select(from);

		// List<Predicate> predicates = this.createPredicate(filters, from);
		// select.where(predicates.toArray(new Predicate[] {}));

		// ordenacao vindo do datatable
		if (sortField != null) {

			if (sortDirections.equalsIgnoreCase("asc")) {
				select.orderBy(getCriteriaBuilder().asc(from.get(sortField)));
			} else if (sortDirections.equalsIgnoreCase("desc")) {
				select.orderBy(getCriteriaBuilder().desc(from.get(sortField)));
			} else {
				// error
			}

		}

		// realizar a consulta paginada
		TypedQuery<Resource> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<Resource> r = listQuery.getResultList();

		return r;
	}

	public int getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Resource> cQuery = getCriteriaBuilder().createQuery(
				Resource.class);
		Root<Resource> from = cQuery.from(Resource.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(Resource.class)));
		getEntityManager().createQuery(countQuery);

		List<Predicate> predicates = this.createPredicate(from, search);

		countQuery.where(predicates.toArray(new Predicate[] {}));

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.setFirstResult(startPosition).setMaxResults(maxResults)
				.getSingleResult() + 0);

		// return rowCount;
		return 1;

	}

	public List<Resource> findResourcesSearch(int startPosition,
			int maxResults, String sortField, String sortDirections,
			String search) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Resource> cQuery = getCriteriaBuilder().createQuery(
				Resource.class);
		Root<Resource> from = cQuery.from(Resource.class);
		CriteriaQuery<Resource> select = cQuery.select(from);

		List<Predicate> predicates = this.createPredicate(from, search);
		select.where(predicates.toArray(new Predicate[] {}));

		// order by field from grid
		if (sortField != null) {

			if (sortDirections.equalsIgnoreCase("asc")) {
				select.orderBy(getCriteriaBuilder().asc(from.get(sortField)));
			} else if (sortDirections.equalsIgnoreCase("desc")) {
				select.orderBy(getCriteriaBuilder().desc(from.get(sortField)));
			} else {
				// error
			}

		}

		// realizar a consulta paginada
		TypedQuery<Resource> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<Resource> r = listQuery.getResultList();

		return r;
	}

	private ArrayList<Predicate> createPredicate(Root<Resource> from,
			String search) {

		ArrayList<Predicate> predicates = new ArrayList<Predicate>();

		if (search.equalsIgnoreCase("null")) {

			return predicates;
		}

		else if (this.getUtilConverter().isNumeric(search)) {

			Expression expressionId = (Expression) from.get("id");
			Expression expressionDescription = (Expression) from
					.get("description");

			// Expression expressionEmail = (Expression) from
			// .get("email");

			predicates.add(getCriteriaBuilder().or(
					getCriteriaBuilder().equal(expressionId, search),
					getCriteriaBuilder().like(expressionDescription,
							"%" + search.toUpperCase() + "%")));
		}

		else {

			Expression expressionDescription = (Expression) from
					.get("description");

			predicates.add(getCriteriaBuilder().like(expressionDescription,
					"%" + search.toUpperCase() + "%"));
		}

		return predicates;
	}
	
	
	private CriteriaBuilder getCriteriaBuilder(){
		if (builder == null){
		 builder = getEntityManager().getCriteriaBuilder();
		}
		return builder;
	}
	
	private UtilConverter getUtilConverter() {
		if (utilConverter == null) {
			utilConverter = new UtilConverter();
		}
		return utilConverter;
	}

}
