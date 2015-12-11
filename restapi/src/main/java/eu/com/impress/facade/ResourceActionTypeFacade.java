package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ResourceActionTypeDAO;
import eu.com.impress.model.ResourceActionType;

public class ResourceActionTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceActionTypeDAO resourceActionTypeDAO = new ResourceActionTypeDAO();

	public void create(ResourceActionType obj) {
		resourceActionTypeDAO.beginTransaction();
		resourceActionTypeDAO.save(obj);
		resourceActionTypeDAO.commitAndCloseTransaction();
	}

	public void update(ResourceActionType obj) {
		resourceActionTypeDAO.beginTransaction();
		resourceActionTypeDAO.update(obj);
		resourceActionTypeDAO.commitAndCloseTransaction();
	}

	public ResourceActionType find(Integer id) {
		resourceActionTypeDAO.beginTransaction();
		ResourceActionType obj = resourceActionTypeDAO.find(id);
		resourceActionTypeDAO.closeTransaction();
		return obj;
	}

	public List<ResourceActionType> listAll() {
		resourceActionTypeDAO.beginTransaction();
		List<ResourceActionType> result = resourceActionTypeDAO.findAll();
		resourceActionTypeDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceActionType obj) {
		resourceActionTypeDAO.beginTransaction();
		ResourceActionType persistedCurso = resourceActionTypeDAO.findReferenceOnly(obj.getId());
		resourceActionTypeDAO.delete(persistedCurso);
		resourceActionTypeDAO.commitAndCloseTransaction();
	}
	
	public List<ResourceActionType> findResourceActionType(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		resourceActionTypeDAO.beginTransaction();
		List<ResourceActionType> listResourceActionType = resourceActionTypeDAO.findResourceActionTypes(startPosition, maxResults, sortField, sortDirections);
		resourceActionTypeDAO.closeTransaction();
		
		return listResourceActionType;
	}
	
	public List<ResourceActionType> findResourceActionTypeSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		resourceActionTypeDAO.beginTransaction();
		List<ResourceActionType> listResourceActionType = resourceActionTypeDAO.findResourceActionTypesSearch(startPosition, maxResults, sortField, sortDirections, search);
		resourceActionTypeDAO.closeTransaction();
		
		return listResourceActionType;
	}
	
	public Integer countResourceActionTypes() {
		
		resourceActionTypeDAO.beginTransaction();
		Integer places = resourceActionTypeDAO.countResourceActionTypes();
		resourceActionTypeDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		resourceActionTypeDAO.beginTransaction();
		Integer placeTypes = resourceActionTypeDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		resourceActionTypeDAO.closeTransaction();
		
		return placeTypes;
	}
	
}
