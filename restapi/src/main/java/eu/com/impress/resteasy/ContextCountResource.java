package eu.com.impress.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.com.impress.model.ContextCount;
import eu.com.impress.pagination.ContextCountListWrapper;
import eu.com.impress.facade.ContextCountFacade;

@Path("contextcount")
public class ContextCountResource {
	
	private ContextCountFacade contextCountFacade;
	
	public ContextCountFacade getContextCountFacade(){
		//if(this.contextCountFacade == null)
			this.contextCountFacade = new ContextCountFacade();
		
		return this.contextCountFacade; 
	}
	
	private ContextCountListWrapper findContextCounts(ContextCountListWrapper wrapper) {

		wrapper.setTotalResults(this.getContextCountFacade().countContextCounts());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getContextCountFacade().findContextCounts(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
//	private ContextCountListWrapper findContextCountsSearch(ContextCountListWrapper wrapper, String search) {
//
//
//		//wrapper.setTotalResults(1);
//		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
//		
//		wrapper.setTotalResults(this.getContextCountFacade().getRowCountSearch(start,
//				wrapper.getPageSize(), wrapper.getSortFields(),
//				wrapper.getSortDirections(), search) );
//		
//		wrapper.setList(this.getContextCountFacade().findContextCountsSearch(start,
//				wrapper.getPageSize(), wrapper.getSortFields(),
//				wrapper.getSortDirections(), search));
//		return wrapper;
//	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContextCountListWrapper list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ContextCountListWrapper paginatedListWrapper = new ContextCountListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContextCounts(paginatedListWrapper);
	}
		
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ContextCount getById(@PathParam("id") String id){
		ContextCount place = getContextCountFacade().find(Integer.parseInt(id));
		return place;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ContextCount contextCount){
		
		ContextCount objectToSave = new ContextCount();

		if (contextCount.getId() == null) {

			objectToSave.setContextCount(contextCount.getContextCount());
			//objectToSave.setContextRegistred(contextCount.getContextRegistred());
			objectToSave.setContextSequence(contextCount.getContextSequence());
			//objectToSave.setContextCount(contextCount.getContextCount());
			this.getContextCountFacade().create(objectToSave);
		} else {
			objectToSave.setId(contextCount.getId());
			objectToSave.setContextCount(contextCount.getContextCount());
			//objectToSave.setContextRegistred(0);
			objectToSave.setContextSequence(contextCount.getContextSequence());
			//objectToSave.setContextCount(contextCount.getContextCount());
			this.getContextCountFacade().update(objectToSave);

		}
	}
	
//	@PUT
//	@Path("{id}")
//	@Consumes("application/json")
//	public void update(Place p, @PathParam("id")String id){
//		Place place = getContextCountFacade().find(Integer.parseInt(id));
//		place.setDescription(p.getDescription());
//		place.setDependentIndependent(p.getDependentIndependent());
//		place.setPlaceType(p.getPlaceType());
//		place.setPlace(p.getPlace());
//		getContextCountFacade().update(place);
//	}
	
	@DELETE
	@Path("/{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ContextCount Place = getContextCountFacade().find(Integer.parseInt(id));
		getContextCountFacade().delete(Place);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ContextCount> getPlaces() {
		// StringBuilder result = new StringBuilder();

		List<ContextCount> listContextCount = this.getContextCountFacade().listAll();

		return listContextCount;
	}
	
//	@GET
//	@Path("/search/{d}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ContextCountListWrapper getContextCountByDescription(
//			@PathParam("d") String d,
//			@DefaultValue("1") @QueryParam("page") Integer page,
//			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
//			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
//		
//		ContextCountListWrapper paginatedListWrapper = new ContextCountListWrapper();
//		paginatedListWrapper.setCurrentPage(page);
//		paginatedListWrapper.setSortFields(sortFields);
//		paginatedListWrapper.setSortDirections(sortDirections);
//		paginatedListWrapper.setPageSize(10);
//		return findContextCountsSearch(paginatedListWrapper,d);
//
//		//List<ContextCount> listContextCount = this.getContextCountFacade().getByDescription(d);
//
//		//return listContextCount;
//	}
	
//	@GET
//	@Path("{id}")
//	@Produces("application/json")
//	public ContextCount search(@PathParam("id") String id){
//		ContextCount contextCount = getFacade().find(Integer.parseInt(id));
//		return contextCount;
//	}
	
//	@POST
//	@Consumes("application/json")
//	public void insert(ContextCount contextCount){
//		getFacade().create(contextCount);
//	}
//	
//	@PUT
//	@Path("{id}")
//	@Consumes("application/json")
//	public void update(ContextCount c, @PathParam("id")String id){
//		ContextCount contextCount = getFacade().find(Integer.parseInt(id));
//		contextCount.setContextCount(c.getContextCount());
//		contextCount.setContextRegistered(c.getContextRegistred());
//		contextCount.setContextSequence(c.getContextSequence());
//		getFacade().update(contextCount);
//	}
//	
//	@DELETE
//	@Path("{id}")
//	@Consumes("application/json")
//	public void delete(@PathParam("id") String id){
//		ContextCount contextCount = getFacade().find(Integer.parseInt(id));
//		getFacade().delete(contextCount);
//	}
	
}
