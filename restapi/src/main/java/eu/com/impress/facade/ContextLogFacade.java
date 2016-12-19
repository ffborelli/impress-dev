package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ContextLogDAO;
import eu.com.impress.model.ContextLog;

public class ContextLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextLogDAO contextLogDAO = new ContextLogDAO();

	public void create(ContextLog obj) {
		contextLogDAO.beginTransaction();
		contextLogDAO.save(obj);
		contextLogDAO.commitAndCloseTransaction();
	}

	public void update(ContextLog obj) {
		contextLogDAO.beginTransaction();
		contextLogDAO.update(obj);
		contextLogDAO.commitAndCloseTransaction();
	}

	public ContextLog find(Integer id) {
		contextLogDAO.beginTransaction();
		ContextLog obj = contextLogDAO.find(id);
		contextLogDAO.closeTransaction();
		return obj;
	}

	public List<ContextLog> listAll() {
		contextLogDAO.beginTransaction();
		List<ContextLog> result = contextLogDAO.findAll();
		contextLogDAO.closeTransaction();
		return result;
	}

	public void delete(ContextLog obj) {
		contextLogDAO.beginTransaction();
		ContextLog persistedCurso = contextLogDAO.findReferenceOnly(obj.getId());
		contextLogDAO.delete(persistedCurso);
		contextLogDAO.commitAndCloseTransaction();
	}
	
	public List<ContextLog> findContextLogs(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		contextLogDAO.beginTransaction();
		List<ContextLog> listContextLog = contextLogDAO.findContextLogs(startPosition, maxResults, sortField, sortDirections);
		contextLogDAO.closeTransaction();
		
		return listContextLog;
	}
	
	public List<ContextLog> findContextLogsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		contextLogDAO.beginTransaction();
		List<ContextLog> listContextLog = contextLogDAO.findContextLogsSearch(startPosition, maxResults, sortField, sortDirections, search);
		contextLogDAO.closeTransaction();
		
		return listContextLog;
	}
	
	public Integer countContextLogs() {
		
		contextLogDAO.beginTransaction();
		Integer contextLogs = contextLogDAO.countContextLogs();
		contextLogDAO.closeTransaction();
		
		return contextLogs;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		contextLogDAO.beginTransaction();
		Integer contextLogs = contextLogDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		contextLogDAO.closeTransaction();
		
		return contextLogs;
	}
	
}
