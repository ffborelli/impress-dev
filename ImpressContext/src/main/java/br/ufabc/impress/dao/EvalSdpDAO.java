package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.espertech.esper.client.annotation.Description;

import br.ufabc.impress.model.EvalSdp;

public class EvalSdpDAO extends GenericDAO<EvalSdp> implements Serializable {

	private static final long serialVersionUID = 1L;
	private CriteriaBuilder builder;
	
	public EvalSdpDAO() {
		super(EvalSdp.class);
	}

	public void delete(EvalSdp obj) {
		super.delete(obj.getId(), EvalSdp.class);
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
	
	public List<EvalSdp> listExcelSearch(int id, String module, Date start, Date finish, Date duration,String description, String name) {
		
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
			
			if(module != null){
				
				Expression expression = (Expression) variableRoot.get("module");

				predicates.add(getCriteriaBuilder().and(
						getCriteriaBuilder().like(expression, module)));
				
			}
			
			if(description != null){
				
				Expression expression = (Expression) variableRoot.get("description");

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
	
	private CriteriaBuilder getCriteriaBuilder(){
		if (builder == null){
		 builder = getEntityManager().getCriteriaBuilder();
		}
		return builder;
	}
	
}
