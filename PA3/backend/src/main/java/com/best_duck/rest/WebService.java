package com.best_duck.rest;

import com.alibaba.fastjson.JSONObject;
import com.best_duck.Database;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Map;

@Path("")
public class WebService {

    @Path("getAllProductsByCategory/{category}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Object getAllProductsByCategory(@PathParam("category") String category){
        System.out.println(Database.getAllProductsByCategory(category));
        return Database.getAllProductsByCategory(category);
    }

    @Path("getOrderInfo/{orderID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Object getOrderInfo(@PathParam("orderID") String orderID){
        return Database.getOrderInfo(orderID);
    }

    @Path("getOrderProducts/{orderID}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Object getOrderProducts(@PathParam("orderID") String orderID){
        return Database.getOrderProducts(orderID);
    }

    @Path("getproducts/{sku}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Object getProduct(@PathParam("sku") String sku){
        return Database.getProduct(sku);
    }

    @Path("setOrder")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Object setOrder(JSONObject object){
    System.out.println(object);
        Database.setOrder(
                object.getString("order_id"),
                object.getIntValue("sku"),
                object.getString("firstname"),
                object.getString("lastname"),
                object.getString("address"),
                object.getString("city"),
                object.getString("state"),
                object.getString("zip"),
                object.getString("shippingmethod"),
                object.getIntValue("qty"),
                object.getString("cardnumber"),
                object.getIntValue("expMonthInt"),
                object.getIntValue("expyearInt"),
                object.getIntValue("cvvInt"),
                object.getString("phone"),
                object.getString("email"),
                object.getString("user_id"),
                object.getObject("todaysDate",java.util.Date.class)
                );
        return object;
    }

    @Path("orderlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Object hi(){
        String userid="0";
        ArrayList<Map<String, Object>> orderList = com.best_duck.Database.getAllOrdersByUserid(userid);
        return orderList;
    }
}
