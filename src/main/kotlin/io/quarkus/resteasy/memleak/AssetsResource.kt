package io.quarkus.resteasy.memleak

import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/v1/")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
class AssetsResource {

    class Thing(val data: String)

    @GET
    @Path("/getthings")
    fun get(
    ): List<Thing> {
        return (1..100)
                .map {
                    // Need to return something large to trigger the issue, return 100k of data
                    val chars = CharArray(1024 * 100) { _ -> 'a' }
                    Thing(String(chars))
                }
                .toList()
    }
}