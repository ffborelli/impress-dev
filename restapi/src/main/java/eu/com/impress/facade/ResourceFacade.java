package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ResourceDAO;
import eu.com.impress.model.Resource;

public class ResourceFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceDAO resourceDAO = new ResourceDAO();

	public void create(Resource obj) {
		resourceDAO.beginTransaction();
		resourceDAO.save(obj);
		resourceDAO.commitAndCloseTransaction();
	}

	public void update(Resource obj) {
		resourceDAO.beginTransaction();
		resourceDAO.update(obj);
		resourceDAO.commitAndCloseTransaction();
	}

	public Resource find(Integer id) {
		resourceDAO.beginTransaction();
		Resource obj = resourceDAO.find(id);
		resourceDAO.closeTransaction();
		return obj;
	}

	public List<Resource> listAll() {
		resourceDAO.beginTransaction();
		List<Resource> result = resourceDAO.findAll();
		resourceDAO.closeTransaction();
		return result;
	}

	public void delete(Resource obj) {
		resourceDAO.beginTransaction();
		Resource persistedCurso = resourceDAO.findReferenceOnly(obj.getId());
		resourceDAO.delete(persistedCurso);
		resourceDAO.commitAndCloseTransaction();
	}
	public List<Resource> getAllSensorsOrActuators(int resource) {
		resourceDAO.beginTransaction();
		List<Resource> result = resourceDAO.getAllSensorsOrActuators(resource);
		resourceDAO.closeTransaction();
		return result;
	}
	
	public List<Resource> findResources(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		resourceDAO.beginTransaction();
		List<Resource> listResource = resourceDAO.findResources(startPosition, maxResults, sortField, sortDirections);
		resourceDAO.closeTransaction();
		
		return listResource;
	}
	
	public List<Resource> findResourcesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		resourceDAO.beginTransaction();
		List<Resource> listResource = resourceDAO.findResourcesSearch(startPosition, maxResults, sortField, sortDirections, search);
		resourceDAO.closeTransaction();
		
		return listResource;
	}
	
	public Integer countResources() {
		
		resourceDAO.beginTransaction();
		Integer places = resourceDAO.countResources();
		resourceDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		resourceDAO.beginTransaction();
		Integer resources = resourceDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		resourceDAO.closeTransaction();
		
		return resources;
	}
	
}
