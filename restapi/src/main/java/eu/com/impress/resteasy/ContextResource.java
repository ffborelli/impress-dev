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

import eu.com.impress.model.Context;
import eu.com.impress.pagination.ContextListWrapper;
import eu.com.impress.facade.ContextFacade;

@Path("context")
public class ContextResource {
	
	private ContextFacade contextFacade;
	
	public ContextFacade getContextFacade(){
		//if(this.contextFacade == null)
			this.contextFacade = new ContextFacade();
		
		return this.contextFacade; 
	}
	
	private ContextListWrapper findContext(ContextListWrapper wrapper) {

		wrapper.setTotalResults(this.getContextFacade().countPlaceTypes());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getContextFacade().findContexts(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ContextListWrapper findContextSearch(ContextListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getContextFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getContextFacade().findContextsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContextListWrapper listContexts(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ContextListWrapper paginatedListWrapper = new ContextListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContext(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Context> getAll(){
		List<Context> contextList = getContextFacade().listAll();
		return contextList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Context getById(@PathParam("id") String id){
		Context context = getContextFacade().find(Integer.parseInt(id));
		return context;
	}
	
	@POST
	@Consumes("application/json")
	public Context insert(Context context){
		
		Context objectToSave = new Context();
		
		if(context.getId() == null){
			objectToSave.setContextName(context.getContextName());
			objectToSave.setEnableDisable(context.getEnableDisable());
			objectToSave.setContextType(context.getContextType());
			objectToSave.setContext(context.getContext());
			objectToSave.setPlace(context.getPlace());
			getContextFacade().create(objectToSave);
		}else{
			objectToSave.setId(context.getId());
			objectToSave.setContextName(context.getContextName());
			objectToSave.setEnableDisable(context.getEnableDisable());
			objectToSave.setContextType(context.getContextType());
			objectToSave.setContext(context.getContext());
			objectToSave.setPlace(context.getPlace());
			getContextFacade().update(objectToSave);
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(Context c, @PathParam("id")String id){
		Context context = getContextFacade().find(Integer.parseInt(id));
		context.setContextName(c.getContextName());
		context.setEnableDisable(c.getEnableDisable());
		context.setContextType(c.getContextType());
		context.setContext(c.getContext());
		getContextFacade().update(context);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Context context = getContextFacade().find(Integer.parseInt(id));
		getContextFacade().delete(context);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContextListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ContextListWrapper paginatedListWrapper = new ContextListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContextSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
