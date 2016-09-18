package br.ufabc.impress.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufabc.impress.model.Place;

public class PlaceDAO extends GenericDAO<Place> implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
	
}
