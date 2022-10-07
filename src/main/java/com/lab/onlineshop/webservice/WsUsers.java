package com.lab.onlineshop.webservice;

import com.lab.onlineshop.model.user.JsonAddUserRequest;
import com.lab.onlineshop.model.user.JsonGetUsersResponse;
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
        List<JsonGetUsersResponse> jsonGetUsersResponseList = userService.getUsers().stream().map(user ->
                new JsonGetUsersResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getUserLevel().getDescription(),
                        user.getIsActive(),user.getRegisterDate(), false)).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonGetUsersResponseList)).build();
    }

    @POST
    @Path("application/users/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveUser(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonAddUserRequest jsonAddUserRequest = jsonb.fromJson(json, JsonAddUserRequest.class);
        SimpleResponse response;
        try {
            User newUser = getUserFrom(jsonAddUserRequest);
            dao.save(newUser);
            response = new SimpleResponse(true,"");
        }catch (Exception exception){
            response = new SimpleResponse(false,"Error saving user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private User getUserFrom(JsonAddUserRequest jsonAddUserRequest){
        User user = new User();
        user.setFirstName(jsonAddUserRequest.firstName());
        user.setLastName(jsonAddUserRequest.lastName());
        user.setUserName(jsonAddUserRequest.userName());
        user.setPassword(jsonAddUserRequest.password());
        user.setEmail(jsonAddUserRequest.email());
        user.setUserLevel(UserLevel.getUserLevel(jsonAddUserRequest.userLevel()));
        user.setIsActive(jsonAddUserRequest.isActive());
        return user;
    }

    @POST
    @Path("application/users/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteUser(String json){
        return null;
    }
}
