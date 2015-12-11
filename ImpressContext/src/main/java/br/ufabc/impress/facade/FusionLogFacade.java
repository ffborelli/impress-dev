package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.FusionLogDAO;
import br.ufabc.impress.model.FusionLog;

public class FusionLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionLogDAO fusionLogDAO = new FusionLogDAO();

	public void create(FusionLog obj) {
		fusionLogDAO.beginTransaction();
		fusionLogDAO.save(obj);
		fusionLogDAO.commitAndCloseTransaction();
	}

	public void update(FusionLog obj) {
		fusionLogDAO.beginTransaction();
		fusionLogDAO.update(obj);
		fusionLogDAO.commitAndCloseTransaction();
	}

	public FusionLog find(Integer id) {
		fusionLogDAO.beginTransaction();
		FusionLog obj = fusionLogDAO.find(id);
		fusionLogDAO.closeTransaction();
		return obj;
	}

	public List<FusionLog> listAll() {
		fusionLogDAO.beginTransaction();
		List<FusionLog> result = fusionLogDAO.findAll();
		fusionLogDAO.closeTransaction();
		return result;
	}

	public void delete(FusionLog obj) {
		fusionLogDAO.beginTransaction();
		FusionLog persistedCurso = fusionLogDAO.findReferenceOnly(obj.getId());
		fusionLogDAO.delete(persistedCurso);
		fusionLogDAO.commitAndCloseTransaction();
	}
	
}
