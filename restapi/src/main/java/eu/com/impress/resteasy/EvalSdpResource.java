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

import eu.com.impress.model.EvalSdp;
import eu.com.impress.pagination.EvalSdpListWrapper;
import eu.com.impress.pagination.EvalSdpListWrapper;
import eu.com.impress.facade.EvalSdpFacade;

@Path("evalsdp")
public class EvalSdpResource {
	
private EvalSdpFacade evalSdpFacade;
	
	public EvalSdpFacade getEvalSdpFacade(){
		//if(this.EvalSdpFacade == null)
			this.evalSdpFacade = new EvalSdpFacade();
		
		return this.evalSdpFacade; 
	}
	
	private EvalSdpListWrapper findEvalSdp(EvalSdpListWrapper wrapper) {

		wrapper.setTotalResults(this.getEvalSdpFacade().countEvalSdps());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getEvalSdpFacade().findEvalSdp(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private EvalSdpListWrapper findEvalSdpSearch(EvalSdpListWrapper wrapper, String search) {
		

		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getEvalSdpFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getEvalSdpFacade().findEvalSdpSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public EvalSdpListWrapper list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		EvalSdpListWrapper paginatedListWrapper = new EvalSdpListWrapper();
		
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findEvalSdp(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<EvalSdp> getAll(){
		List<EvalSdp> actionList = getEvalSdpFacade().listAll();
		return actionList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public EvalSdp getById(@PathParam("id") String id){
		EvalSdp action = getEvalSdpFacade().find(Integer.parseInt(id));
		return action;
	}
	
	@POST
	@Consumes("application/json")
	public EvalSdp insert(EvalSdp evalSdp){
		
		EvalSdp objectToSave = new EvalSdp();
		
		if(evalSdp.getUid() == null){
			
			objectToSave.setP1(evalSdp.getP1());
			objectToSave.setP2(evalSdp.getP2());
			objectToSave.setP3(evalSdp.getP3());
			objectToSave.setP4(evalSdp.getP4());
			objectToSave.setP5(evalSdp.getP5());
			objectToSave.setP6(evalSdp.getP6());

			objectToSave.setP1DiskRead(evalSdp.getP1DiskRead());
			objectToSave.setP2DiskRead(evalSdp.getP2DiskRead());
			objectToSave.setP3DiskRead(evalSdp.getP3DiskRead());
			objectToSave.setP4DiskRead(evalSdp.getP4DiskRead());
			objectToSave.setP5DiskRead(evalSdp.getP5DiskRead());
			objectToSave.setP6DiskRead(evalSdp.getP6DiskRead());
			
			objectToSave.setP1DiskWrite(evalSdp.getP1DiskWrite());
			objectToSave.setP2DiskWrite(evalSdp.getP2DiskWrite());
			objectToSave.setP3DiskWrite(evalSdp.getP3DiskWrite());
			objectToSave.setP4DiskWrite(evalSdp.getP4DiskWrite());
			objectToSave.setP5DiskWrite(evalSdp.getP5DiskWrite());
			objectToSave.setP6DiskWrite(evalSdp.getP6DiskWrite());
			
			objectToSave.setP1MemTotal(evalSdp.getP1MemTotal());
			objectToSave.setP2MemTotal(evalSdp.getP2MemTotal());
			objectToSave.setP3MemTotal(evalSdp.getP3MemTotal());
			objectToSave.setP4MemTotal(evalSdp.getP4MemTotal());
			objectToSave.setP5MemTotal(evalSdp.getP5MemTotal());
			objectToSave.setP6MemTotal(evalSdp.getP6MemTotal());
			
			objectToSave.setP1MemUsage(evalSdp.getP1MemUsage());
			objectToSave.setP2MemUsage(evalSdp.getP2MemUsage());
			objectToSave.setP3MemUsage(evalSdp.getP3MemUsage());
			objectToSave.setP4MemUsage(evalSdp.getP4MemUsage());
			objectToSave.setP5MemUsage(evalSdp.getP5MemUsage());
			objectToSave.setP6MemUsage(evalSdp.getP6MemUsage());
			
			objectToSave.setP1ProcUsage(evalSdp.getP1ProcUsage());
			objectToSave.setP2ProcUsage(evalSdp.getP2ProcUsage());
			objectToSave.setP3ProcUsage(evalSdp.getP3ProcUsage());
			objectToSave.setP4ProcUsage(evalSdp.getP4ProcUsage());
			objectToSave.setP5ProcUsage(evalSdp.getP5ProcUsage());
			objectToSave.setP6ProcUsage(evalSdp.getP6ProcUsage());
			
			getEvalSdpFacade().create(objectToSave);
		}else{
			
			objectToSave.setUid(evalSdp.getUid());

			objectToSave.setP1(evalSdp.getP1());
			objectToSave.setP2(evalSdp.getP2());
			objectToSave.setP3(evalSdp.getP3());
			objectToSave.setP4(evalSdp.getP4());
			objectToSave.setP5(evalSdp.getP5());
			objectToSave.setP6(evalSdp.getP6());

			objectToSave.setP1DiskRead(evalSdp.getP1DiskRead());
			objectToSave.setP2DiskRead(evalSdp.getP2DiskRead());
			objectToSave.setP3DiskRead(evalSdp.getP3DiskRead());
			objectToSave.setP4DiskRead(evalSdp.getP4DiskRead());
			objectToSave.setP5DiskRead(evalSdp.getP5DiskRead());
			objectToSave.setP6DiskRead(evalSdp.getP6DiskRead());
			
			objectToSave.setP1DiskWrite(evalSdp.getP1DiskWrite());
			objectToSave.setP2DiskWrite(evalSdp.getP2DiskWrite());
			objectToSave.setP3DiskWrite(evalSdp.getP3DiskWrite());
			objectToSave.setP4DiskWrite(evalSdp.getP4DiskWrite());
			objectToSave.setP5DiskWrite(evalSdp.getP5DiskWrite());
			objectToSave.setP6DiskWrite(evalSdp.getP6DiskWrite());
			
			objectToSave.setP1MemTotal(evalSdp.getP1MemTotal());
			objectToSave.setP2MemTotal(evalSdp.getP2MemTotal());
			objectToSave.setP3MemTotal(evalSdp.getP3MemTotal());
			objectToSave.setP4MemTotal(evalSdp.getP4MemTotal());
			objectToSave.setP5MemTotal(evalSdp.getP5MemTotal());
			objectToSave.setP6MemTotal(evalSdp.getP6MemTotal());
			
			objectToSave.setP1MemUsage(evalSdp.getP1MemUsage());
			objectToSave.setP2MemUsage(evalSdp.getP2MemUsage());
			objectToSave.setP3MemUsage(evalSdp.getP3MemUsage());
			objectToSave.setP4MemUsage(evalSdp.getP4MemUsage());
			objectToSave.setP5MemUsage(evalSdp.getP5MemUsage());
			objectToSave.setP6MemUsage(evalSdp.getP6MemUsage());
			
			objectToSave.setP1ProcUsage(evalSdp.getP1ProcUsage());
			objectToSave.setP2ProcUsage(evalSdp.getP2ProcUsage());
			objectToSave.setP3ProcUsage(evalSdp.getP3ProcUsage());
			objectToSave.setP4ProcUsage(evalSdp.getP4ProcUsage());
			objectToSave.setP5ProcUsage(evalSdp.getP5ProcUsage());
			objectToSave.setP6ProcUsage(evalSdp.getP6ProcUsage());
			
			getEvalSdpFacade().update(objectToSave);
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(EvalSdp a, @PathParam("id")String id){
		EvalSdp action = getEvalSdpFacade().find(Integer.parseInt(id));
		action.setResource(a.getResource());
		//action.setEvalSdp(a.getEvalSdp());
		action.setRule(a.getRule());
		getEvalSdpFacade().update(action);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		EvalSdp action = getEvalSdpFacade().find(Integer.parseInt(id));
		getEvalSdpFacade().delete(action);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public EvalSdpListWrapper getResourceEvalSdpByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		EvalSdpListWrapper paginatedListWrapper = new EvalSdpListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findEvalSdpSearch(paginatedListWrapper,d);

	}
	
}
