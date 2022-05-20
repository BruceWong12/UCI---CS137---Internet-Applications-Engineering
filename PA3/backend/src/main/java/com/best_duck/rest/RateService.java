package com.best_duck.rest;

import com.best_duck.Database;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static java.lang.String.valueOf;

@Path("getrate")
public class RateService {
        @Path("{name}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_XML)
        public Object hi(@PathParam("name") String name){
            return Database.getStatesByName(valueOf(name));
        }
}
