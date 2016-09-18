package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ResourceActionTypeDAO;
import br.ufabc.impress.model.ResourceActionType;

public class ResourceActionTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceActionTypeDAO resourceActionTypeDAO = new ResourceActionTypeDAO();

	public void create(ResourceActionType obj) {
		resourceActionTypeDAO.beginTransaction();
		resourceActionTypeDAO.save(obj);
		resourceActionTypeDAO.commitAndCloseTransaction();
	}

	public void update(ResourceActionType obj) {
		resourceActionTypeDAO.beginTransaction();
		resourceActionTypeDAO.update(obj);
		resourceActionTypeDAO.commitAndCloseTransaction();
	}

	public ResourceActionType find(Integer id) {
		resourceActionTypeDAO.beginTransaction();
		ResourceActionType obj = resourceActionTypeDAO.find(id);
		resourceActionTypeDAO.closeTransaction();
		return obj;
	}

	public List<ResourceActionType> listAll() {
		resourceActionTypeDAO.beginTransaction();
		List<ResourceActionType> result = resourceActionTypeDAO.findAll();
		resourceActionTypeDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceActionType obj) {
		resourceActionTypeDAO.beginTransaction();
		ResourceActionType persistedCurso = resourceActionTypeDAO.findReferenceOnly(obj.getId());
		resourceActionTypeDAO.delete(persistedCurso);
		resourceActionTypeDAO.commitAndCloseTransaction();
	}
	
}
