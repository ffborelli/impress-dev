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

import eu.com.impress.model.FusionContext;
import eu.com.impress.facade.FusionContextFacade;

@Path("fusioncontext")
public class FusionContextResource {
	
	private FusionContextFacade fusionContextFacade;
	
	public FusionContextFacade getFacade(){
		if(this.fusionContextFacade == null)
			this.fusionContextFacade = new FusionContextFacade();
		
		return this.fusionContextFacade; 
	}
	
	@GET
	@Produces("application/json")
	public List<FusionContext> getAll(){
		List<FusionContext> fusionContextList = getFacade().listAll();
		return fusionContextList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public FusionContext getById(@PathParam("id") String id){
		FusionContext fusionContext = getFacade().find(Integer.parseInt(id));
		return fusionContext;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(FusionContext fusionContext){
		getFacade().create(fusionContext);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(FusionContext f, @PathParam("id")String id){
		FusionContext fusionContext = getFacade().find(Integer.parseInt(id));
		fusionContext.setFusion(f.getFusion());
		fusionContext.setContext(f.getContext());
		getFacade().update(fusionContext);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		FusionContext fusionContext = getFacade().find(Integer.parseInt(id));
		getFacade().delete(fusionContext);
	}
	
}
