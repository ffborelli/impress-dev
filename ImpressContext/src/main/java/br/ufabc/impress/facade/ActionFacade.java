package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ActionDAO;
import br.ufabc.impress.model.Action;

public class ActionFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ActionDAO actionDAO = new ActionDAO();

	public void create(Action obj) {
		actionDAO.beginTransaction();
		actionDAO.save(obj);
		actionDAO.commitAndCloseTransaction();
	}

	public void update(Action obj) {
		actionDAO.beginTransaction();
		actionDAO.update(obj);
		actionDAO.commitAndCloseTransaction();
	}

	public Action find(Integer id) {
		actionDAO.beginTransaction();
		Action obj = actionDAO.find(id);
		actionDAO.closeTransaction();
		return obj;
	}

	public List<Action> listAll() {
		actionDAO.beginTransaction();
		List<Action> result = actionDAO.findAll();
		actionDAO.closeTransaction();
		return result;
	}

	public void delete(Action obj) {
		actionDAO.beginTransaction();
		Action persistedCurso = actionDAO.findReferenceOnly(obj.getId());
		actionDAO.delete(persistedCurso);
		actionDAO.commitAndCloseTransaction();
	}
	
}
