package eu.com.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;





import eu.com.impress.model.EvalSdp;
import eu.com.impress.model.EvalSdp;
import eu.com.impress.util.UtilConverter;

public class EvalSdpDAO extends GenericDAO<EvalSdp> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	private UtilConverter utilConverter;

	public EvalSdpDAO() {
		super(EvalSdp.class);
	}

	public void delete(EvalSdp obj) {
		super.delete(obj.getUid(), EvalSdp.class);
	}

	public List<EvalSdp> findAll() {

		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<EvalSdp> query = builder.createQuery(EvalSdp.class);
			Root<EvalSdp> variableRoot = query.from(EvalSdp.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<EvalSdp> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();

			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public Long getMaxUid(){
		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<EvalSdp> query = builder.createQuery(EvalSdp.class);
			Root<EvalSdp> variableRoot = query.from(EvalSdp.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			//List<Predicate> predicates = new ArrayList<Predicate>();

			
			query.orderBy(getCriteriaBuilder().desc(variableRoot.get("uid")));
			

			List<EvalSdp> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.setMaxResults(1)
					.getResultList();
			
			if (r.isEmpty()){
				return (long) 0;
			}
			else 
				return r.get(0).getUid();

		} catch (Exception e) {
			throw new IllegalStateException("Get Max Uid Error!");
		}
	}
	
	public List<EvalSdp> getLastRegisters(int limit){
		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<EvalSdp> query = builder.createQuery(EvalSdp.class);
			Root<EvalSdp> variableRoot = query.from(EvalSdp.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			//List<Predicate> predicates = new ArrayList<Predicate>();

			
			query.orderBy(getCriteriaBuilder().desc(variableRoot.get("uid")));
			

			List<EvalSdp> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.setMaxResults(limit)
					.getResultList();
			
			return r;
			
			
		} catch (Exception e) {
			throw new IllegalStateException("Get Last Registers Error!");
		}
	}

	public List<EvalSdp> listExcelSearch(int id, String module, Date start,
			Date finish, Date duration, String description, String name) {

		try {

			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<EvalSdp> query = builder.createQuery(EvalSdp.class);
			Root<EvalSdp> variableRoot = query.from(EvalSdp.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (id > 0) {
				Expression expressionId = (Expression) variableRoot.get("id");

				predicates.add(getCriteriaBuilder().and(
						getCriteriaBuilder().equal(expressionId, id)));
			}

			if (module != null) {

				Expression expression = (Expression) variableRoot.get("module");

				predicates.add(getCriteriaBuilder().and(
						getCriteriaBuilder().like(expression, module)));

			}

			if (description != null) {

				Expression expression = (Expression) variableRoot
						.get("description");

				predicates.add(getCriteriaBuilder().and(
						getCriteriaBuilder().like(expression, description)));

			}

			query.where(predicates.toArray(new Predicate[] {}));
			query.orderBy(getCriteriaBuilder().asc(variableRoot.get("name")));

			List<EvalSdp> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();

			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
	public Integer countEvalSdps() {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EvalSdp> cQuery = getCriteriaBuilder().createQuery(
				EvalSdp.class);
		Root<EvalSdp> from = cQuery.from(EvalSdp.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(EvalSdp.class)));
		getEntityManager().createQuery(countQuery);

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.getSingleResult() + 0);

		return rowCount;

	}

	public List<EvalSdp> findEvalSdps(int startPosition, int maxResults,
			String sortField, String sortDirections) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EvalSdp> cQuery = getCriteriaBuilder().createQuery(
				EvalSdp.class);
		Root<EvalSdp> from = cQuery.from(EvalSdp.class);
		CriteriaQuery<EvalSdp> select = cQuery.select(from);

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
		TypedQuery<EvalSdp> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<EvalSdp> r = listQuery.getResultList();

		return r;
	}

	public int getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {

		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EvalSdp> cQuery = getCriteriaBuilder().createQuery(
				EvalSdp.class);
		Root<EvalSdp> from = cQuery.from(EvalSdp.class);

		// obter o total de registros da consulta
		CriteriaQuery<Long> countQuery = getCriteriaBuilder().createQuery(
				Long.class);
		countQuery.select(getCriteriaBuilder().count(
				countQuery.from(EvalSdp.class)));
		getEntityManager().createQuery(countQuery);

		List<Predicate> predicates = this.createPredicate(from, search);

		countQuery.where(predicates.toArray(new Predicate[] {}));

		int rowCount = (int) (getEntityManager().createQuery(countQuery)
				.setFirstResult(startPosition).setMaxResults(maxResults)
				.getSingleResult() + 0);

		// return rowCount;
		return 1;

	}

	public List<EvalSdp> findEvalSdpsSearch(int startPosition,
			int maxResults, String sortField, String sortDirections,
			String search) {

		// criacao do critério de busca
		// CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EvalSdp> cQuery = getCriteriaBuilder().createQuery(
				EvalSdp.class);
		Root<EvalSdp> from = cQuery.from(EvalSdp.class);
		CriteriaQuery<EvalSdp> select = cQuery.select(from);

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
		TypedQuery<EvalSdp> listQuery = getEntityManager()
				.createQuery(select);

		listQuery.setFirstResult(startPosition);
		listQuery.setMaxResults(maxResults);

		// retorna a lista paginada
		List<EvalSdp> r = listQuery.getResultList();

		return r;
	}

	private ArrayList<Predicate> createPredicate(Root<EvalSdp> from,
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
		if (builder == null) {
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
