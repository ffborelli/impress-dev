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

import eu.com.impress.model.ResourceType;
import eu.com.impress.pagination.ResourceTypeListWrapper;
import eu.com.impress.facade.ResourceTypeFacade;

@Path("resourcetype")
public class ResourceTypeResource {
	
	private ResourceTypeFacade resourceTypeFacade;
	
	public ResourceTypeFacade getResourceTypeFacade(){
		//if(this.resourceTypeFacade == null)
			this.resourceTypeFacade = new ResourceTypeFacade();
		
		return this.resourceTypeFacade; 
	}
	
	private ResourceTypeListWrapper findResourceType(ResourceTypeListWrapper wrapper) {

		wrapper.setTotalResults(this.getResourceTypeFacade().countPlaceTypes());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getResourceTypeFacade().findResourceTypes(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ResourceTypeListWrapper findResourceTypeSearch(ResourceTypeListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getResourceTypeFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getResourceTypeFacade().findResourceTypesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceTypeListWrapper listResourceTypes(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ResourceTypeListWrapper paginatedListWrapper = new ResourceTypeListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResourceType(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<ResourceType> getAll(){
		List<ResourceType> resourceTypeList = getResourceTypeFacade().listAll();
		return resourceTypeList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceType getById(@PathParam("id") String id){
		ResourceType resourceType = getResourceTypeFacade().find(Integer.parseInt(id));
		return resourceType;
	}
	
	@POST
	@Consumes("application/json")
	public ResourceType insert(ResourceType resourceType){
		//getPlaceTypeFacade().create(placeType);
		
		ResourceType objectToSave = new ResourceType();

		if (resourceType.getId() == null) {

			objectToSave.setDescription(resourceType.getDescription());
			objectToSave.setSensorActuator(resourceType.getSensorActuator());
			this.getResourceTypeFacade().create(objectToSave);
		} else {
			objectToSave.setId(resourceType.getId());
			objectToSave.setDescription(resourceType.getDescription());
			objectToSave.setSensorActuator(resourceType.getSensorActuator());
			this.getResourceTypeFacade().update(objectToSave);

		}

		return objectToSave;
	}
	
//	@PUT
//	@Path("{id}")
//	@Consumes("application/json")
//	public void update(ResourceType r, @PathParam("id")String id){
//		ResourceType resourceType = getFacade().find(Integer.parseInt(id));
//		resourceType.setDescription(r.getDescription());
//		resourceType.setSensorActuator(r.getSensorActuator());
//		getFacade().update(resourceType);
//	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ResourceType resourceType = getResourceTypeFacade().find(Integer.parseInt(id));
		getResourceTypeFacade().delete(resourceType);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceTypeListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ResourceTypeListWrapper paginatedListWrapper = new ResourceTypeListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResourceTypeSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
