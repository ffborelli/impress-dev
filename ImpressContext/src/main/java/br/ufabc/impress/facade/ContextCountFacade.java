package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ContextCountDAO;
import br.ufabc.impress.model.ContextCount;
import br.ufabc.impress.model.Resource;

public class ContextCountFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextCountDAO contextCountDAO = new ContextCountDAO();

	public void create(ContextCount obj) {
		contextCountDAO.beginTransaction();
		contextCountDAO.save(obj);
		contextCountDAO.commitAndCloseTransaction();
	}

	public void update(ContextCount obj) {
		contextCountDAO.beginTransaction();
		contextCountDAO.update(obj);
		contextCountDAO.commitAndCloseTransaction();
	}

	public ContextCount find(Integer id) {
		contextCountDAO.beginTransaction();
		ContextCount obj = contextCountDAO.find(id);
		contextCountDAO.closeTransaction();
		return obj;
	}

	public List<ContextCount> listAll() {
		contextCountDAO.beginTransaction();
		List<ContextCount> result = contextCountDAO.findAll();
		contextCountDAO.closeTransaction();
		return result;
	}

	public void delete(ContextCount obj) {
		contextCountDAO.beginTransaction();
		ContextCount persistedCurso = contextCountDAO.findReferenceOnly(obj.getId());
		contextCountDAO.delete(persistedCurso);
		contextCountDAO.commitAndCloseTransaction();
	}
	
}
