package io.quarkus.oidc.token.propagation.reactive;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/frontend")
public class FrontendResource {
    @Inject
    @RestClient
    AccessTokenPropagationService accessTokenPropagationService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("token-propagation")
    @RolesAllowed("admin")
    public String userNameTokenPropagation() {
        if ("alice".equals(jwt.getName())) {
            return "Token issued to " + jwt.getName() + " has been exchanged, new user name: "
                    + accessTokenPropagationService.getUserName();
        } else {
            throw new RuntimeException();
        }
    }
}
