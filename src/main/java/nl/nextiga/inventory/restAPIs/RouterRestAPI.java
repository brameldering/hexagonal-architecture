package nl.nextiga.inventory.restAPIs;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import nl.nextiga.inventory.domain.Router;
import nl.nextiga.inventory.framework.adapters.input.stdin.RouterViewCLIAdapter;

import java.util.List;

@Path("/test")
public class RouterRestAPI {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String retrieveRouters() {
        var cli = new RouterViewCLIAdapter();
        var type = "CORE";

        List<Router> relatedRouters= cli.obtainRelatedRouters(type);
        System.out.println(relatedRouters);

        return relatedRouters.toString();
    }
}
