package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ContextDAO;
import br.ufabc.impress.model.Context;

public class ContextFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextDAO contextDAO = new ContextDAO();

	public void create(Context obj) {
		contextDAO.beginTransaction();
		contextDAO.save(obj);
		contextDAO.commitAndCloseTransaction();
	}

	public void update(Context obj) {
		contextDAO.beginTransaction();
		contextDAO.update(obj);
		contextDAO.commitAndCloseTransaction();
	}

	public Context find(Integer id) {
		contextDAO.beginTransaction();
		Context obj = contextDAO.find(id);
		contextDAO.closeTransaction();
		return obj;
	}

	public List<Context> listAll() {
		contextDAO.beginTransaction();
		List<Context> result = contextDAO.findAll();
		contextDAO.closeTransaction();
		return result;
	}

	public void delete(Context obj) {
		contextDAO.beginTransaction();
		Context persistedCurso = contextDAO.findReferenceOnly(obj.getId());
		contextDAO.delete(persistedCurso);
		contextDAO.commitAndCloseTransaction();
	}
	
}
