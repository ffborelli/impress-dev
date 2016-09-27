package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.FusionDAO;
import br.ufabc.impress.model.Fusion;

public class FusionFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionDAO fusionDAO = new FusionDAO();

	public void create(Fusion obj) {
		fusionDAO.beginTransaction();
		fusionDAO.save(obj);
		fusionDAO.commitAndCloseTransaction();
	}

	public void update(Fusion obj) {
		fusionDAO.beginTransaction();
		fusionDAO.update(obj);
		fusionDAO.commitAndCloseTransaction();
	}

	public Fusion find(Integer id) {
		fusionDAO.beginTransaction();
		Fusion obj = fusionDAO.find(id);
		fusionDAO.closeTransaction();
		return obj;
	}

	public List<Fusion> listAll() {
		fusionDAO.beginTransaction();
		List<Fusion> result = fusionDAO.findAll();
		fusionDAO.closeTransaction();
		return result;
	}

	public void delete(Fusion obj) {
		fusionDAO.beginTransaction();
		Fusion persistedCurso = fusionDAO.findReferenceOnly(obj.getId());
		fusionDAO.delete(persistedCurso);
		fusionDAO.commitAndCloseTransaction();
	}
	
}
