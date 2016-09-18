package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Fusion;

public class FusionDAO extends GenericDAO<Fusion> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FusionDAO() {
		super(Fusion.class);
	}

	public void delete(Fusion obj) {
		super.delete(obj.getId(), Fusion.class);
	}

	public List<Fusion> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<Fusion> query = builder.createQuery(Fusion.class);
			Root<Fusion> variableRoot = query.from(Fusion.class);
			query.select(variableRoot);

			getEntityManager().getEntityManagerFactory().getCache().evictAll();

			List<Fusion> r = getEntityManager().createQuery(query)
					.setHint("javax.persistence.cache.storeMode", "REFRESH")
					.getResultList();
			
			return r;

		} catch (Exception e) {
			throw new IllegalStateException("Find All Error!");
		}

	}
	
}
