package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.FusionLogDAO;
import eu.com.impress.model.FusionLog;

public class FusionLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionLogDAO fusionLogDAO = new FusionLogDAO();

	public void create(FusionLog obj) {
		fusionLogDAO.beginTransaction();
		fusionLogDAO.save(obj);
		fusionLogDAO.commitAndCloseTransaction();
	}

	public void update(FusionLog obj) {
		fusionLogDAO.beginTransaction();
		fusionLogDAO.update(obj);
		fusionLogDAO.commitAndCloseTransaction();
	}

	public FusionLog find(Integer id) {
		fusionLogDAO.beginTransaction();
		FusionLog obj = fusionLogDAO.find(id);
		fusionLogDAO.closeTransaction();
		return obj;
	}

	public List<FusionLog> listAll() {
		fusionLogDAO.beginTransaction();
		List<FusionLog> result = fusionLogDAO.findAll();
		fusionLogDAO.closeTransaction();
		return result;
	}

	public void delete(FusionLog obj) {
		fusionLogDAO.beginTransaction();
		FusionLog persistedCurso = fusionLogDAO.findReferenceOnly(obj.getId());
		fusionLogDAO.delete(persistedCurso);
		fusionLogDAO.commitAndCloseTransaction();
	}
	
	public List<FusionLog> findFusionLogs(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		fusionLogDAO.beginTransaction();
		List<FusionLog> listFusionLog = fusionLogDAO.findFusionLogs(startPosition, maxResults, sortField, sortDirections);
		fusionLogDAO.closeTransaction();
		
		return listFusionLog;
	}
	
	public List<FusionLog> findFusionLogsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		fusionLogDAO.beginTransaction();
		List<FusionLog> listFusionLog = fusionLogDAO.findFusionLogsSearch(startPosition, maxResults, sortField, sortDirections, search);
		fusionLogDAO.closeTransaction();
		
		return listFusionLog;
	}
	
	public Integer countFusionLogs() {
		
		fusionLogDAO.beginTransaction();
		Integer fusionLogs = fusionLogDAO.countFusionLogs();
		fusionLogDAO.closeTransaction();
		
		return fusionLogs;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		fusionLogDAO.beginTransaction();
		Integer FusionLogs = fusionLogDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		fusionLogDAO.closeTransaction();
		
		return FusionLogs;
	}
	
}
