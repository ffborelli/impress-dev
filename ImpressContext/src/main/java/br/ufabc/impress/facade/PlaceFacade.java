package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.PlaceDAO;
import br.ufabc.impress.model.Place;

public class PlaceFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PlaceDAO placeDAO = new PlaceDAO();

	public void create(Place obj) {
		placeDAO.beginTransaction();
		placeDAO.save(obj);
		placeDAO.commitAndCloseTransaction();
	}

	public void update(Place obj) {
		placeDAO.beginTransaction();
		placeDAO.update(obj);
		placeDAO.commitAndCloseTransaction();
	}

	public Place find(Integer id) {
		placeDAO.beginTransaction();
		Place obj = placeDAO.find(id);
		placeDAO.closeTransaction();
		return obj;
	}

	public List<Place> listAll() {
		placeDAO.beginTransaction();
		List<Place> result = placeDAO.findAll();
		placeDAO.closeTransaction();
		return result;
	}

	public void delete(Place obj) {
		placeDAO.beginTransaction();
		Place persistedCurso = placeDAO.findReferenceOnly(obj.getId());
		placeDAO.delete(persistedCurso);
		placeDAO.commitAndCloseTransaction();
	}
	
}
