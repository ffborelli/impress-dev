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

import eu.com.impress.model.Schedule;
import eu.com.impress.pagination.ScheduleListWrapper;
import eu.com.impress.facade.ScheduleFacade;

@Path("schedule")
public class ScheduleResource {
	
	private ScheduleFacade scheduleFacade;
	
	public ScheduleFacade getFacade(){
		if(this.scheduleFacade == null)
			this.scheduleFacade = new ScheduleFacade();
		
		return this.scheduleFacade;
	}
	
	private ScheduleListWrapper findSchedule(ScheduleListWrapper wrapper) {

		wrapper.setTotalResults(this.getFacade().countSchedules());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getFacade().findSchedules(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private ScheduleListWrapper findScheduleSearch(ScheduleListWrapper wrapper, String search) {


		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getFacade().findSchedulesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleListWrapper listScheduleLogs(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		ScheduleListWrapper paginatedListWrapper = new ScheduleListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findSchedule(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Schedule> getAll(){
		List<Schedule> scheduleList = getFacade().listAll();
		return scheduleList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Schedule getById(@PathParam("id") String id){
		Schedule schedule = getFacade().find(Integer.parseInt(id));
		return schedule;
	}
	
	@POST
	@Consumes("application/json")
	public Schedule insert(Schedule schedule){
		
		Schedule objectToSave = new Schedule();
		
		if(schedule.getId() == null){
			objectToSave.setDescription(schedule.getDescription());
			objectToSave.setInitialDate(schedule.getInitialDate());
			objectToSave.setFinalDate(schedule.getFinalDate());
			objectToSave.setPriorityTime(schedule.getPriorityTime());
			objectToSave.setRecurrence(schedule.getRecurrence());
			objectToSave.setStatus(schedule.getStatus());
			objectToSave.setPlace(schedule.getPlace());
			objectToSave.setRule(schedule.getRule());
			getFacade().create(objectToSave);
		}else{
			objectToSave.setId(schedule.getId());
			objectToSave.setDescription(schedule.getDescription());
			objectToSave.setInitialDate(schedule.getInitialDate());
			objectToSave.setFinalDate(schedule.getFinalDate());
			objectToSave.setPriorityTime(schedule.getPriorityTime());
			objectToSave.setRecurrence(schedule.getRecurrence());
			objectToSave.setStatus(schedule.getStatus());
			objectToSave.setPlace(schedule.getPlace());
			objectToSave.setRule(schedule.getRule());
			getFacade().update(objectToSave);
		}
		
		return objectToSave;
		
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(Schedule s, @PathParam("id")String id){
		Schedule schedule = getFacade().find(Integer.parseInt(id));
		schedule.setDescription(s.getDescription());
		schedule.setInitialDate(s.getInitialDate());
		schedule.setFinalDate(s.getFinalDate());
		schedule.setPriorityTime(s.getPriorityTime());
		schedule.setRecurrence(s.getRecurrence());
		schedule.setStatus(s.getStatus());
		schedule.setPlace(s.getPlace());
		schedule.setRule(s.getRule());
		getFacade().update(schedule);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Schedule schedule = getFacade().find(Integer.parseInt(id));
		getFacade().delete(schedule);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleListWrapper getScheduleByValue(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		ScheduleListWrapper paginatedListWrapper = new ScheduleListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findScheduleSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
