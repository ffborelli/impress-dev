package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.RuleActionLogDAO;
import eu.com.impress.model.RuleActionLog;

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
	
	public List<RuleActionLog> findRuleActionLogs(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		ruleActionLogDAO.beginTransaction();
		List<RuleActionLog> listRuleActionLog = ruleActionLogDAO.findRuleActionLogs(startPosition, maxResults, sortField, sortDirections);
		ruleActionLogDAO.closeTransaction();
		
		return listRuleActionLog;
	}
	
	public List<RuleActionLog> findRuleActionLogsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		ruleActionLogDAO.beginTransaction();
		List<RuleActionLog> listRuleActionLog = ruleActionLogDAO.findRuleActionLogsSearch(startPosition, maxResults, sortField, sortDirections, search);
		ruleActionLogDAO.closeTransaction();
		
		return listRuleActionLog;
	}
	
	public Integer countRuleActionLogs() {
		
		ruleActionLogDAO.beginTransaction();
		Integer ruleActionLogs = ruleActionLogDAO.countRuleActionLogs();
		ruleActionLogDAO.closeTransaction();
		
		return ruleActionLogs;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		ruleActionLogDAO.beginTransaction();
		Integer ruleActionLogs = ruleActionLogDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		ruleActionLogDAO.closeTransaction();
		
		return ruleActionLogs;
	}
	
}
