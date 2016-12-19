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

import eu.com.impress.model.Resource;
import eu.com.impress.pagination.ResourceListWrapper;
import eu.com.impress.facade.ResourceFacade;

@Path("resource")
public class ResourceResource {
	
	private ResourceFacade resourceFacade;
	
	public ResourceFacade getResourceFacade(){
		//if(this.resourceFacade == null)
			this.resourceFacade = new ResourceFacade();
		
		return this.resourceFacade; 
	}
	
	private ResourceListWrapper findResource(ResourceListWrapper wrapper) {

		wrapper.setTotalResults(this.getResourceFacade().countResources());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getResourceFacade().findResources(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ResourceListWrapper findResourceSearch(ResourceListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getResourceFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getResourceFacade().findResourcesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResourceListWrapper listResourceTypes(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ResourceListWrapper paginatedListWrapper = new ResourceListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findResource(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Resource> getAll(){
		List<Resource> resourceList = getResourceFacade().listAll();
		return resourceList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Resource getById(@PathParam("id") String id){
		Resource resource = getResourceFacade().find(Integer.parseInt(id));
		return resource;
	}
	
	@POST
	@Consumes("application/json")
	public Resource insert(Resource resource){
		
		Resource objectToSave = new Resource();
		
		if(resource.getId() == null){
			objectToSave.setDescription(resource.getDescription());
			objectToSave.setDependentIndependent(resource.getDependentIndependent());
			objectToSave.setResourceType(resource.getResourceType());
			objectToSave.setPlace(resource.getPlace());
			getResourceFacade().create(objectToSave);
		}else{
			objectToSave.setId(resource.getId());
			objectToSave.setDescription(resource.getDescription());
			objectToSave.setDependentIndependent(resource.getDependentIndependent());
			objectToSave.setResourceType(resource.getResourceType());
			objectToSave.setPlace(resource.getPlace());
			getResourceFacade().update(objectToSave);
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(Resource r, @PathParam("id")String id){
		Resource resource = getResourceFacade().find(Integer.parseInt(id));
		resource.setDescription(r.getDescription());
		resource.setDependentIndependent(r.getDependentIndependent());
		resource.setResourceType(r.getResourceType());
		resource.setPlace(r.getPlace());
		getResourceFacade().update(resource);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Resource resource = getResourceFacade().find(Integer.parseInt(id));
		getResourceFacade().delete(resource);
	}
	
	@GET
	@Path("/sensors")
	@Produces("application/json")
	public List<Resource> getAllSensors(){
		List<Resource> resources = getResourceFacade().getAllSensorsOrActuators(0);
		return resources;
	}
	
	@GET
	@Path("/actuators")
	@Produces("application/json")
	public List<Resource> getAllActuators(){
		List<Resource> resources = getResourceFacade().getAllSensorsOrActuators(1);
		return resources;
	}
	
}
