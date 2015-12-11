package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ContextCountDAO;
import eu.com.impress.model.ContextCount;

public class ContextCountFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextCountDAO contextCountDAO = new ContextCountDAO();

	public void create(ContextCount obj) {
		contextCountDAO.beginTransaction();
		contextCountDAO.save(obj);
		contextCountDAO.commitAndCloseTransaction();
	}

	public void update(ContextCount obj) {
		contextCountDAO.beginTransaction();
		contextCountDAO.update(obj);
		contextCountDAO.commitAndCloseTransaction();
	}

	public ContextCount find(Integer id) {
		contextCountDAO.beginTransaction();
		ContextCount obj = contextCountDAO.find(id);
		contextCountDAO.closeTransaction();
		return obj;
	}

	public List<ContextCount> listAll() {
		contextCountDAO.beginTransaction();
		List<ContextCount> result = contextCountDAO.findAll();
		contextCountDAO.closeTransaction();
		return result;
	}

	public void delete(ContextCount obj) {
		contextCountDAO.beginTransaction();
		ContextCount persistedCurso = contextCountDAO.findReferenceOnly(obj.getId());
		contextCountDAO.delete(persistedCurso);
		contextCountDAO.commitAndCloseTransaction();
	}
	
	public List<ContextCount> findContextCounts(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		contextCountDAO.beginTransaction();
		List<ContextCount> listContextCount = contextCountDAO.findContextCounts(startPosition, maxResults, sortField, sortDirections);
		contextCountDAO.closeTransaction();
		
		return listContextCount;
	}
	
	public List<ContextCount> findContextCountsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		contextCountDAO.beginTransaction();
		List<ContextCount> listContextCount = contextCountDAO.findContextCountsSearch(startPosition, maxResults, sortField, sortDirections, search);
		contextCountDAO.closeTransaction();
		
		return listContextCount;
	}
	
	public Integer countContextCounts() {
		
		contextCountDAO.beginTransaction();
		Integer places = contextCountDAO.countContextCounts();
		contextCountDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		contextCountDAO.beginTransaction();
		Integer resources = contextCountDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		contextCountDAO.closeTransaction();
		
		return resources;
	}
	
}
