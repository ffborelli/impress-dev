package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ResourceLogDAO;
import br.ufabc.impress.model.ResourceLog;

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
	
	public ResourceLog getLastByResource(int resource){
		
		resourceLogDAO.beginTransaction();
		ResourceLog r = resourceLogDAO.getLastByResource(resource);
		resourceLogDAO.closeTransaction();
		
		return r;
	}
	
	
}
