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

import eu.com.impress.model.FusionLog;
import eu.com.impress.util.UtilConverter;

public class FusionLogDAO extends GenericDAO<FusionLog> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CriteriaBuilder builder;
	private UtilConverter utilConverter;
	
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
	
	public Integer countFusionLogs() {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FusionLog> cQuery = getCriteriaBuilder().createQuery(
				FusionLog.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(FusionLog.class)));
		getEntityManager().createQuery(countQuery);

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.getSingleResult() + 0);

		return rowCount;

	}
	
	public List<FusionLog> findFusionLogs(int startPosition, int maxResults,
			String sortField, String sortDirections) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FusionLog> cQuery = getCriteriaBuilder().createQuery(
				FusionLog.class);
		Root<FusionLog> from = cQuery.from(FusionLog.class);
		CriteriaQuery<FusionLog> select = cQuery.select(from);

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
		TypedQuery<FusionLog> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<FusionLog> r = listQuery.getResultList();

		return r;
	}
	
	public int getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FusionLog> cQuery = getCriteriaBuilder().createQuery(
				FusionLog.class);
		Root<FusionLog> from = cQuery.from(FusionLog.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(FusionLog.class)));
		getEntityManager().createQuery(countQuery);

		List<Predicate> predicates = this.createPredicate(from, search);

		countQuery.where(predicates.toArray(new Predicate[] {}));

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.setFirstResult(startPosition).setMaxResults(maxResults)
				.getSingleResult() + 0);

		// return rowCount;
		return 1;

	}
	
	public List<FusionLog> findFusionLogsSearch(int startPosition,
			int maxResults, String sortField, String sortDirections,
			String search) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<FusionLog> cQuery = getCriteriaBuilder().createQuery(
				FusionLog.class);
		Root<FusionLog> from = cQuery.from(FusionLog.class);
		CriteriaQuery<FusionLog> select = cQuery.select(from);

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
		TypedQuery<FusionLog> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<FusionLog> r = listQuery.getResultList();

		return r;
	}
	
	private ArrayList<Predicate> createPredicate(Root<FusionLog> from,
			String search) {

		ArrayList<Predicate> predicates = new ArrayList<Predicate>();

		if (search.equalsIgnoreCase("null")) {

			return predicates;
		}

		else if (this.getUtilConverter().isNumeric(search)) {

			Expression expressionId = (Expression) from.get("id");
			Expression expressionDescription = (Expression) from
					.get("fusionLogValue");

			// Expression expressionEmail = (Expression) from
			// .get("email");

			predicates.add(getCriteriaBuilder().or(
					getCriteriaBuilder().equal(expressionId, search),
					getCriteriaBuilder().like(expressionDescription,
							"%" + search.toUpperCase() + "%")));
		}

		else {

			Expression expressionDescription = (Expression) from
					.get("fusionLogValue");

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
