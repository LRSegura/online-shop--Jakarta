package com.lab.onlineshop.webservice;

import com.lab.onlineshop.model.user.JsonUserRequest;
import com.lab.onlineshop.model.user.JsonUserResponse;
import com.lab.onlineshop.model.user.User;
import com.lab.onlineshop.model.user.UserLevel;
import com.lab.onlineshop.model.webservices.SimpleResponse;
import com.lab.onlineshop.services.dao.Dao;
import com.lab.onlineshop.services.user.UserService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
public class WsUsers {

    @Inject
    private UserService userService;

    @EJB
    private Dao dao;

    @GET
    @Path("application/users")
    public Response getUsers(){
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonUserResponse> jsonUserResponseList = userService.getUsers().stream().map(user ->
                new JsonUserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getUserLevel(),
                        user.getIsActive(),user.getRegisterDate())).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonUserResponseList)).build();
    }

    @POST
    @Path("application/users/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveUser(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonUserRequest jsonUserRequest = jsonb.fromJson(json, JsonUserRequest.class);
        SimpleResponse response;
        try {
            User newUser = getUserFrom(jsonUserRequest);
            dao.save(newUser);
            response = new SimpleResponse(true,"");
        }catch (Exception exception){
            response = new SimpleResponse(false,"Error saving user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private User getUserFrom(JsonUserRequest jsonUserRequest){
        User user = new User();
        user.setFirstName(jsonUserRequest.firstName());
        user.setLastName(jsonUserRequest.lastName());
        user.setUserName(jsonUserRequest.userName());
        user.setPassword(jsonUserRequest.password());
        user.setEmail(jsonUserRequest.email());
        user.setUserLevel(UserLevel.getUserLevel(jsonUserRequest.userLevel()));
        user.setIsActive(jsonUserRequest.isActive());
        return user;
    }
}
