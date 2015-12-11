package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.RuleDAO;
import br.ufabc.impress.model.Rule;

public class RuleFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private RuleDAO ruleDAO = new RuleDAO();

	public void create(Rule obj) {
		ruleDAO.beginTransaction();
		ruleDAO.save(obj);
		ruleDAO.commitAndCloseTransaction();
	}

	public void update(Rule obj) {
		ruleDAO.beginTransaction();
		ruleDAO.update(obj);
		ruleDAO.commitAndCloseTransaction();
	}

	public Rule find(Integer id) {
		ruleDAO.beginTransaction();
		Rule obj = ruleDAO.find(id);
		ruleDAO.closeTransaction();
		return obj;
	}

	public List<Rule> listAll() {
		ruleDAO.beginTransaction();
		List<Rule> result = ruleDAO.findAll();
		ruleDAO.closeTransaction();
		return result;
	}

	public void delete(Rule obj) {
		ruleDAO.beginTransaction();
		Rule persistedCurso = ruleDAO.findReferenceOnly(obj.getId());
		ruleDAO.delete(persistedCurso);
		ruleDAO.commitAndCloseTransaction();
	}
	
}
