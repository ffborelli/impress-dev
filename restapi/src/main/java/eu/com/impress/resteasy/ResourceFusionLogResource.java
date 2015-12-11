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

import eu.com.impress.model.ResourceFusionLog;
import eu.com.impress.facade.ResourceFusionLogFacade;

@Path("resourcefusionlog")
public class ResourceFusionLogResource {
	
	private ResourceFusionLogFacade resourceFusionLogFacade;
	
	public ResourceFusionLogFacade getFacade(){
		if(this.resourceFusionLogFacade == null)
			this.resourceFusionLogFacade = new ResourceFusionLogFacade();
		
		return this.resourceFusionLogFacade; 
	}
	
	@GET
	@Produces("application/json")
	public List<ResourceFusionLog> getAll(){
		List<ResourceFusionLog> resourceFusionLogList = getFacade().listAll();
		return resourceFusionLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceFusionLog getById(@PathParam("id") String id){
		ResourceFusionLog resourceFusionLog = getFacade().find(Integer.parseInt(id));
		return resourceFusionLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ResourceFusionLog resourceFusionLog){
		getFacade().create(resourceFusionLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ResourceFusionLog r, @PathParam("id")String id){
		ResourceFusionLog resourceFusionLog = getFacade().find(Integer.parseInt(id));
		resourceFusionLog.setCreationDate(r.getCreationDate());
		resourceFusionLog.setFusionLog(r.getFusionLog());
		resourceFusionLog.setResourceLog(r.getResourceLog());
		getFacade().update(resourceFusionLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		ResourceFusionLog resourceFusionLog = getFacade().find(Integer.parseInt(id));
		getFacade().delete(resourceFusionLog);
	}
	
}
