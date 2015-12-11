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

import eu.com.impress.model.FusionRuleLog;
import eu.com.impress.facade.FusionRuleLogFacade;

@Path("fusionrulelog")
public class FusionRuleLogResource {
	
	private FusionRuleLogFacade fusionRuleLogFacade;
	
	public FusionRuleLogFacade getFacade(){
		if(this.fusionRuleLogFacade == null)
			this.fusionRuleLogFacade = new FusionRuleLogFacade();
		
		return this.fusionRuleLogFacade; 
	}
	
	@GET
	@Produces("application/json")
	public List<FusionRuleLog> getAll(){
		List<FusionRuleLog> fusionRuleLogList = getFacade().listAll();
		return fusionRuleLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public FusionRuleLog getById(@PathParam("id") String id){
		FusionRuleLog fusionRuleLog = getFacade().find(Integer.parseInt(id));
		return fusionRuleLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(FusionRuleLog fusionRuleLog){
		getFacade().create(fusionRuleLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(FusionRuleLog f, @PathParam("id")String id){
		FusionRuleLog fusionRuleLog = getFacade().find(Integer.parseInt(id));
		fusionRuleLog.setCreationDate(f.getCreationDate());
		fusionRuleLog.setFusionLog(f.getFusionLog());
		fusionRuleLog.setRuleActionLog(f.getRuleActionLog());
		getFacade().update(fusionRuleLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		FusionRuleLog fusionRuleLog = getFacade().find(Integer.parseInt(id));
		getFacade().delete(fusionRuleLog);
	}
	
}
