package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.FusionRuleLogDAO;
import eu.com.impress.model.FusionRuleLog;

public class FusionRuleLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionRuleLogDAO fusionRuleLogDAO = new FusionRuleLogDAO();

	public void create(FusionRuleLog obj) {
		fusionRuleLogDAO.beginTransaction();
		fusionRuleLogDAO.save(obj);
		fusionRuleLogDAO.commitAndCloseTransaction();
	}

	public void update(FusionRuleLog obj) {
		fusionRuleLogDAO.beginTransaction();
		fusionRuleLogDAO.update(obj);
		fusionRuleLogDAO.commitAndCloseTransaction();
	}

	public FusionRuleLog find(Integer id) {
		fusionRuleLogDAO.beginTransaction();
		FusionRuleLog obj = fusionRuleLogDAO.find(id);
		fusionRuleLogDAO.closeTransaction();
		return obj;
	}

	public List<FusionRuleLog> listAll() {
		fusionRuleLogDAO.beginTransaction();
		List<FusionRuleLog> result = fusionRuleLogDAO.findAll();
		fusionRuleLogDAO.closeTransaction();
		return result;
	}

	public void delete(FusionRuleLog obj) {
		fusionRuleLogDAO.beginTransaction();
		FusionRuleLog persistedCurso = fusionRuleLogDAO.findReferenceOnly(obj.getId());
		fusionRuleLogDAO.delete(persistedCurso);
		fusionRuleLogDAO.commitAndCloseTransaction();
	}
	
}
