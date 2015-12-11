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

import eu.com.impress.model.ResourceActionType;
import eu.com.impress.pagination.ResourceActionTypeListWrapper;
import eu.com.impress.facade.ResourceActionTypeFacade;

@Path("resourceactiontype")
public class ResourceActionTypeResource {
	
	private ResourceActionTypeFacade resourceActionTypeFacade;
	
	public ResourceActionTypeFacade getResourceActionTypeFacade(){
		//if(this.resourceActionTypeFacade == null)
			this.resourceActionTypeFacade = new ResourceActionTypeFacade();
		
		return this.resourceActionTypeFacade; 
	}
	
	private ResourceActionTypeListWrapper findResourceActionType(ResourceActionTypeListWrapper wrapper) {

		wrapper.setTotalResults(this.getResourceActionTypeFacade().countResourceActionTypes());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getResourceActionTypeFacade().findResourceActionType(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ResourceActionTypeListWrapper findResourceActionTypeSearch(ResourceActionTypeListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getResourceActionTypeFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getResourceActionTypeFacade().findResourceActionTypeSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceActionTypeListWrapper list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ResourceActionTypeListWrapper paginatedListWrapper = new ResourceActionTypeListWrapper();
		
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResourceActionType(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<ResourceActionType> getAll(){
		List<ResourceActionType> resourceActionTypeList = getResourceActionTypeFacade().listAll();
		return resourceActionTypeList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceActionType getById(@PathParam("id") String id){
		ResourceActionType resourceActionType = getResourceActionTypeFacade().find(Integer.parseInt(id));
		return resourceActionType;
	}
	
	@POST
	@Consumes("application/json")
	public ResourceActionType insert(ResourceActionType resourceActionType){
		
		ResourceActionType objectToSave = new ResourceActionType();
		
		if(resourceActionType.getId() == null){
			objectToSave.setResourceActionTypeText(resourceActionType.getResourceActionTypeText());
			objectToSave.setResourceType(resourceActionType.getResourceType());
			getResourceActionTypeFacade().create(objectToSave);
		}else{
			objectToSave.setId(resourceActionType.getId());
			objectToSave.setResourceActionTypeText(resourceActionType.getResourceActionTypeText());
			objectToSave.setResourceType(resourceActionType.getResourceType());
			getResourceActionTypeFacade().update(objectToSave);
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ResourceActionType r, @PathParam("id")String id){
		ResourceActionType resourceActionType = getResourceActionTypeFacade().find(Integer.parseInt(id));
		resourceActionType.setResourceActionTypeText(r.getResourceActionTypeText());
		resourceActionType.setResourceType(r.getResourceType());
		getResourceActionTypeFacade().update(resourceActionType);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ResourceActionType resourceActionType = getResourceActionTypeFacade().find(Integer.parseInt(id));
		getResourceActionTypeFacade().delete(resourceActionType);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceActionTypeListWrapper getResourceActionByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ResourceActionTypeListWrapper paginatedListWrapper = new ResourceActionTypeListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResourceActionTypeSearch(paginatedListWrapper,d);

	}
	
}
