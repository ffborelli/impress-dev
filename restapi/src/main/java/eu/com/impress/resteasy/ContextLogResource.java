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

import eu.com.impress.model.ContextLog;
import eu.com.impress.pagination.ContextLogListWrapper;
import eu.com.impress.facade.ContextLogFacade;

@Path("contextlog")
public class ContextLogResource {
	
	private ContextLogFacade contextLogFacade;
	
	public ContextLogFacade getFacade(){
		if(this.contextLogFacade == null)
			this.contextLogFacade = new ContextLogFacade();
		
		return this.contextLogFacade; 
	}
	
	private ContextLogListWrapper findContextLog(ContextLogListWrapper wrapper) {

		wrapper.setTotalResults(this.getFacade().countContextLogs());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getFacade().findContextLogs(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ContextLogListWrapper findContextLogSearch(ContextLogListWrapper wrapper, String search) {


		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getFacade().findContextLogsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ContextLogListWrapper listContextLogs(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ContextLogListWrapper paginatedListWrapper = new ContextLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContextLog(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<ContextLog> getAll(){
		List<ContextLog> contextLogList = getFacade().listAll();
		return contextLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ContextLog getById(@PathParam("id") String id){
		ContextLog contextLog = getFacade().find(Integer.parseInt(id));
		return contextLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ContextLog contextLog){
		getFacade().create(contextLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ContextLog c, @PathParam("id")String id){
		ContextLog contextLog = getFacade().find(Integer.parseInt(id));
		contextLog.setCreationDate(c.getCreationDate());
		contextLog.setContext(c.getContext());
		getFacade().update(contextLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ContextLog contextLog = getFacade().find(Integer.parseInt(id));
		getFacade().delete(contextLog);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ContextLogListWrapper getContextLogByValue(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ContextLogListWrapper paginatedListWrapper = new ContextLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findContextLogSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
