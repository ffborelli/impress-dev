package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ContextLogDAO;
import br.ufabc.impress.model.ContextLog;

public class ContextLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextLogDAO contextLogDAO = new ContextLogDAO();

	public void create(ContextLog obj) {
		contextLogDAO.beginTransaction();
		contextLogDAO.save(obj);
		contextLogDAO.commitAndCloseTransaction();
	}

	public void update(ContextLog obj) {
		contextLogDAO.beginTransaction();
		contextLogDAO.update(obj);
		contextLogDAO.commitAndCloseTransaction();
	}

	public ContextLog find(Integer id) {
		contextLogDAO.beginTransaction();
		ContextLog obj = contextLogDAO.find(id);
		contextLogDAO.closeTransaction();
		return obj;
	}

	public List<ContextLog> listAll() {
		contextLogDAO.beginTransaction();
		List<ContextLog> result = contextLogDAO.findAll();
		contextLogDAO.closeTransaction();
		return result;
	}

	public void delete(ContextLog obj) {
		contextLogDAO.beginTransaction();
		ContextLog persistedCurso = contextLogDAO.findReferenceOnly(obj.getId());
		contextLogDAO.delete(persistedCurso);
		contextLogDAO.commitAndCloseTransaction();
	}
	
}
