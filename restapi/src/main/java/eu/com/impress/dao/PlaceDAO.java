package eu.com.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eu.com.impress.model.Place;
import eu.com.impress.util.UtilConverter;

import java.util.ArrayList;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import eu.com.impress.model.Resource;
import eu.com.impress.model.PlaceType;
import eu.com.impress.model.ResourceActionType;

public class PlaceDAO extends GenericDAO<Place> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	private UtilConverter utilConverter;
	
	public PlaceDAO() {
		super(Place.class);
	}

	public void delete(Place obj) {
		super.delete(obj.getId(), Place.class);
	}

	public List<Place> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Place> query = builder.createQuery(Place.class);
			Root<Place> variableRoot = query.from(Place.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Place> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public List<Place> getByDescription(String description) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Place> query = builder.createQuery(Place.class);
		Root<Place> variableRoot = query.from(Place.class);
		query.select(variableRoot);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Expression expressionDescription = (Expression) variableRoot
				.get("description");

		predicates.add(getCriteriaBuilder().like(expressionDescription,
				"%" + description + "%"));

		query.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Place> listQuery = getEntityManager().createQuery(query);
		// listQuery.setFirstResult(startIndex);
		// listQuery.setMaxResults(pageSize);

		// retorna a lista paginada
		List<Place> r = listQuery.getResultList();

		return r;

	}

	public List<Place> getPlaceByType(String places) {

		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Place> query = builder.createQuery(Place.class);
		Root<Place> variableRoot = query.from(Place.class);
		query.select(variableRoot);

		Join<Place, PlaceType> place = variableRoot.join("placeType");

		List<Predicate> predicates = new ArrayList<Predicate>();

		Expression expressionResource = (Expression) place.get("id");

		predicates.add(getCriteriaBuilder().equal(expressionResource, places));

		query.where(predicates.toArray(new Predicate[] {}));

		// query.orderBy(builder.desc(variableRoot.get("creationDate")));

		TypedQuery<Place> listQuery = getEntityManager().createQuery(query);
		// listQuery.setFirstResult(startIndex);
		// We want only the last value
		// listQuery.setMaxResults(100);

		// retorna a lista paginada
		// LogResource r = listQuery.getSingleResult();
		List<Place> r = listQuery.getResultList();

		return r;

	}

	public Integer countPlaces() {
	
		
		//CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Place> cQuery = getCriteriaBuilder().createQuery(Place.class);
		Root<Place> from = cQuery.from(Place.class);		

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(Long.class);
		countQuery.select(getCriteriaBuilder().count(countQuery.from(Place.class)));
		getEntityManager().createQuery(countQuery);
				
		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.getSingleResult() + 0);
		
		return rowCount;
		
	}
	
	
	public List<Place> findPlaces(int startPosition, int maxResults,
			String sortField, String sortDirections) {
//		Query query = entityManager
//				.createQuery("SELECT p FROM Person p ORDER BY p." + sortField
//						+ " " + sortDirections);
//		query.setFirstResult(startPosition);
//		query.setMaxResults(maxResults);
//		return query.getResultList();
		
		
		// criacao do critério de busca
		//CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Place> cQuery = getCriteriaBuilder().createQuery(Place.class);
		Root<Place> from = cQuery.from(Place.class);
		CriteriaQuery<Place> select = cQuery.select(from);
		
		//List<Predicate> predicates = this.createPredicate(filters, from);
		//select.where(predicates.toArray(new Predicate[] {}));

		// ordenacao vindo do datatable
		if (sortField != null) {
						
			if (sortDirections.equalsIgnoreCase("asc")){
				select.orderBy(getCriteriaBuilder().asc(from.get(sortField)));
			}
			else if (sortDirections.equalsIgnoreCase("desc")){
				select.orderBy(getCriteriaBuilder().desc(from.get(sortField)));
			}
			else {
				//error
			}

		}

		// realizar a consulta paginada
		TypedQuery<Place> listQuery = getEntityManager().createQuery(select);
		
		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);
	
		// retorna a lista paginada
		List<Place> r = listQuery.getResultList();

		return r;
	}
	
	public int getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {

		//CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Place> cQuery = getCriteriaBuilder().createQuery(Place.class);
		Root<Place> from = cQuery.from(Place.class);		

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(Long.class);
		countQuery.select(getCriteriaBuilder().count(countQuery.from(Place.class)));
		getEntityManager().createQuery(countQuery);
		
		List<Predicate> predicates = this.createPredicate(from,search);
		
		countQuery.where(predicates.toArray(new Predicate[] {}));
		
		int rowCount = (int) (getEntityManager().createQuery(countQuery).
				setFirstResult(startPosition).setMaxResults(maxResults)
				.getSingleResult() + 0);
		
		//return rowCount;
		return 1;

	}
	
	public List<Place> findPlacesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
//		Query query = entityManager
//				.createQuery("SELECT p FROM Person p ORDER BY p." + sortField
//						+ " " + sortDirections);
//		query.setFirstResult(startPosition);
//		query.setMaxResults(maxResults);
//		return query.getResultList();
		
		
		// criacao do critério de busca
		//CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Place> cQuery = getCriteriaBuilder().createQuery(Place.class);
		Root<Place> from = cQuery.from(Place.class);
		CriteriaQuery<Place> select = cQuery.select(from);
		
		List<Predicate> predicates = this.createPredicate(from,search);
		select.where(predicates.toArray(new Predicate[] {}));

		// order by field from grid
		if (sortField != null) {
						
			if (sortDirections.equalsIgnoreCase("asc")){
				select.orderBy(getCriteriaBuilder().asc(from.get(sortField)));
			}
			else if (sortDirections.equalsIgnoreCase("desc")){
				select.orderBy(getCriteriaBuilder().desc(from.get(sortField)));
			}
			else {
				//error
			}

		}

		// realizar a consulta paginada
		TypedQuery<Place> listQuery = getEntityManager().createQuery(select);
		
		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);
	
		// retorna a lista paginada
		List<Place> r = listQuery.getResultList();

		return r;
	}
	
	private ArrayList<Predicate> createPredicate(Root<Place> from, String search) {

		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if (search.equalsIgnoreCase("null")){
			
			return predicates;
		}

		else if (this.getUtilConverter().isNumeric(search)){
			
			Expression expressionId = (Expression) from
					.get("id");
			Expression expressionDescription = (Expression) from.get("description");

//			Expression expressionEmail = (Expression) from
//					.get("email");

			predicates.add(getCriteriaBuilder().or(
					getCriteriaBuilder().equal(expressionId,
							search),
					getCriteriaBuilder().like(expressionDescription,
							"%" + search.toUpperCase() + "%")
							)
						);
		}
		
		else {
			
			Expression expressionDescription = (Expression) from.get("description");

			predicates.add(getCriteriaBuilder().like(expressionDescription,
					"%" + search.toUpperCase() + "%"));
		}
			
		return predicates;
	}


	private CriteriaBuilder getCriteriaBuilder() {
		// if (builder == null) {
		builder = getEntityManager().getCriteriaBuilder();
		// }
		return builder;
	}

	private UtilConverter getUtilConverter() {
		if (utilConverter == null) {
			utilConverter = new UtilConverter();
		}
		return utilConverter;
	}
	
	
}
