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

import eu.com.impress.model.FusionLog;
import eu.com.impress.pagination.FusionLogListWrapper;
import eu.com.impress.facade.FusionLogFacade;

@Path("fusionlog")
public class FusionLogResource {
	
	private FusionLogFacade fusionLogFacade;
	
	public FusionLogFacade getFacade(){
		if(this.fusionLogFacade == null)
			this.fusionLogFacade = new FusionLogFacade();
		
		return this.fusionLogFacade; 
	}
	
	private FusionLogListWrapper findFusionLog(FusionLogListWrapper wrapper) {

		wrapper.setTotalResults(this.getFacade().countFusionLogs());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getFacade().findFusionLogs(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private FusionLogListWrapper findFusionLogSearch(FusionLogListWrapper wrapper, String search) {


		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getFacade().findFusionLogsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public FusionLogListWrapper listFusionLogs(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		FusionLogListWrapper paginatedListWrapper = new FusionLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findFusionLog(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<FusionLog> getAll(){
		List<FusionLog> fusionLogList = getFacade().listAll();
		return fusionLogList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public FusionLog getById(@PathParam("id") String id){
		FusionLog fusionLog = getFacade().find(Integer.parseInt(id));
		return fusionLog;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(FusionLog fusionLog){
		getFacade().create(fusionLog);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(FusionLog f, @PathParam("id")String id){
		FusionLog fusionLog = getFacade().find(Integer.parseInt(id));
		fusionLog.setCreationDate(f.getCreationDate());
		fusionLog.setFusionLogValue(f.getFusionLogValue());
		fusionLog.setFusion(f.getFusion());
		getFacade().update(fusionLog);
	}
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		FusionLog fusionLog = getFacade().find(Integer.parseInt(id));
		getFacade().delete(fusionLog);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public FusionLogListWrapper getFusionLogByValue(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		FusionLogListWrapper paginatedListWrapper = new FusionLogListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findFusionLogSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
