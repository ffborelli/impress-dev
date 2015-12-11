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

import eu.com.impress.model.FusionRule;
import eu.com.impress.facade.FusionRuleFacade;

@Path("fusionrule")
public class FusionRuleResource {
	
	private FusionRuleFacade fusionRuleFacade;
	
	public FusionRuleFacade getFacade(){
		if(this.fusionRuleFacade == null)
			this.fusionRuleFacade = new FusionRuleFacade();
		
		return this.fusionRuleFacade; 
	}
	
	@GET
	@Produces("application/json")
	public List<FusionRule> getAll(){
		List<FusionRule> fusionRuleList = getFacade().listAll();
		return fusionRuleList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public FusionRule getById(@PathParam("id") String id){
		FusionRule fusionRule = getFacade().find(Integer.parseInt(id));
		return fusionRule;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(FusionRule fusionRule){
		getFacade().create(fusionRule);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(FusionRule f, @PathParam("id")String id){
		FusionRule fusionRule = getFacade().find(Integer.parseInt(id));
		fusionRule.setFusion(f.getFusion());
		fusionRule.setRule(f.getRule());
		getFacade().update(fusionRule);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		FusionRule fusionRule = getFacade().find(Integer.parseInt(id));
		getFacade().delete(fusionRule);
	}
	
}
