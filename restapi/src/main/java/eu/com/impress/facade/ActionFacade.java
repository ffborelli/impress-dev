package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.ActionDAO;
import eu.com.impress.model.Action;
import eu.com.impress.model.Action;

public class ActionFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ActionDAO actionDAO = new ActionDAO();

	public void create(Action obj) {
		actionDAO.beginTransaction();
		actionDAO.save(obj);
		actionDAO.commitAndCloseTransaction();
	}

	public void update(Action obj) {
		actionDAO.beginTransaction();
		actionDAO.update(obj);
		actionDAO.commitAndCloseTransaction();
	}

	public Action find(Integer id) {
		actionDAO.beginTransaction();
		Action obj = actionDAO.find(id);
		actionDAO.closeTransaction();
		return obj;
	}

	public List<Action> listAll() {
		actionDAO.beginTransaction();
		List<Action> result = actionDAO.findAll();
		actionDAO.closeTransaction();
		return result;
	}

	public void delete(Action obj) {
		actionDAO.beginTransaction();
		Action persistedCurso = actionDAO.findReferenceOnly(obj.getId());
		actionDAO.delete(persistedCurso);
		actionDAO.commitAndCloseTransaction();
	}
	
	public List<Action> findAction(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		actionDAO.beginTransaction();
		List<Action> listAction = actionDAO.findActions(startPosition, maxResults, sortField, sortDirections);
		actionDAO.closeTransaction();
		
		return listAction;
	}
	
	public List<Action> findActionSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		actionDAO.beginTransaction();
		List<Action> listAction = actionDAO.findActionsSearch(startPosition, maxResults, sortField, sortDirections, search);
		actionDAO.closeTransaction();
		
		return listAction;
	}
	
	public Integer countActions() {
		
		actionDAO.beginTransaction();
		Integer evalSdp = actionDAO.countActions();
		actionDAO.closeTransaction();
		
		return evalSdp;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		actionDAO.beginTransaction();
		Integer evalSdp = actionDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		actionDAO.closeTransaction();
		
		return evalSdp;
	}
	
}
