package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ContextTypeDAO;
import eu.com.impress.model.ContextType;

public class ContextTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextTypeDAO contextTypeDAO = new ContextTypeDAO();

	public void create(ContextType obj) {
		contextTypeDAO.beginTransaction();
		contextTypeDAO.save(obj);
		contextTypeDAO.commitAndCloseTransaction();
	}

	public void update(ContextType obj) {
		contextTypeDAO.beginTransaction();
		contextTypeDAO.update(obj);
		contextTypeDAO.commitAndCloseTransaction();
	}

	public ContextType find(Integer id) {
		contextTypeDAO.beginTransaction();
		ContextType obj = contextTypeDAO.find(id);
		contextTypeDAO.closeTransaction();
		return obj;
	}

	public List<ContextType> listAll() {
		contextTypeDAO.beginTransaction();
		List<ContextType> result = contextTypeDAO.findAll();
		contextTypeDAO.closeTransaction();
		return result;
	}

	public void delete(ContextType obj) {
		contextTypeDAO.beginTransaction();
		ContextType persistedCurso = contextTypeDAO.findReferenceOnly(obj.getId());
		contextTypeDAO.delete(persistedCurso);
		contextTypeDAO.commitAndCloseTransaction();
	}
	
	public List<ContextType> findContextTypes(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		contextTypeDAO.beginTransaction();
		List<ContextType> listContextType = contextTypeDAO.findContextTypes(startPosition, maxResults, sortField, sortDirections);
		contextTypeDAO.closeTransaction();
		
		return listContextType;
	}
	
	public List<ContextType> findContextTypesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		contextTypeDAO.beginTransaction();
		List<ContextType> listContextType = contextTypeDAO.findContextTypesSearch(startPosition, maxResults, sortField, sortDirections, search);
		contextTypeDAO.closeTransaction();
		
		return listContextType;
	}
	
	public Integer countPlaceTypes() {
		
		contextTypeDAO.beginTransaction();
		Integer places = contextTypeDAO.countContextTypes();
		contextTypeDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		contextTypeDAO.beginTransaction();
		Integer resourceTypes = contextTypeDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		contextTypeDAO.closeTransaction();
		
		return resourceTypes;
	}
	
	
}
