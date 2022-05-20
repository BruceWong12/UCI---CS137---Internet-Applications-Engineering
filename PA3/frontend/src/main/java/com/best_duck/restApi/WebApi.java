package com.best_duck.restApi;

import com.best_duck.config.HttpConfig;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("")
public class WebApi implements HttpConfig {

  @GET
  @Path("orderlist")
  @Produces(MediaType.APPLICATION_JSON)
  public Object getOrderList() {
    return http.sync("/orderlist").bodyType("application/json").get().getBody().toString();
  }

  @GET
  @Path("getproducts/{sku}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_XML)
  public Object getProducts(@PathParam("sku") String sku){
    return http.sync("/getproducts/"+sku).bodyType("application/json").get().getBody().toString();
  }

  @GET
  @Path("getrate/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_XML)
  public Object getRate(@PathParam("name") String name){
    return http.sync("/getrate/"+name).bodyType("application/json").get().getBody().toString();
  }

  @GET
  @Path("gettaxregionname/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_XML)
  public Object getTax(@PathParam("name") String name){
    return http.sync("/gettaxregionname/"+name).bodyType("application/json").get().getBody().toString();
  }
}
