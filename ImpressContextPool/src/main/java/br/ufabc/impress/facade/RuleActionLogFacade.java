package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.RuleActionLogDAO;
import br.ufabc.impress.model.RuleActionLog;

public class RuleActionLogFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private RuleActionLogDAO ruleActionLogDAO = new RuleActionLogDAO();

	public void create(RuleActionLog obj) {
		ruleActionLogDAO.beginTransaction();
		ruleActionLogDAO.save(obj);
		ruleActionLogDAO.commitAndCloseTransaction();
	}

	public void update(RuleActionLog obj) {
		ruleActionLogDAO.beginTransaction();
		ruleActionLogDAO.update(obj);
		ruleActionLogDAO.commitAndCloseTransaction();
	}

	public RuleActionLog find(Integer id) {
		ruleActionLogDAO.beginTransaction();
		RuleActionLog obj = ruleActionLogDAO.find(id);
		ruleActionLogDAO.closeTransaction();
		return obj;
	}

	public List<RuleActionLog> listAll() {
		ruleActionLogDAO.beginTransaction();
		List<RuleActionLog> result = ruleActionLogDAO.findAll();
		ruleActionLogDAO.closeTransaction();
		return result;
	}

	public void delete(RuleActionLog obj) {
		ruleActionLogDAO.beginTransaction();
		RuleActionLog persistedCurso = ruleActionLogDAO.findReferenceOnly(obj.getId());
		ruleActionLogDAO.delete(persistedCurso);
		ruleActionLogDAO.commitAndCloseTransaction();
	}
	
}
