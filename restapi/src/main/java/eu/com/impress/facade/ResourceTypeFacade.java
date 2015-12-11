package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ResourceTypeDAO;
import eu.com.impress.model.ResourceType;

public class ResourceTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceTypeDAO resourceTypeDAO = new ResourceTypeDAO();

	public void create(ResourceType obj) {
		resourceTypeDAO.beginTransaction();
		resourceTypeDAO.save(obj);
		resourceTypeDAO.commitAndCloseTransaction();
	}

	public void update(ResourceType obj) {
		resourceTypeDAO.beginTransaction();
		resourceTypeDAO.update(obj);
		resourceTypeDAO.commitAndCloseTransaction();
	}

	public ResourceType find(Integer id) {
		resourceTypeDAO.beginTransaction();
		ResourceType obj = resourceTypeDAO.find(id);
		resourceTypeDAO.closeTransaction();
		return obj;
	}

	public List<ResourceType> listAll() {
		resourceTypeDAO.beginTransaction();
		List<ResourceType> result = resourceTypeDAO.findAll();
		resourceTypeDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceType obj) {
		resourceTypeDAO.beginTransaction();
		ResourceType persistedCurso = resourceTypeDAO.findReferenceOnly(obj.getId());
		resourceTypeDAO.delete(persistedCurso);
		resourceTypeDAO.commitAndCloseTransaction();
	}
	
	public List<ResourceType> findResourceTypes(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		resourceTypeDAO.beginTransaction();
		List<ResourceType> listResourceType = resourceTypeDAO.findResourceTypes(startPosition, maxResults, sortField, sortDirections);
		resourceTypeDAO.closeTransaction();
		
		return listResourceType;
	}
	
	public List<ResourceType> findResourceTypesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		resourceTypeDAO.beginTransaction();
		List<ResourceType> listResourceType = resourceTypeDAO.findResourceTypesSearch(startPosition, maxResults, sortField, sortDirections, search);
		resourceTypeDAO.closeTransaction();
		
		return listResourceType;
	}
	
	public Integer countPlaceTypes() {
		
		resourceTypeDAO.beginTransaction();
		Integer places = resourceTypeDAO.countResourceTypes();
		resourceTypeDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		resourceTypeDAO.beginTransaction();
		Integer resourceTypes = resourceTypeDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		resourceTypeDAO.closeTransaction();
		
		return resourceTypes;
	}
	
	
}
