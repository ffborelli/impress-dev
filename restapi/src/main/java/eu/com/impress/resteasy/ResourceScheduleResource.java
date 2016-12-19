package eu.com.impress.resteasy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import eu.com.impress.model.ResourceSchedule;
import eu.com.impress.facade.ResourceScheduleFacade;

@Path("resourceschedule")
public class ResourceScheduleResource {
	
	private ResourceScheduleFacade resourceScheduleFacade;
	
	public ResourceScheduleFacade getResourceScheduleFacade(){
		if(this.resourceScheduleFacade == null)
			this.resourceScheduleFacade = new ResourceScheduleFacade();
		
		return this.resourceScheduleFacade;
	}
	
	@GET
	@Path("/")
	@Produces("application/json")
	public List<ResourceSchedule> getAll(){
		List<ResourceSchedule> resourceList = getResourceScheduleFacade().listAll();
		return resourceList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceSchedule getById(@PathParam("id") String id){
		ResourceSchedule resourceSchedule = getResourceScheduleFacade().find(Integer.parseInt(id));
		return resourceSchedule;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ResourceSchedule resourceSchedule){
		getResourceScheduleFacade().create(resourceSchedule);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ResourceSchedule r, @PathParam("id")String id){
		ResourceSchedule resourceSchedule = getResourceScheduleFacade().find(Integer.parseInt(id));
		resourceSchedule.setResource(r.getResource());
		resourceSchedule.setSchedule(r.getSchedule());
		getResourceScheduleFacade().update(resourceSchedule);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ResourceSchedule resourceSchedule = getResourceScheduleFacade().find(Integer.parseInt(id));
		getResourceScheduleFacade().delete(resourceSchedule);
	}
	
}
