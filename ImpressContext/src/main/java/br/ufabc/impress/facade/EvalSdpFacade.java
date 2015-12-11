package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.ufabc.impress.dao.EvalSdpDAO;
import br.ufabc.impress.model.EvalSdp;

public class EvalSdpFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EvalSdpDAO evalSdpDAO = new EvalSdpDAO();

	public void create(EvalSdp obj) {
		evalSdpDAO.beginTransaction();
		evalSdpDAO.save(obj);
		evalSdpDAO.commitAndCloseTransaction();
	}

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

	public List<EvalSdp> listAll() {
		evalSdpDAO.beginTransaction();
		List<EvalSdp> result = evalSdpDAO.findAll();
		evalSdpDAO.commitAndCloseTransaction();
		return result;
	}

	public void delete(EvalSdp obj) {
		evalSdpDAO.beginTransaction();
		EvalSdp persistedCurso = evalSdpDAO.findReferenceOnly(obj.getId());
		evalSdpDAO.delete(persistedCurso);
		evalSdpDAO.commitAndCloseTransaction();
	}
	
	public List<EvalSdp> listExcelSearch(int id, String module, Date start, Date finish, Date duration,String decription, String name) {
		evalSdpDAO.beginTransaction();
		List<EvalSdp> result = evalSdpDAO.listExcelSearch(id, module, start, finish, duration, decription, name);
		evalSdpDAO.commitAndCloseTransaction();
		return result;
	}
	
}
