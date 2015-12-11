package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ScheduleDAO;
import eu.com.impress.model.Schedule;

public class ScheduleFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ScheduleDAO scheduleDAO = new ScheduleDAO();

	public void create(Schedule obj) {
		scheduleDAO.beginTransaction();
		scheduleDAO.save(obj);
		scheduleDAO.commitAndCloseTransaction();
	}

	public void update(Schedule obj) {
		scheduleDAO.beginTransaction();
		scheduleDAO.update(obj);
		scheduleDAO.commitAndCloseTransaction();
	}

	public Schedule find(Integer id) {
		scheduleDAO.beginTransaction();
		Schedule obj = scheduleDAO.find(id);
		scheduleDAO.closeTransaction();
		return obj;
	}

	public List<Schedule> listAll() {
		scheduleDAO.beginTransaction();
		List<Schedule> result = scheduleDAO.findAll();
		scheduleDAO.closeTransaction();
		return result;
	}

	public void delete(Schedule obj) {
		scheduleDAO.beginTransaction();
		Schedule persistedCurso = scheduleDAO.findReferenceOnly(obj.getId());
		scheduleDAO.delete(persistedCurso);
		scheduleDAO.commitAndCloseTransaction();
	}
	
	public List<Schedule> findSchedules(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		scheduleDAO.beginTransaction();
		List<Schedule> listSchedule = scheduleDAO.findSchedules(startPosition, maxResults, sortField, sortDirections);
		scheduleDAO.closeTransaction();
		
		return listSchedule;
	}
	
	public List<Schedule> findSchedulesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		scheduleDAO.beginTransaction();
		List<Schedule> listSchedule = scheduleDAO.findSchedulesSearch(startPosition, maxResults, sortField, sortDirections, search);
		scheduleDAO.closeTransaction();
		
		return listSchedule;
	}
	
	public Integer countSchedules() {
		
		scheduleDAO.beginTransaction();
		Integer schedules = scheduleDAO.countSchedules();
		scheduleDAO.closeTransaction();
		
		return schedules;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		scheduleDAO.beginTransaction();
		Integer schedules = scheduleDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		scheduleDAO.closeTransaction();
		
		return schedules;
	}
	
}
