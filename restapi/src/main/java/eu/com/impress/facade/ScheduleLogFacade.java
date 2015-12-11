package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ScheduleLogDAO;
import eu.com.impress.model.ScheduleLog;

public class ScheduleLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ScheduleLogDAO scheduleLogDAO = new ScheduleLogDAO();

	public void create(ScheduleLog obj) {
		scheduleLogDAO.beginTransaction();
		scheduleLogDAO.save(obj);
		scheduleLogDAO.commitAndCloseTransaction();
	}

	public void update(ScheduleLog obj) {
		scheduleLogDAO.beginTransaction();
		scheduleLogDAO.update(obj);
		scheduleLogDAO.commitAndCloseTransaction();
	}

	public ScheduleLog find(Integer id) {
		scheduleLogDAO.beginTransaction();
		ScheduleLog obj = scheduleLogDAO.find(id);
		scheduleLogDAO.closeTransaction();
		return obj;
	}

	public List<ScheduleLog> listAll() {
		scheduleLogDAO.beginTransaction();
		List<ScheduleLog> result = scheduleLogDAO.findAll();
		scheduleLogDAO.closeTransaction();
		return result;
	}

	public void delete(ScheduleLog obj) {
		scheduleLogDAO.beginTransaction();
		ScheduleLog persistedCurso = scheduleLogDAO.findReferenceOnly(obj.getId());
		scheduleLogDAO.delete(persistedCurso);
		scheduleLogDAO.commitAndCloseTransaction();
	}
	
	public List<ScheduleLog> findScheduleLogs(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		scheduleLogDAO.beginTransaction();
		List<ScheduleLog> listScheduleLog = scheduleLogDAO.findScheduleLogs(startPosition, maxResults, sortField, sortDirections);
		scheduleLogDAO.closeTransaction();
		
		return listScheduleLog;
	}
	
	public List<ScheduleLog> findScheduleLogsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		scheduleLogDAO.beginTransaction();
		List<ScheduleLog> listScheduleLog = scheduleLogDAO.findScheduleLogsSearch(startPosition, maxResults, sortField, sortDirections, search);
		scheduleLogDAO.closeTransaction();
		
		return listScheduleLog;
	}
	
	public Integer countScheduleLogs() {
		
		scheduleLogDAO.beginTransaction();
		Integer scheduleLogs = scheduleLogDAO.countScheduleLogs();
		scheduleLogDAO.closeTransaction();
		
		return scheduleLogs;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		scheduleLogDAO.beginTransaction();
		Integer scheduleLogs = scheduleLogDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		scheduleLogDAO.closeTransaction();
		
		return scheduleLogs;
	}
	
}
