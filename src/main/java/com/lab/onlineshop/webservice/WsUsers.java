package com.lab.onlineshop.webservice;

import com.lab.onlineshop.model.user.JsonUserResponse;
import com.lab.onlineshop.services.user.UserService;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
public class WsUsers {

    @Inject
    private UserService userService;

    @GET
    @Path("application/users")
    public Response getUsers(){
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonUserResponse> jsonUserResponseList = userService.getUsers().stream().map(user ->
                new JsonUserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getUserLevel(),
                        user.getIsActive(),user.getRegisterDate())).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonUserResponseList)).build();
    }
}
