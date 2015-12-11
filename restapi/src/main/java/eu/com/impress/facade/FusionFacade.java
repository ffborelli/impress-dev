package eu.com.impress.facade;

import java.io.Serializable;
import java.util.List;

import eu.com.impress.dao.FusionDAO;
import eu.com.impress.model.Fusion;
import eu.com.impress.model.ResourceType;

public class FusionFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private FusionDAO fusionDAO = new FusionDAO();

	public void create(Fusion obj) {
		fusionDAO.beginTransaction();
		fusionDAO.save(obj);
		fusionDAO.commitAndCloseTransaction();
	}

	public void update(Fusion obj) {
		fusionDAO.beginTransaction();
		fusionDAO.update(obj);
		fusionDAO.commitAndCloseTransaction();
	}

	public Fusion find(Integer id) {
		fusionDAO.beginTransaction();
		Fusion obj = fusionDAO.find(id);
		fusionDAO.closeTransaction();
		return obj;
	}

	public List<Fusion> listAll() {
		fusionDAO.beginTransaction();
		List<Fusion> result = fusionDAO.findAll();
		fusionDAO.closeTransaction();
		return result;
	}

	public void delete(Fusion obj) {
		fusionDAO.beginTransaction();
		Fusion persistedCurso = fusionDAO.findReferenceOnly(obj.getId());
		fusionDAO.delete(persistedCurso);
		fusionDAO.commitAndCloseTransaction();
	}
	
	public List<Fusion> findFusions(int startPosition, int maxResults,
			String sortField, String sortDirections){
		
		fusionDAO.beginTransaction();
		List<Fusion> listFusion = fusionDAO.findFusions(startPosition, maxResults, sortField, sortDirections);
		fusionDAO.closeTransaction();
		
		return listFusion;
	}
	
	public List<Fusion> findFusionsSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search){
		
		fusionDAO.beginTransaction();
		List<Fusion> listFusion = fusionDAO.findFusionsSearch(startPosition, maxResults, sortField, sortDirections, search);
		fusionDAO.closeTransaction();
		
		return listFusion;
	}
	
	public Integer countFusions() {
		
		fusionDAO.beginTransaction();
		Integer places = fusionDAO.countFusions();
		fusionDAO.closeTransaction();
		
		return places;
	}
	
	public Integer getRowCountSearch(int startPosition, int maxResults,
			String sortField, String sortDirections, String search) {
		
		fusionDAO.beginTransaction();
		Integer fusions = fusionDAO.getRowCountSearch(startPosition, maxResults,
				sortField, sortDirections, search);
		fusionDAO.closeTransaction();
		
		return fusions;
	}
	
	
}
