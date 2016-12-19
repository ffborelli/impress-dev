package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ResourceFusionLogDAO;
import eu.com.impress.model.ResourceFusionLog;

public class ResourceFusionLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceFusionLogDAO resourceFusionLogDAO = new ResourceFusionLogDAO();

	public void create(ResourceFusionLog obj) {
		resourceFusionLogDAO.beginTransaction();
		resourceFusionLogDAO.save(obj);
		resourceFusionLogDAO.commitAndCloseTransaction();
	}

	public void update(ResourceFusionLog obj) {
		resourceFusionLogDAO.beginTransaction();
		resourceFusionLogDAO.update(obj);
		resourceFusionLogDAO.commitAndCloseTransaction();
	}

	public ResourceFusionLog find(Integer id) {
		resourceFusionLogDAO.beginTransaction();
		ResourceFusionLog obj = resourceFusionLogDAO.find(id);
		resourceFusionLogDAO.closeTransaction();
		return obj;
	}

	public List<ResourceFusionLog> listAll() {
		resourceFusionLogDAO.beginTransaction();
		List<ResourceFusionLog> result = resourceFusionLogDAO.findAll();
		resourceFusionLogDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceFusionLog obj) {
		resourceFusionLogDAO.beginTransaction();
		ResourceFusionLog persistedCurso = resourceFusionLogDAO.findReferenceOnly(obj.getId());
		resourceFusionLogDAO.delete(persistedCurso);
		resourceFusionLogDAO.commitAndCloseTransaction();
	}
	
}
