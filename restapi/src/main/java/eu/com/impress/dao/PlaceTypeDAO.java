package eu.com.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import eu.com.impress.model.Place;
import eu.com.impress.model.PlaceType;
import eu.com.impress.util.UtilConverter;

public class PlaceTypeDAO extends GenericDAO<PlaceType> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	private UtilConverter utilConverter;

	public PlaceTypeDAO() {
		super(PlaceType.class);
	}

	public void delete(PlaceType obj) {
		super.delete(obj.getId(), PlaceType.class);
	}

	public List<PlaceType> findAll() {

		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<PlaceType> query = builder
					.createQuery(PlaceType.class);
			Root<PlaceType> variableRoot = query.from(PlaceType.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<PlaceType> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();

			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}

	public Integer countPlaceTypes() {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PlaceType> cQuery = getCriteriaBuilder().createQuery(
				PlaceType.class);
		Root<Place> from = cQuery.from(Place.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(PlaceType.class)));
		getEntityManager().createQuery(countQuery);

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.getSingleResult() + 0);

		return rowCount;

	}

	public List<PlaceType> findPlaceTypes(int startPosition, int maxResults,
			String sortField, String sortDirections) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PlaceType> cQuery = getCriteriaBuilder().createQuery(
				PlaceType.class);
		Root<PlaceType> from = cQuery.from(PlaceType.class);
		CriteriaQuery<PlaceType> select = cQuery.select(from);

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
		TypedQuery<PlaceType> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<PlaceType> r = listQuery.getResultList();

		return r;
	}

	public int getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PlaceType> cQuery = getCriteriaBuilder().createQuery(
				PlaceType.class);
		Root<PlaceType> from = cQuery.from(PlaceType.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(PlaceType.class)));
		getEntityManager().createQuery(countQuery);

		List<Predicate> predicates = this.createPredicate(from, search);

		countQuery.where(predicates.toArray(new Predicate[] {}));

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.setFirstResult(startPosition).setMaxResults(maxResults)
				.getSingleResult() + 0);

		// return rowCount;
		return 1;

	}

	public List<PlaceType> findPlaceTypesSearch(int startPosition,
			int maxResults, String sortField, String sortDirections,
			String search) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<PlaceType> cQuery = getCriteriaBuilder().createQuery(
				PlaceType.class);
		Root<PlaceType> from = cQuery.from(PlaceType.class);
		CriteriaQuery<PlaceType> select = cQuery.select(from);

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
		TypedQuery<PlaceType> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<PlaceType> r = listQuery.getResultList();

		return r;
	}

	private ArrayList<Predicate> createPredicate(Root<PlaceType> from,
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
