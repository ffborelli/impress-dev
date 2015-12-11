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

import eu.com.impress.model.ContextType;
import eu.com.impress.pagination.ContextTypeListWrapper;
import eu.com.impress.facade.ContextTypeFacade;

@Path("contexttype")
public class ContextTypeResource {
	
	private ContextTypeFacade contextTypeFacade;
	
	public ContextTypeFacade getContextTypeFacade(){
		if(this.contextTypeFacade == null)
			this.contextTypeFacade = new ContextTypeFacade();
		
		return this.contextTypeFacade; 
	}
	
	private ContextTypeListWrapper findContextType(ContextTypeListWrapper  wrapper) {

		wrapper.setTotalResults(this.getContextTypeFacade().countPlaceTypes());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getContextTypeFacade().findContextTypes(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ContextTypeListWrapper findContextTypeSearch(ContextTypeListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getContextTypeFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getContextTypeFacade().findContextTypesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContextTypeListWrapper listContextTypes(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ContextTypeListWrapper paginatedListWrapper = new ContextTypeListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContextType(paginatedListWrapper);
	}
	
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<ContextType> getAll(){
		List<ContextType> contextTypeList = getContextTypeFacade().listAll();
		return contextTypeList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ContextType getById(@PathParam("id") String id){
		ContextType contextType = getContextTypeFacade().find(Integer.parseInt(id));
		return contextType;
	}
	
	@POST
	@Consumes("application/json")
	public ContextType insert(ContextType contextType){
		
		ContextType objectToSave = new ContextType();

		if (contextType.getId() == null) {

			objectToSave.setDescription(contextType.getDescription());
			this.getContextTypeFacade().create(objectToSave);
		} else {
			objectToSave.setId(contextType.getId());
			objectToSave.setDescription(contextType.getDescription());
			this.getContextTypeFacade().update(objectToSave);
		}

		return objectToSave;
	}
	
	/*@POST
	@Consumes("application/json")
	public void insert(ContextType contextType){
		getContextTypeFacade().create(contextType);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ContextType c, @PathParam("id")String id){
		ContextType contextType = getContextTypeFacade().find(Integer.parseInt(id));
		contextType.setDescription(c.getDescription());
		getContextTypeFacade().update(contextType);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ContextType contextType = getContextTypeFacade().find(Integer.parseInt(id));
		getContextTypeFacade().delete(contextType);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContextTypeListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ContextTypeListWrapper paginatedListWrapper = new ContextTypeListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContextTypeSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
