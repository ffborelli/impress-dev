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

import eu.com.impress.model.RuleContext;
import eu.com.impress.facade.RuleContextFacade;

@Path("rulecontext")
public class RuleContextResource {
	
	private RuleContextFacade ruleContextFacade;
	
	public RuleContextFacade getFacade(){
		if(this.ruleContextFacade == null)
			this.ruleContextFacade = new RuleContextFacade();
		
		return this.ruleContextFacade; 
	}
	
	@GET
	@Produces("application/json")
	public List<RuleContext> getAll(){
		List<RuleContext> ruleContextList = getFacade().listAll();
		return ruleContextList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public RuleContext getById(@PathParam("id") String id){
		RuleContext ruleContext = getFacade().find(Integer.parseInt(id));
		return ruleContext;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(RuleContext ruleContext){
		getFacade().create(ruleContext);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(RuleContext r, @PathParam("id")String id){
		RuleContext ruleContext = getFacade().find(Integer.parseInt(id));
		ruleContext.setRule(r.getRule());
		ruleContext.setContext(r.getContext());
		getFacade().update(ruleContext);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		RuleContext ruleContext = getFacade().find(Integer.parseInt(id));
		getFacade().delete(ruleContext);
	}
	
}
