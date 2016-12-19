package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ResourceLogDAO;
import eu.com.impress.model.ResourceLog;

public class ResourceLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceLogDAO resourceLogDAO = new ResourceLogDAO();

	public void create(ResourceLog obj) {
		resourceLogDAO.beginTransaction();
		resourceLogDAO.save(obj);
		resourceLogDAO.commitAndCloseTransaction();
	}

	public void update(ResourceLog obj) {
		resourceLogDAO.beginTransaction();
		resourceLogDAO.update(obj);
		resourceLogDAO.commitAndCloseTransaction();
	}

	public ResourceLog find(Integer id) {
		resourceLogDAO.beginTransaction();
		ResourceLog obj = resourceLogDAO.find(id);
		resourceLogDAO.closeTransaction();
		return obj;
	}

	public List<ResourceLog> listAll() {
		resourceLogDAO.beginTransaction();
		List<ResourceLog> result = resourceLogDAO.findAll();
		resourceLogDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceLog obj) {
		resourceLogDAO.beginTransaction();
		ResourceLog persistedCurso = resourceLogDAO.findReferenceOnly(obj.getId());
		resourceLogDAO.delete(persistedCurso);
		resourceLogDAO.commitAndCloseTransaction();
	}
	
	public List<ResourceLog> findResourceLogs(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		resourceLogDAO.beginTransaction();
		List<ResourceLog> listResourceLog = resourceLogDAO.findResourceLogs(startPosition, maxResults, sortField, sortDirections);
		resourceLogDAO.closeTransaction();
		
		return listResourceLog;
	}
	
	public List<ResourceLog> findResourceLogsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		resourceLogDAO.beginTransaction();
		List<ResourceLog> listResourceLog = resourceLogDAO.findResourceLogsSearch(startPosition, maxResults, sortField, sortDirections, search);
		resourceLogDAO.closeTransaction();
		
		return listResourceLog;
	}
	
	public Integer countResourceLogs() {
		
		resourceLogDAO.beginTransaction();
		Integer places = resourceLogDAO.countResourceLogs();
		resourceLogDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		resourceLogDAO.beginTransaction();
		Integer ResourceLogs = resourceLogDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		resourceLogDAO.closeTransaction();
		
		return ResourceLogs;
	}
	
}
