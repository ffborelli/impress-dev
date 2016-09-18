package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.PlaceTypeDAO;
import br.ufabc.impress.model.PlaceType;

public class PlaceTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private PlaceTypeDAO placeTypeDAO = new PlaceTypeDAO();

	public void create(PlaceType obj) {
		placeTypeDAO.beginTransaction();
		placeTypeDAO.save(obj);
		placeTypeDAO.commitAndCloseTransaction();
	}

	public void update(PlaceType obj) {
		placeTypeDAO.beginTransaction();
		placeTypeDAO.update(obj);
		placeTypeDAO.commitAndCloseTransaction();
	}

	public PlaceType find(Integer id) {
		placeTypeDAO.beginTransaction();
		PlaceType obj = placeTypeDAO.find(id);
		placeTypeDAO.closeTransaction();
		return obj;
	}

	public List<PlaceType> listAll() {
		placeTypeDAO.beginTransaction();
		List<PlaceType> result = placeTypeDAO.findAll();
		placeTypeDAO.closeTransaction();
		return result;
	}

	public void delete(PlaceType obj) {
		placeTypeDAO.beginTransaction();
		PlaceType persistedCurso = placeTypeDAO.findReferenceOnly(obj.getId());
		placeTypeDAO.delete(persistedCurso);
		placeTypeDAO.commitAndCloseTransaction();
	}
	
}
