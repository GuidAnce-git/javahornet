package database;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/db")
public class DatabaseApiResource {
    private static final Logger LOGGER = Logger.getLogger("RocksApiResource");

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/save/{key}/{value}")
    public Response save(@PathParam("key") final String key, @PathParam("value") final String value) {
        LOGGER.info("Save API call received");
        if (RedisDB.save(key, value)) return Response.ok().build();
        return Response.serverError().build();
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get/{key}")

    public static Response get(@PathParam("key") final String key) {
        LOGGER.info("Get API call received");
        return Response.ok(RedisDB.get(key)).build();

    }
}
