package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ResourceContextDAO;
import br.ufabc.impress.model.ResourceContext;

public class ResourceContextFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceContextDAO resourceContextDAO = new ResourceContextDAO();

	public void create(ResourceContext obj) {
		resourceContextDAO.beginTransaction();
		resourceContextDAO.save(obj);
		resourceContextDAO.commitAndCloseTransaction();
	}

	public void update(ResourceContext obj) {
		resourceContextDAO.beginTransaction();
		resourceContextDAO.update(obj);
		resourceContextDAO.commitAndCloseTransaction();
	}

	public ResourceContext find(Integer id) {
		resourceContextDAO.beginTransaction();
		ResourceContext obj = resourceContextDAO.find(id);
		resourceContextDAO.closeTransaction();
		return obj;
	}

	public List<ResourceContext> listAll() {
		resourceContextDAO.beginTransaction();
		List<ResourceContext> result = resourceContextDAO.findAll();
		resourceContextDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceContext obj) {
		resourceContextDAO.beginTransaction();
		ResourceContext persistedCurso = resourceContextDAO.findReferenceOnly(obj.getId());
		resourceContextDAO.delete(persistedCurso);
		resourceContextDAO.commitAndCloseTransaction();
	}
	
}
