package Database;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/db")
public class RocksApiResource {

    @Inject
    RocksApiService rocksApiService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/save/{key}/{value}")
    public Response save(@PathParam ("key") String key, @PathParam ("value") String value) {
        return rocksApiService.save(key, value);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get/{key}")
    public Response get(@PathParam ("key") String key){
        return rocksApiService.get(key);
    }
}
