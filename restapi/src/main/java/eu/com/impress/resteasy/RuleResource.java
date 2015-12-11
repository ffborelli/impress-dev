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

import eu.com.impress.model.Rule;
import eu.com.impress.pagination.RuleListWrapper;
import eu.com.impress.facade.RuleFacade;

@Path("rule")
public class RuleResource {
	
	private RuleFacade ruleFacade;
	
	public RuleFacade getRuleFacade(){
		//if(this.ruleFacade == null)
			this.ruleFacade = new RuleFacade();
		
		return this.ruleFacade; 
	}
	
	private RuleListWrapper findRule(RuleListWrapper wrapper) {

		wrapper.setTotalResults(this.getRuleFacade().countPlaceTypes());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getRuleFacade().findRules(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private RuleListWrapper findRuleSearch(RuleListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getRuleFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getRuleFacade().findRulesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RuleListWrapper listResourceTypes(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		RuleListWrapper paginatedListWrapper = new RuleListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findRule(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Rule> getAll(){
		List<Rule> ruleList = getRuleFacade().listAll();
		return ruleList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Rule getById(@PathParam("id") String id){
		Rule rule = getRuleFacade().find(Integer.parseInt(id));
		return rule;
	}
	
	@POST
	@Consumes("application/json")
	public Rule insert(Rule rule){
		
		Rule objectToSave = new Rule();
		
		if(rule.getId() == null){
			
			objectToSave.setRuleName(rule.getRuleName());
			objectToSave.setRuleText(rule.getRuleText());
			objectToSave.setDependentIndependent(rule.getDependentIndependent());
			this.getRuleFacade().create(objectToSave);
			
		}else{
			
			objectToSave.setId(rule.getId());
			objectToSave.setRuleName(rule.getRuleName());
			objectToSave.setRuleText(rule.getRuleText());
			objectToSave.setDependentIndependent(rule.getDependentIndependent());
			this.getRuleFacade().update(objectToSave);
			
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(Rule r, @PathParam("id")String id){
		Rule rule = getRuleFacade().find(Integer.parseInt(id));
		rule.setRuleName(r.getRuleName());
		rule.setRuleText(r.getRuleText());
		rule.setDependentIndependent(r.getDependentIndependent());
		getRuleFacade().update(rule);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Rule rule = getRuleFacade().find(Integer.parseInt(id));
		getRuleFacade().delete(rule);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public RuleListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		RuleListWrapper paginatedListWrapper = new RuleListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findRuleSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
