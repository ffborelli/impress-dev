package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.PlaceDAO;
import eu.com.impress.model.Place;

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
	
	public List<Place> getByDescription(String description){
		
		placeDAO.beginTransaction();
		List<Place> listPLace = placeDAO.getByDescription(description);
		placeDAO.closeTransaction();
		
		return listPLace;
	}
	
	public List<Place> getPlaceByType(String resource){
		
		placeDAO.beginTransaction();
		List<Place> listplace = placeDAO.getPlaceByType(resource);
		placeDAO.closeTransaction();
		
		return listplace;
		
	}
	
	public List<Place> findPlaces(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		placeDAO.beginTransaction();
		List<Place> listplace = placeDAO.findPlaces(startPosition, maxResults, sortField, sortDirections);
		placeDAO.closeTransaction();
		
		return listplace;
	}
	
	public List<Place> findPlacesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		placeDAO.beginTransaction();
		List<Place> listplace = placeDAO.findPlacesSearch(startPosition, maxResults, sortField, sortDirections, search);
		placeDAO.closeTransaction();
		
		return listplace;
	}
	
	public Integer countPlaces() {
		
		placeDAO.beginTransaction();
		Integer places = placeDAO.countPlaces();
		placeDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		placeDAO.beginTransaction();
		Integer places = placeDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		placeDAO.closeTransaction();
		
		return places;
	}
	
}
