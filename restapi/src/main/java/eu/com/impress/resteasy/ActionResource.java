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

import eu.com.impress.model.Action;
import eu.com.impress.pagination.ActionListWrapper;
import eu.com.impress.facade.ActionFacade;

@Path("action")
public class ActionResource {
	
private ActionFacade actionFacade;
	
	public ActionFacade getActionFacade(){
		//if(this.actionFacade == null)
			this.actionFacade = new ActionFacade();
		
		return this.actionFacade; 
	}
	
	private ActionListWrapper findAction(ActionListWrapper wrapper) {

		wrapper.setTotalResults(this.getActionFacade().countActions());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getActionFacade().findAction(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ActionListWrapper findActionSearch(ActionListWrapper wrapper, String search) {
		

		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getActionFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getActionFacade().findActionSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ActionListWrapper list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ActionListWrapper paginatedListWrapper = new ActionListWrapper();
		
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findAction(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Action> getAll(){
		List<Action> actionList = getActionFacade().listAll();
		return actionList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Action getById(@PathParam("id") String id){
		Action action = getActionFacade().find(Integer.parseInt(id));
		return action;
	}
	
	@POST
	@Consumes("application/json")
	public Action insert(Action action){
		
		Action objectToSave = new Action();
		
		if(action.getId() == null){
			objectToSave.setResourceActionType(action.getResourceActionType());
			objectToSave.setResource(action.getResource());
			objectToSave.setRule(action.getRule());
			getActionFacade().create(objectToSave);
		}else{
			objectToSave.setId(action.getId());
			objectToSave.setResourceActionType(action.getResourceActionType());
			objectToSave.setResource(action.getResource());
			objectToSave.setRule(action.getRule());
			getActionFacade().update(objectToSave);
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(Action a, @PathParam("id")String id){
		Action action = getActionFacade().find(Integer.parseInt(id));
		action.setResource(a.getResource());
		//action.setAction(a.getAction());
		action.setRule(a.getRule());
		getActionFacade().update(action);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Action action = getActionFacade().find(Integer.parseInt(id));
		getActionFacade().delete(action);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActionListWrapper getResourceActionByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ActionListWrapper paginatedListWrapper = new ActionListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findActionSearch(paginatedListWrapper,d);

	}
	
}
