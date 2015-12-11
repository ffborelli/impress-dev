package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.RuleDAO;
import eu.com.impress.model.Rule;

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
	
	public List<Rule> findRules(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		ruleDAO.beginTransaction();
		List<Rule> listRule = ruleDAO.findRules(startPosition, maxResults, sortField, sortDirections);
		ruleDAO.closeTransaction();
		
		return listRule;
	}
	
	public List<Rule> findRulesSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		ruleDAO.beginTransaction();
		List<Rule> listRule = ruleDAO.findRulesSearch(startPosition, maxResults, sortField, sortDirections, search);
		ruleDAO.closeTransaction();
		
		return listRule;
	}
	
	public Integer countPlaceTypes() {
		
		ruleDAO.beginTransaction();
		Integer places = ruleDAO.countRules();
		ruleDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		ruleDAO.beginTransaction();
		Integer resourceTypes = ruleDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		ruleDAO.closeTransaction();
		
		return resourceTypes;
	}
	
}
