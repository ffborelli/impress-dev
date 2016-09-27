package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ContextTypeDAO;
import br.ufabc.impress.model.ContextType;

public class ContextTypeFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ContextTypeDAO contextTypeDAO = new ContextTypeDAO();

	public void create(ContextType obj) {
		contextTypeDAO.beginTransaction();
		contextTypeDAO.save(obj);
		contextTypeDAO.commitAndCloseTransaction();
	}

	public void update(ContextType obj) {
		contextTypeDAO.beginTransaction();
		contextTypeDAO.update(obj);
		contextTypeDAO.commitAndCloseTransaction();
	}

	public ContextType find(Integer id) {
		contextTypeDAO.beginTransaction();
		ContextType obj = contextTypeDAO.find(id);
		contextTypeDAO.closeTransaction();
		return obj;
	}

	public List<ContextType> listAll() {
		contextTypeDAO.beginTransaction();
		List<ContextType> result = contextTypeDAO.findAll();
		contextTypeDAO.closeTransaction();
		return result;
	}

	public void delete(ContextType obj) {
		contextTypeDAO.beginTransaction();
		ContextType persistedCurso = contextTypeDAO.findReferenceOnly(obj.getId());
		contextTypeDAO.delete(persistedCurso);
		contextTypeDAO.commitAndCloseTransaction();
	}
	
}
