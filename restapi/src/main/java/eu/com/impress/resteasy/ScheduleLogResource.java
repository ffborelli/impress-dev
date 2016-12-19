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

import eu.com.impress.model.ScheduleLog;
import eu.com.impress.pagination.ScheduleLogListWrapper;
import eu.com.impress.facade.ScheduleLogFacade;

@Path("schedulelog")
public class ScheduleLogResource {
	
	private ScheduleLogFacade scheduleLogFacade;
	
	public ScheduleLogFacade getFacade(){
		if(this.scheduleLogFacade == null)
			this.scheduleLogFacade = new ScheduleLogFacade();
		
		return this.scheduleLogFacade;
	}
	
	private ScheduleLogListWrapper findScheduleLog(ScheduleLogListWrapper wrapper) {

		wrapper.setTotalResults(this.getFacade().countScheduleLogs());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getFacade().findScheduleLogs(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ScheduleLogListWrapper findScheduleLogSearch(ScheduleLogListWrapper wrapper, String search) {


		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getFacade().findScheduleLogsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleLogListWrapper listScheduleLogs(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ScheduleLogListWrapper paginatedListWrapper = new ScheduleLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findScheduleLog(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<ScheduleLog> getAll(){
		List<ScheduleLog> scheduleLogList = getFacade().listAll();
		return scheduleLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public ScheduleLog getById(@PathParam("id") String id){
		ScheduleLog scheduleLog = getFacade().find(Integer.parseInt(id));
		return scheduleLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(ScheduleLog scheduleLog){
		getFacade().create(scheduleLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ScheduleLog s, @PathParam("id")String id){
		ScheduleLog scheduleLog = getFacade().find(Integer.parseInt(id));
		scheduleLog.setCreationDate(s.getCreationDate());
		scheduleLog.setSchedule(s.getSchedule());
		getFacade().update(scheduleLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		ScheduleLog scheduleLog = getFacade().find(Integer.parseInt(id));
		getFacade().delete(scheduleLog);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleLogListWrapper getScheduleLogByValue(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ScheduleLogListWrapper paginatedListWrapper = new ScheduleLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findScheduleLogSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
