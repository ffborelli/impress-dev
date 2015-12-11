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

import eu.com.impress.model.Rule;
import eu.com.impress.util.UtilConverter;

public class RuleDAO extends GenericDAO<Rule> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	private UtilConverter utilConverter;
	
	public RuleDAO() {
		super(Rule.class);
	}

	public void delete(Rule obj) {
		super.delete(obj.getId(), Rule.class);
	}

	public List<Rule> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Rule> query = builder.createQuery(Rule.class);
			Root<Rule> variableRoot = query.from(Rule.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Rule> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public Integer countRules() {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Rule> cQuery = getCriteriaBuilder().createQuery(
				Rule.class);
		Root<Rule> from = cQuery.from(Rule.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(Rule.class)));
		getEntityManager().createQuery(countQuery);

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.getSingleResult() + 0);

		return rowCount;

	}

	public List<Rule> findRules(int startPosition, int maxResults,
			String sortField, String sortDirections) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Rule> cQuery = getCriteriaBuilder().createQuery(
				Rule.class);
		Root<Rule> from = cQuery.from(Rule.class);
		CriteriaQuery<Rule> select = cQuery.select(from);

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
		TypedQuery<Rule> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<Rule> r = listQuery.getResultList();

		return r;
	}

	public int getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Rule> cQuery = getCriteriaBuilder().createQuery(
				Rule.class);
		Root<Rule> from = cQuery.from(Rule.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(Rule.class)));
		getEntityManager().createQuery(countQuery);

		List<Predicate> predicates = this.createPredicate(from, search);

		countQuery.where(predicates.toArray(new Predicate[] {}));

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.setFirstResult(startPosition).setMaxResults(maxResults)
				.getSingleResult() + 0);

		// return rowCount;
		return 1;

	}

	public List<Rule> findRulesSearch(int startPosition,
			int maxResults, String sortField, String sortDirections,
			String search) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Rule> cQuery = getCriteriaBuilder().createQuery(
				Rule.class);
		Root<Rule> from = cQuery.from(Rule.class);
		CriteriaQuery<Rule> select = cQuery.select(from);

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
		TypedQuery<Rule> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<Rule> r = listQuery.getResultList();

		return r;
	}

	private ArrayList<Predicate> createPredicate(Root<Rule> from,
			String search) {

		ArrayList<Predicate> predicates = new ArrayList<Predicate>();

		if (search.equalsIgnoreCase("null")) {

			return predicates;
		}

		else if (this.getUtilConverter().isNumeric(search)) {

			Expression expressionId = (Expression) from.get("id");
			
			Expression expressionRuleName = (Expression) from
					.get("fusionName");
			
			Expression expressionRuleText = (Expression) from
					.get("fusionText");

			// Expression expressionEmail = (Expression) from
			// .get("email");

			predicates.add(getCriteriaBuilder().or(
					getCriteriaBuilder().equal(expressionId, search),
					getCriteriaBuilder().like(expressionRuleName,
							"%" + search.toUpperCase() + "%"),
					getCriteriaBuilder().like(expressionRuleText,
							"%" + search.toUpperCase() + "%")
							
					));
		}

		else {

			Expression expressionRuleName = (Expression) from
					.get("fusionName");
			
			Expression expressionRuleText = (Expression) from
					.get("fusionText");

			// Expression expressionEmail = (Expression) from
			// .get("email");

			predicates.add(getCriteriaBuilder().or(
					
					getCriteriaBuilder().like(expressionRuleName,
							"%" + search.toUpperCase() + "%"),
					getCriteriaBuilder().like(expressionRuleText,
							"%" + search.toUpperCase() + "%")
							
					));
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
