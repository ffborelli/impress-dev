package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ContextDAO;
import eu.com.impress.model.Context;
import eu.com.impress.model.Context;

public class ContextFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextDAO contextDAO = new ContextDAO();

	public void create(Context obj) {
		contextDAO.beginTransaction();
		contextDAO.save(obj);
		contextDAO.commitAndCloseTransaction();
	}

	public void update(Context obj) {
		contextDAO.beginTransaction();
		contextDAO.update(obj);
		contextDAO.commitAndCloseTransaction();
	}

	public Context find(Integer id) {
		contextDAO.beginTransaction();
		Context obj = contextDAO.find(id);
		contextDAO.closeTransaction();
		return obj;
	}

	public List<Context> listAll() {
		contextDAO.beginTransaction();
		List<Context> result = contextDAO.findAll();
		contextDAO.closeTransaction();
		return result;
	}

	public void delete(Context obj) {
		contextDAO.beginTransaction();
		Context persistedCurso = contextDAO.findReferenceOnly(obj.getId());
		contextDAO.delete(persistedCurso);
		contextDAO.commitAndCloseTransaction();
	}
	
	public List<Context> findContexts(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		contextDAO.beginTransaction();
		List<Context> listContext = contextDAO.findContexts(startPosition, maxResults, sortField, sortDirections);
		contextDAO.closeTransaction();
		
		return listContext;
	}
	
	public List<Context> findContextsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		contextDAO.beginTransaction();
		List<Context> listContext = contextDAO.findContextsSearch(startPosition, maxResults, sortField, sortDirections, search);
		contextDAO.closeTransaction();
		
		return listContext;
	}
	
	public Integer countPlaceTypes() {
		
		contextDAO.beginTransaction();
		Integer places = contextDAO.countContexts();
		contextDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		contextDAO.beginTransaction();
		Integer resourceTypes = contextDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		contextDAO.closeTransaction();
		
		return resourceTypes;
	}
	
	
}
