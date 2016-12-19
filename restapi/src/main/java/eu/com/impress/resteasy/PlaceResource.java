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
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eu.com.impress.model.Place;
import eu.com.impress.model.PlaceType;
import eu.com.impress.pagination.PlaceListWrapper;
import eu.com.impress.facade.PlaceFacade;

@Path("place")
public class PlaceResource {
	
	private PlaceFacade placeFacade;
	
	public PlaceFacade getPlaceFacade(){
		//if(this.placeFacade == null)
			this.placeFacade = new PlaceFacade();
		
		return this.placeFacade; 
	}
	
	private PlaceListWrapper findPlaces(PlaceListWrapper wrapper) {

		wrapper.setTotalResults(this.getPlaceFacade().countPlaces());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(this.getPlaceFacade().findPlaces(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections()));
		return wrapper;
	}
	
	private PlaceListWrapper findPlacesSearch(PlaceListWrapper wrapper, String search) {


		//wrapper.setTotalResults(1);
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		
		wrapper.setTotalResults(this.getPlaceFacade().getRowCountSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search) );
		
		wrapper.setList(this.getPlaceFacade().findPlacesSearch(start,
				wrapper.getPageSize(), wrapper.getSortFields(),
				wrapper.getSortDirections(), search));
		return wrapper;
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PlaceListWrapper list(
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		PlaceListWrapper paginatedListWrapper = new PlaceListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findPlaces(paginatedListWrapper);
	}
		
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Place getById(@PathParam("id") String id){
		Place place = getPlaceFacade().find(Integer.parseInt(id));
		return place;
	}
	
	@POST
	@Consumes("application/json")
	public void insert(Place place){
		
		Place objectToSave = new Place();

		if (place.getId() == null) {

			objectToSave.setDescription(place.getDescription());
			objectToSave.setDependentIndependent(place.getDependentIndependent());
			objectToSave.setPlaceType(place.getPlaceType());
			objectToSave.setPlace(place.getPlace());
			this.getPlaceFacade().create(objectToSave);
		} else {
			objectToSave.setId(place.getId());
			objectToSave.setDescription(place.getDescription());
			objectToSave.setDependentIndependent(place.getDependentIndependent());
			objectToSave.setPlaceType(place.getPlaceType());
			objectToSave.setPlace(place.getPlace());
			this.getPlaceFacade().update(objectToSave);

		}
	}
	
//	@PUT
//	@Path("{id}")
//	@Consumes("application/json")
//	public void update(Place p, @PathParam("id")String id){
//		Place place = getPlaceFacade().find(Integer.parseInt(id));
//		place.setDescription(p.getDescription());
//		place.setDependentIndependent(p.getDependentIndependent());
//		place.setPlaceType(p.getPlaceType());
//		place.setPlace(p.getPlace());
//		getPlaceFacade().update(place);
//	}
	
	@DELETE
	@Path("/{id}")
	@Consumes("*/*")
	public void delete(@PathParam("id") String id){
		Place Place = getPlaceFacade().find(Integer.parseInt(id));
		getPlaceFacade().delete(Place);
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Place> getPlaces() {
		// StringBuilder result = new StringBuilder();

		List<Place> listPlace = this.getPlaceFacade().listAll();

		return listPlace;
	}
	
	@GET
	@Path("/search/{d}")
	@Produces(MediaType.APPLICATION_JSON)
	public PlaceListWrapper getPlaceByDescription(
			@PathParam("d") String d,
			@DefaultValue("1") @QueryParam("page") Integer page,
			@DefaultValue("id") @QueryParam("sortFields") String sortFields,
			@DefaultValue("asc") @QueryParam("sortDirections") String sortDirections) {
		
		PlaceListWrapper paginatedListWrapper = new PlaceListWrapper();
		paginatedListWrapper.setCurrentPage(page);
		paginatedListWrapper.setSortFields(sortFields);
		paginatedListWrapper.setSortDirections(sortDirections);
		paginatedListWrapper.setPageSize(10);
		return findPlacesSearch(paginatedListWrapper,d);

		//List<Place> listPlace = this.getPlaceFacade().getByDescription(d);

		//return listPlace;
	}
}
