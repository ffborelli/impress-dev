package br.ufabc.impress.facade;

import java.io.Serializable;
import java.util.List;

import br.ufabc.impress.dao.ResourceFusionDAO;
import br.ufabc.impress.model.ResourceFusion;

public class ResourceFusionFacade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private ResourceFusionDAO resourceFusionDAO = new ResourceFusionDAO();

	public void create(ResourceFusion obj) {
		resourceFusionDAO.beginTransaction();
		resourceFusionDAO.save(obj);
		resourceFusionDAO.commitAndCloseTransaction();
	}

	public void update(ResourceFusion obj) {
		resourceFusionDAO.beginTransaction();
		resourceFusionDAO.update(obj);
		resourceFusionDAO.commitAndCloseTransaction();
	}

	public ResourceFusion find(Integer id) {
		resourceFusionDAO.beginTransaction();
		ResourceFusion obj = resourceFusionDAO.find(id);
		resourceFusionDAO.closeTransaction();
		return obj;
	}

	public List<ResourceFusion> listAll() {
		resourceFusionDAO.beginTransaction();
		List<ResourceFusion> result = resourceFusionDAO.findAll();
		resourceFusionDAO.closeTransaction();
		return result;
	}

	public void delete(ResourceFusion obj) {
		resourceFusionDAO.beginTransaction();
		ResourceFusion persistedCurso = resourceFusionDAO.findReferenceOnly(obj.getId());
		resourceFusionDAO.delete(persistedCurso);
		resourceFusionDAO.commitAndCloseTransaction();
	}
	
}
