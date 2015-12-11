package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.FusionContextDAO;
import br.ufabc.impress.model.FusionContext;

public class FusionContextFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionContextDAO fusionContextDAO = new FusionContextDAO();

	public void create(FusionContext obj) {
		fusionContextDAO.beginTransaction();
		fusionContextDAO.save(obj);
		fusionContextDAO.commitAndCloseTransaction();
	}

	public void update(FusionContext obj) {
		fusionContextDAO.beginTransaction();
		fusionContextDAO.update(obj);
		fusionContextDAO.commitAndCloseTransaction();
	}

	public FusionContext find(Integer id) {
		fusionContextDAO.beginTransaction();
		FusionContext obj = fusionContextDAO.find(id);
		fusionContextDAO.closeTransaction();
		return obj;
	}

	public List<FusionContext> listAll() {
		fusionContextDAO.beginTransaction();
		List<FusionContext> result = fusionContextDAO.findAll();
		fusionContextDAO.closeTransaction();
		return result;
	}

	public void delete(FusionContext obj) {
		fusionContextDAO.beginTransaction();
		FusionContext persistedCurso = fusionContextDAO.findReferenceOnly(obj.getId());
		fusionContextDAO.delete(persistedCurso);
		fusionContextDAO.commitAndCloseTransaction();
	}
	
}
