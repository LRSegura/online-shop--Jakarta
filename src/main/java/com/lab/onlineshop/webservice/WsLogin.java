package com.lab.onlineshop.webservice;

import com.lab.onlineshop.api.util.Context;
import com.lab.onlineshop.model.login.JsonLoginRequest;
import com.lab.onlineshop.model.login.JsonLoginResponse;
import com.lab.onlineshop.model.webservices.DataResponse;
import com.lab.onlineshop.model.webservices.JsonDataResponse;
import com.lab.onlineshop.model.webservices.SimpleResponse;
import com.lab.onlineshop.services.customer.CustomerService;
import com.lab.onlineshop.services.user.UserService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class WsLogin {
    @Inject
    private UserService userService;
    @Inject
    private CustomerService customerService;
    @EJB
    private Context applicationContext;
    private JsonLoginResponse jsonLoginResponse;

    @POST
    @Path("application/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String json) {
        Jsonb jsonb = JsonbBuilder.create();
        JsonLoginRequest jsonLogin = jsonb.fromJson(json, JsonLoginRequest.class);
        boolean success = login(jsonLogin.userName(), jsonLogin.password());
        if (success) {
            DataResponse response = new DataResponse(true, null, jsonLoginResponse);
            return Response.status(200).entity(jsonb.toJson(response)).build();
        } else {
            SimpleResponse response = new SimpleResponse(false, "Username or password incorrect");
            return Response.status(200).entity(jsonb.toJson(response)).build();
        }
    }

    public boolean login(String userName, String password) {
        userService.getUser(userName, password).ifPresentOrElse(user -> {
                    applicationContext.getRequestMapApplication().put("AbstractPerson", user);
                    applicationContext.init();
                },
                () -> customerService.getCustomer(userName, password).ifPresent(customer -> {
                    applicationContext.getRequestMapApplication().put("AbstractPerson", customer);
                    applicationContext.init();
                }));

        if (applicationContext.isLoggedUser()) {
            applicationContext.getLoggedUser().ifPresent(user ->
                    jsonLoginResponse = new JsonLoginResponse(true, false, user.getUserName()));
            return true;
        } else if (applicationContext.isLoggedCustomer()) {
            applicationContext.getLoggedCustomer().ifPresent(customer ->
                    jsonLoginResponse = new JsonLoginResponse(false, true, customer.getFirstName() + customer.getLastName()));
            return true;
        } else {
            jsonLoginResponse = new JsonLoginResponse(false, false, null);
            return false;
        }
    }
}
