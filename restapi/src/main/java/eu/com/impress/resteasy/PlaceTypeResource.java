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

import eu.com.impress.model.PlaceType;
import eu.com.impress.pagination.PlaceTypeListWrapper;
import eu.com.impress.facade.PlaceTypeFacade;

@Path("/placetype")
public class PlaceTypeResource {
	
	private PlaceTypeFacade placeTypeFacade;
	
	public PlaceTypeFacade getPlaceTypeFacade(){
		//if(this.placeTypeFacade == null)
			this.placeTypeFacade = new PlaceTypeFacade();
		
		return this.placeTypeFacade; 
	}
	
	private PlaceTypeListWrapper findPlaceType(PlaceTypeListWrapper wrapper) {

		wrapper.setTotalResults(this.getPlaceTypeFacade().countPlaceTypes());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getPlaceTypeFacade().findPlaces(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private PlaceTypeListWrapper findPlaceTypeSearch(PlaceTypeListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getPlaceTypeFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getPlaceTypeFacade().findPlacesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PlaceTypeListWrapper list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		PlaceTypeListWrapper paginatedListWrapper = new PlaceTypeListWrapper();
		
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findPlaceType(paginatedListWrapper);
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public PlaceType getById(@PathParam("id") String id){
		PlaceType placeType = getPlaceTypeFacade().find(Integer.parseInt(id));
		return placeType;
	}
	
	@POST
	@Consumes("application/json")
	public PlaceType insert(PlaceType placeType){
		//getPlaceTypeFacade().create(placeType);
		
		PlaceType objectToSave = new PlaceType();

		if (placeType.getId() == null) {

			objectToSave.setDescription(placeType.getDescription());
			// objectToSave.setPlace(place);
			// objectToSave.setImageUrl(person.getImageUrl());
			this.getPlaceTypeFacade().create(objectToSave);
		} else {
			objectToSave.setId(placeType.getId());
			objectToSave.setDescription(placeType.getDescription());
			this.getPlaceTypeFacade().update(objectToSave);
		}

		return objectToSave;
	}
	
//	@PUT
//	@Path("{id}")
//	@Consumes("application/json")
//	public void update(PlaceType p, @PathParam("id")String id){
//		PlaceType placeType = getPlaceTypeFacade().find(Integer.parseInt(id));
//		placeType.setDescription(p.getDescription());
//		getPlaceTypeFacade().update(placeType);
//	}
	
	@DELETE
	@Path("/{id}")
	@Consumes("*/*")
	//@Consumes("application/json")
	public void delete(@PathParam("id") String id){
		PlaceType placeType = getPlaceTypeFacade().find(Integer.parseInt(id));
		getPlaceTypeFacade().delete(placeType);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlaceType> getPlaceTypes() {

		List<PlaceType> listPlaceType = this.getPlaceTypeFacade().listAll();

		return listPlaceType;
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public PlaceTypeListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		PlaceTypeListWrapper paginatedListWrapper = new PlaceTypeListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findPlaceTypeSearch(paginatedListWrapper,d);

	}
	
}
