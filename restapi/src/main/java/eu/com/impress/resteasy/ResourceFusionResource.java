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

import eu.com.impress.model.ResourceFusion;
import eu.com.impress.facade.ResourceFusionFacade;

@Path("resourcefusion")
public class ResourceFusionResource {
	
	private ResourceFusionFacade resourceFusionFacade;
	
	public ResourceFusionFacade getFacade(){
		if(this.resourceFusionFacade == null)
			this.resourceFusionFacade = new ResourceFusionFacade();
		
		return this.resourceFusionFacade; 
	}
	
	@GET
	@Produces("application/json")
	public List<ResourceFusion> getAll(){
		List<ResourceFusion> resourceFusionList = getFacade().listAll();
		return resourceFusionList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceFusion getById(@PathParam("id") String id){
		ResourceFusion resourceFusion = getFacade().find(Integer.parseInt(id));
		return resourceFusion;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ResourceFusion resourceFusion){
		getFacade().create(resourceFusion);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ResourceFusion r, @PathParam("id")String id){
		ResourceFusion resourceFusion = getFacade().find(Integer.parseInt(id));
		resourceFusion.setFusion(r.getFusion());
		resourceFusion.setResource(r.getResource());
		getFacade().update(resourceFusion);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		ResourceFusion resourceFusion = getFacade().find(Integer.parseInt(id));
		getFacade().delete(resourceFusion);
	}
	
}
