package com.lab.onlineshop.webservice;

import com.lab.onlineshop.model.user.*;
import com.lab.onlineshop.model.webservices.SimpleResponse;
import com.lab.onlineshop.services.dao.Dao;
import com.lab.onlineshop.services.user.UserService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
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
        List<JsonUsers> jsonUsersList = userService.getUsers().stream().map(user ->
                new JsonUsers(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(),
                        user.getPassword(), user.getUserLevel().getDescription(),
                        user.getIsActive(),user.getRegisterDate(), false)).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonUsersList)).build();
    }

    @POST
    @Path("application/users/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveUser(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonAddUser jsonAddUser = jsonb.fromJson(json, JsonAddUser.class);
        SimpleResponse response;
        try {
            User newUser = getUserFromJson(jsonAddUser);
            dao.save(newUser);
            response = new SimpleResponse(true,"");
        }catch (Exception exception){
            response = new SimpleResponse(false,"Error saving user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private User getUserFromJson(JsonAddUser jsonAddUser){
        User user = new User();
        return getUser(user, jsonAddUser.firstName(), jsonAddUser.lastName(), jsonAddUser.userName(), jsonAddUser.password(),
                jsonAddUser.email(), jsonAddUser.userLevel(), jsonAddUser.isActive());
    }

    @DELETE
    @Path("application/users/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteUser(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonDeleteUsers deleteUsers = jsonb.fromJson(json, JsonDeleteUsers.class);
        List<User> users = new ArrayList<>();
        deleteUsers.usersId().forEach(id -> users.add(dao.getEntity(User.class, id)));
        dao.delete(users);
        SimpleResponse response = new SimpleResponse(true,"");
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    @PUT
    @Path("application/users/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateUser(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonUpdateUser jsonAddUser = jsonb.fromJson(json, JsonUpdateUser.class);
        SimpleResponse response;
        try {
            User updateUser = getUserFromJson(jsonAddUser);
            dao.saveOrUpdate(updateUser);
            response = new SimpleResponse(true,"");
        }catch (Exception exception){
            response = new SimpleResponse(false,"Error updating user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private User getUserFromJson(JsonUpdateUser jsonUpdateUser){
        User user = dao.getEntity(User.class, jsonUpdateUser.id());
        return getUser(user, jsonUpdateUser.firstName(), jsonUpdateUser.lastName(), jsonUpdateUser.userName(), jsonUpdateUser.password(),
                jsonUpdateUser.email(), jsonUpdateUser.userLevel(), jsonUpdateUser.isActive());
    }

    private User getUser(User user, String firstName, String lastName, String userName, String password, String email,
                         String userLevel, boolean active) {
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setUserLevel(UserLevel.getUserLevel(userLevel));
        user.setIsActive(active);
        return user;
    }
}
