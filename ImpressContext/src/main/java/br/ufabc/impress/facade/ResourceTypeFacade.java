package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ResourceTypeDAO;
import br.ufabc.impress.model.ResourceType;

public class ResourceTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceTypeDAO resourceTypeDAO = new ResourceTypeDAO();

	public void create(ResourceType obj) {
		resourceTypeDAO.beginTransaction();
		resourceTypeDAO.save(obj);
		resourceTypeDAO.commitAndCloseTransaction();
	}

	public void update(ResourceType obj) {
		resourceTypeDAO.beginTransaction();
		resourceTypeDAO.update(obj);
		resourceTypeDAO.commitAndCloseTransaction();
	}

	public ResourceType find(Integer id) {
		resourceTypeDAO.beginTransaction();
		ResourceType obj = resourceTypeDAO.find(id);
		resourceTypeDAO.closeTransaction();
		return obj;
	}

	public List<ResourceType> listAll() {
		resourceTypeDAO.beginTransaction();
		List<ResourceType> result = resourceTypeDAO.findAll();
		resourceTypeDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceType obj) {
		resourceTypeDAO.beginTransaction();
		ResourceType persistedCurso = resourceTypeDAO.findReferenceOnly(obj.getId());
		resourceTypeDAO.delete(persistedCurso);
		resourceTypeDAO.commitAndCloseTransaction();
	}
	
}
