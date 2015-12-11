package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ResourceScheduleDAO;
import eu.com.impress.model.ResourceSchedule;

public class ResourceScheduleFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceScheduleDAO resourceScheduleDAO = new ResourceScheduleDAO();

	public void create(ResourceSchedule obj) {
		resourceScheduleDAO.beginTransaction();
		resourceScheduleDAO.save(obj);
		resourceScheduleDAO.commitAndCloseTransaction();
	}

	public void update(ResourceSchedule obj) {
		resourceScheduleDAO.beginTransaction();
		resourceScheduleDAO.update(obj);
		resourceScheduleDAO.commitAndCloseTransaction();
	}

	public ResourceSchedule find(Integer id) {
		resourceScheduleDAO.beginTransaction();
		ResourceSchedule obj = resourceScheduleDAO.find(id);
		resourceScheduleDAO.closeTransaction();
		return obj;
	}

	public List<ResourceSchedule> listAll() {
		resourceScheduleDAO.beginTransaction();
		List<ResourceSchedule> result = resourceScheduleDAO.findAll();
		resourceScheduleDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceSchedule obj) {
		resourceScheduleDAO.beginTransaction();
		ResourceSchedule persistedCurso = resourceScheduleDAO.findReferenceOnly(obj.getId());
		resourceScheduleDAO.delete(persistedCurso);
		resourceScheduleDAO.commitAndCloseTransaction();
	}
	
}
