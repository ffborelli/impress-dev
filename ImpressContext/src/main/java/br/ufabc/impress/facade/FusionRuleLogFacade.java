package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.FusionRuleLogDAO;
import br.ufabc.impress.model.FusionRuleLog;
import br.ufabc.impress.model.RuleActionLog;

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
	
	public List<FusionRuleLog> getByRuleAction(List<RuleActionLog> list) {
		fusionRuleLogDAO.beginTransaction();
		List<FusionRuleLog> result = fusionRuleLogDAO.getByRuleAction(list);
		fusionRuleLogDAO.closeTransaction();
		return result;
	}
	
}
