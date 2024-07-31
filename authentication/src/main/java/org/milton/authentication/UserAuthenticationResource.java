package org.milton.authentication;


import org.jboss.resteasy.reactive.NoCache;

import io.quarkus.security.identity.SecurityIdentity;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/users")
public class UserAuthenticationResource {

    @Inject
    SecurityIdentity identity;

    @GET
    @Path("/me")
    @NoCache
    public User me(){
        return new User(identity);
    }

    public static class User {
        private final String userName;

        User(SecurityIdentity identity){
            this.userName = identity.getPrincipal().getName();
        }

        public String getUserName(){
            return userName;
        }
    }
}
