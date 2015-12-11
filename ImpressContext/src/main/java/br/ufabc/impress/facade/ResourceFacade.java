package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ResourceDAO;
import br.ufabc.impress.model.Resource;

public class ResourceFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceDAO resourceDAO = new ResourceDAO();

	public void create(Resource obj) {
		resourceDAO.beginTransaction();
		resourceDAO.save(obj);
		resourceDAO.commitAndCloseTransaction();
	}

	public void update(Resource obj) {
		resourceDAO.beginTransaction();
		resourceDAO.update(obj);
		resourceDAO.commitAndCloseTransaction();
	}

	public Resource find(Integer id) {
		resourceDAO.beginTransaction();
		Resource obj = resourceDAO.find(id);
		resourceDAO.closeTransaction();
		return obj;
	}

	public List<Resource> listAll() {
		resourceDAO.beginTransaction();
		List<Resource> result = resourceDAO.findAll();
		resourceDAO.closeTransaction();
		return result;
	}

	public void delete(Resource obj) {
		resourceDAO.beginTransaction();
		Resource persistedCurso = resourceDAO.findReferenceOnly(obj.getId());
		resourceDAO.delete(persistedCurso);
		resourceDAO.commitAndCloseTransaction();
	}
	
}
