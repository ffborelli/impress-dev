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

import eu.com.impress.model.ResourceLog;
import eu.com.impress.pagination.ResourceLogListWrapper;
import eu.com.impress.facade.ResourceLogFacade;

@Path("resourcelog")
public class ResourceLogResource {
	
	private ResourceLogFacade resourceLogFacade;
	
	public ResourceLogFacade getResourceLogFacade(){
		//if(this.resourceLogFacade == null)
			this.resourceLogFacade = new ResourceLogFacade();
		
		return this.resourceLogFacade; 
	}
	
	private ResourceLogListWrapper findResourceLog(ResourceLogListWrapper wrapper) {

		wrapper.setTotalResults(this.getResourceLogFacade().countResourceLogs());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getResourceLogFacade().findResourceLogs(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ResourceLogListWrapper findResourceLogSearch(ResourceLogListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getResourceLogFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getResourceLogFacade().findResourceLogsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceLogListWrapper listResourceTypes(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ResourceLogListWrapper paginatedListWrapper = new ResourceLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResourceLog(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<ResourceLog> getAll(){
		List<ResourceLog> resourceLogList = getResourceLogFacade().listAll();
		return resourceLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceLog getById(@PathParam("id") String id){
		ResourceLog resourceLog = getResourceLogFacade().find(Integer.parseInt(id));
		return resourceLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ResourceLog resourceLog){
		getResourceLogFacade().create(resourceLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ResourceLog r, @PathParam("id")String id){
		ResourceLog resourceLog = getResourceLogFacade().find(Integer.parseInt(id));
		resourceLog.setCreationDate(r.getCreationDate());
		resourceLog.setResourceLogValue(r.getResourceLogValue());
		resourceLog.setResource(r.getResource());
		getResourceLogFacade().update(resourceLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ResourceLog resourceLog = getResourceLogFacade().find(Integer.parseInt(id));
		getResourceLogFacade().delete(resourceLog);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceLogListWrapper getResourceLogByValue(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ResourceLogListWrapper paginatedListWrapper = new ResourceLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResourceLogSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
