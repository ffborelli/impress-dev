package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.FusionRuleDAO;
import br.ufabc.impress.model.FusionRule;

public class FusionRuleFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionRuleDAO fusionRuleDAO = new FusionRuleDAO();

	public void create(FusionRule obj) {
		fusionRuleDAO.beginTransaction();
		fusionRuleDAO.save(obj);
		fusionRuleDAO.commitAndCloseTransaction();
	}

	public void update(FusionRule obj) {
		fusionRuleDAO.beginTransaction();
		fusionRuleDAO.update(obj);
		fusionRuleDAO.commitAndCloseTransaction();
	}

	public FusionRule find(Integer id) {
		fusionRuleDAO.beginTransaction();
		FusionRule obj = fusionRuleDAO.find(id);
		fusionRuleDAO.closeTransaction();
		return obj;
	}

	public List<FusionRule> listAll() {
		fusionRuleDAO.beginTransaction();
		List<FusionRule> result = fusionRuleDAO.findAll();
		fusionRuleDAO.closeTransaction();
		return result;
	}

	public void delete(FusionRule obj) {
		fusionRuleDAO.beginTransaction();
		FusionRule persistedCurso = fusionRuleDAO.findReferenceOnly(obj.getId());
		fusionRuleDAO.delete(persistedCurso);
		fusionRuleDAO.commitAndCloseTransaction();
	}
	
}
