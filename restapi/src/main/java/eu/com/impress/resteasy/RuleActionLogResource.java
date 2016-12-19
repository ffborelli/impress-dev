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

import eu.com.impress.model.RuleActionLog;
import eu.com.impress.pagination.RuleActionLogListWrapper;
import eu.com.impress.facade.RuleActionLogFacade;

@Path("ruleactionlog")
public class RuleActionLogResource {
	
	private RuleActionLogFacade ruleActionLogFacade;
	
	public RuleActionLogFacade getFacade(){
		if(this.ruleActionLogFacade == null)
			this.ruleActionLogFacade = new RuleActionLogFacade();
		
		return this.ruleActionLogFacade; 
	}
	
	private RuleActionLogListWrapper findRuleActionLog(RuleActionLogListWrapper wrapper) {

		wrapper.setTotalResults(this.getFacade().countRuleActionLogs());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getFacade().findRuleActionLogs(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private RuleActionLogListWrapper findRuleActionLogSearch(RuleActionLogListWrapper wrapper, String search) {


		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getFacade().findRuleActionLogsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RuleActionLogListWrapper listRuleActionLogs(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		RuleActionLogListWrapper paginatedListWrapper = new RuleActionLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findRuleActionLog(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<RuleActionLog> getAll(){
		List<RuleActionLog> ruleActionLogList = getFacade().listAll();
		return ruleActionLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public RuleActionLog getById(@PathParam("id") String id){
		RuleActionLog ruleActionLog = getFacade().find(Integer.parseInt(id));
		return ruleActionLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(RuleActionLog ruleActionLog){
		getFacade().create(ruleActionLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(RuleActionLog r, @PathParam("id")String id){
		RuleActionLog ruleActionLog = getFacade().find(Integer.parseInt(id));
		ruleActionLog.setCreationDate(r.getCreationDate());
		ruleActionLog.setRule(r.getRule());
		ruleActionLog.setResource(r.getResource());
		ruleActionLog.setResourceActionType(r.getResourceActionType());
		getFacade().update(ruleActionLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		RuleActionLog ruleActionLog = getFacade().find(Integer.parseInt(id));
		getFacade().delete(ruleActionLog);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public RuleActionLogListWrapper getRuleActionLogByValue(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		RuleActionLogListWrapper paginatedListWrapper = new RuleActionLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findRuleActionLogSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
