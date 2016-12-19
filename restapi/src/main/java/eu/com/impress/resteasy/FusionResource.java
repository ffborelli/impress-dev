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

import eu.com.impress.model.Fusion;
import eu.com.impress.pagination.FusionListWrapper;
import eu.com.impress.pagination.ResourceTypeListWrapper;
import eu.com.impress.facade.FusionFacade;

@Path("fusion")
public class FusionResource {
	
	private FusionFacade fusionFacade;
	
	public FusionFacade getFusionFacade(){
		if(this.fusionFacade == null)
			this.fusionFacade = new FusionFacade();
		
		return this.fusionFacade; 
	}
	
	private FusionListWrapper findFusion(FusionListWrapper wrapper) {

		wrapper.setTotalResults(this.getFusionFacade().countFusions());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getFusionFacade().findFusions(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private FusionListWrapper findFusionSearch(FusionListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getFusionFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getFusionFacade().findFusionsSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public FusionListWrapper listResourceTypes(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		FusionListWrapper paginatedListWrapper = new FusionListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findFusion(paginatedListWrapper);
	}
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public List<Fusion> getAll(){
		List<Fusion> fusionList = getFusionFacade().listAll();
		return fusionList;
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Fusion getById(@PathParam("id") String id){
		Fusion fusion = getFusionFacade().find(Integer.parseInt(id));
		return fusion;
	}
	
	@POST
	@Consumes("application/json")
	public Fusion insert(Fusion fusion){
		
		Fusion objectToSave = new Fusion();
		
		if(fusion.getId() == null){
			
			objectToSave.setFusionName(fusion.getFusionName());
			objectToSave.setFusionText(fusion.getFusionText());
			objectToSave.setDependentIndependent(fusion.getDependentIndependent());
			this.getFusionFacade().create(objectToSave);
			
		}else{
			
			objectToSave.setId(fusion.getId());
			objectToSave.setFusionName(fusion.getFusionName());
			objectToSave.setFusionText(fusion.getFusionText());
			objectToSave.setDependentIndependent(fusion.getDependentIndependent());
			this.getFusionFacade().update(objectToSave);
			
		}
		
		return objectToSave;
	}
	
	/*@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(Fusion f, @PathParam("id")String id){
		Fusion fusion = getFusionFacade().find(Integer.parseInt(id));
		fusion.setFusionName(f.getFusionName());
		fusion.setFusionText(f.getFusionText());
		fusion.setDependentIndependent(f.getDependentIndependent());
		getFusionFacade().update(fusion);
	}*/
	
	@DELETE
	@Path("{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Fusion fusion = getFusionFacade().find(Integer.parseInt(id));
		getFusionFacade().delete(fusion);
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public FusionListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		FusionListWrapper paginatedListWrapper = new FusionListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findFusionSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
	
}
