package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.PlaceType;

public class PlaceTypeDAO extends GenericDAO<PlaceType> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public PlaceTypeDAO() {
		super(PlaceType.class);
	}

	public void delete(PlaceType obj) {
		super.delete(obj.getId(), PlaceType.class);
	}

	public List<PlaceType> findAll() {

		try {
			
			CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
			CriteriaQuery<PlaceType> query = builder.createQuery(PlaceType.class);
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
	
}
