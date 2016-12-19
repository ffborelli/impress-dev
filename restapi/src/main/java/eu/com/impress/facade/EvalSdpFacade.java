package eu.com.impress.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import eu.com.impress.dao.EvalSdpDAO;
import eu.com.impress.model.EvalSdp;
import eu.com.impress.model.EvalSdp;

public class EvalSdpFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EvalSdpDAO evalSdpDAO = new EvalSdpDAO();

	public void create(EvalSdp obj) {
		evalSdpDAO.beginTransaction();
		evalSdpDAO.save(obj);
		evalSdpDAO.commitAndCloseTransaction();
	}
	
//	public void create(EvalSdp obj, Long uid) {
//		evalSdpDAO.beginTransaction();
//		evalSdpDAO.save(obj);
//		evalSdpDAO.commitAndCloseTransaction();
//	}

	public void update(EvalSdp obj) {
		evalSdpDAO.beginTransaction();
		evalSdpDAO.update(obj);
		evalSdpDAO.commitAndCloseTransaction();
	}

	public EvalSdp find(Integer id) {
		evalSdpDAO.beginTransaction();
		EvalSdp obj = evalSdpDAO.find(id);
		evalSdpDAO.commitAndCloseTransaction();
		return obj;
	}
	
	public EvalSdp find(Long id) {
		evalSdpDAO.beginTransaction();
		EvalSdp obj = evalSdpDAO.find(id);
		evalSdpDAO.commitAndCloseTransaction();
		return obj;
	}

	public List<EvalSdp> listAll() {
		evalSdpDAO.beginTransaction();
		List<EvalSdp> result = evalSdpDAO.findAll();
		evalSdpDAO.commitAndCloseTransaction();
		return result;
	}

	public void delete(EvalSdp obj) {
		evalSdpDAO.beginTransaction();
		EvalSdp persistedCurso = evalSdpDAO.findReferenceOnly(obj.getUid());
		evalSdpDAO.delete(persistedCurso);
		evalSdpDAO.commitAndCloseTransaction();
	}
	public Long getMaxUid(){
		evalSdpDAO.beginTransaction();
		Long max = evalSdpDAO.getMaxUid();
		evalSdpDAO.commitAndCloseTransaction();
		return max;
	}
	
	public List<EvalSdp> getLastRegisters(int limit){
		evalSdpDAO.beginTransaction();
		List<EvalSdp> r = evalSdpDAO.getLastRegisters(limit);
		evalSdpDAO.commitAndCloseTransaction();
		return r;
	}
	
	public List<EvalSdp> listExcelSearch(int id, String module, Date start, Date finish, Date duration,String decription, String name) {
		evalSdpDAO.beginTransaction();
		List<EvalSdp> result = evalSdpDAO.listExcelSearch(id, module, start, finish, duration, decription, name);
		evalSdpDAO.commitAndCloseTransaction();
		return result;
	}
	
	public List<EvalSdp> findEvalSdp(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		evalSdpDAO.beginTransaction();
		List<EvalSdp> listEvalSdp = evalSdpDAO.findEvalSdps(startPosition, maxResults, sortField, sortDirections);
		evalSdpDAO.closeTransaction();
		
		return listEvalSdp;
	}
	
	public List<EvalSdp> findEvalSdpSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		evalSdpDAO.beginTransaction();
		List<EvalSdp> listEvalSdp = evalSdpDAO.findEvalSdpsSearch(startPosition, maxResults, sortField, sortDirections, search);
		evalSdpDAO.closeTransaction();
		
		return listEvalSdp;
	}
	
	public Integer countEvalSdps() {
		
		evalSdpDAO.beginTransaction();
		Integer evalSdp = evalSdpDAO.countEvalSdps();
		evalSdpDAO.closeTransaction();
		
		return evalSdp;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		evalSdpDAO.beginTransaction();
		Integer evalSdp = evalSdpDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		evalSdpDAO.closeTransaction();
		
		return evalSdp;
	}
	
}
