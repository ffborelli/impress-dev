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

import eu.com.impress.model.ResourceContext;
import eu.com.impress.facade.ResourceContextFacade;

@Path("resourcecontext")
public class ResourceContextResource {

	private ResourceContextFacade resourceContextFacade;

	public ResourceContextFacade getFacade() {
		if (this.resourceContextFacade == null)
			this.resourceContextFacade = new ResourceContextFacade();

		return this.resourceContextFacade;
	}

	@GET
	@Produces("application/json")
	public List<ResourceContext> getAll() {
		List<ResourceContext> resourceContextList = getFacade().listAll();
		return resourceContextList;
	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public ResourceContext getById(@PathParam("id") String id) {
		ResourceContext resourceContext = getFacade().find(Integer.parseInt(id));
		return resourceContext;
	}

	@POST
	@Consumes("application/json")
	public void insert(ResourceContext resourceContext) {
		getFacade().create(resourceContext);
	}

	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public void update(ResourceContext r, @PathParam("id") String id) {
		ResourceContext resourceContext = getFacade().find(Integer.parseInt(id));
		resourceContext.setContext(r.getContext());
		resourceContext.setResource(r.getResource());
		getFacade().update(resourceContext);
	}

	@DELETE
	@Path("{id}")
	@Consumes("application/json")
	public void delete(@PathParam("id") String id) {
		ResourceContext resourceContext = getFacade().find(Integer.parseInt(id));
		getFacade().delete(resourceContext);
	}

}
