package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.PlaceTypeDAO;
import eu.com.impress.model.Place;
import eu.com.impress.model.PlaceType;

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
	
	public List<PlaceType> findPlaces(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		placeTypeDAO.beginTransaction();
		List<PlaceType> listplaceType = placeTypeDAO.findPlaceTypes(startPosition, maxResults, sortField, sortDirections);
		placeTypeDAO.closeTransaction();
		
		return listplaceType;
	}
	
	public List<PlaceType> findPlacesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		placeTypeDAO.beginTransaction();
		List<PlaceType> listPlaceType = placeTypeDAO.findPlaceTypesSearch(startPosition, maxResults, sortField, sortDirections, search);
		placeTypeDAO.closeTransaction();
		
		return listPlaceType;
	}
	
	public Integer countPlaceTypes() {
		
		placeTypeDAO.beginTransaction();
		Integer places = placeTypeDAO.countPlaceTypes();
		placeTypeDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		placeTypeDAO.beginTransaction();
		Integer placeTypes = placeTypeDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		placeTypeDAO.closeTransaction();
		
		return placeTypes;
	}
	
}
