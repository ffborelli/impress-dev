package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.RuleContextDAO;
import br.ufabc.impress.model.RuleContext;

public class RuleContextFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private RuleContextDAO ruleContextDAO = new RuleContextDAO();

	public void create(RuleContext obj) {
		ruleContextDAO.beginTransaction();
		ruleContextDAO.save(obj);
		ruleContextDAO.commitAndCloseTransaction();
	}

	public void update(RuleContext obj) {
		ruleContextDAO.beginTransaction();
		ruleContextDAO.update(obj);
		ruleContextDAO.commitAndCloseTransaction();
	}

	public RuleContext find(Integer id) {
		ruleContextDAO.beginTransaction();
		RuleContext obj = ruleContextDAO.find(id);
		ruleContextDAO.closeTransaction();
		return obj;
	}

	public List<RuleContext> listAll() {
		ruleContextDAO.beginTransaction();
		List<RuleContext> result = ruleContextDAO.findAll();
		ruleContextDAO.closeTransaction();
		return result;
	}

	public void delete(RuleContext obj) {
		ruleContextDAO.beginTransaction();
		RuleContext persistedCurso = ruleContextDAO.findReferenceOnly(obj.getId());
		ruleContextDAO.delete(persistedCurso);
		ruleContextDAO.commitAndCloseTransaction();
	}
	
}
