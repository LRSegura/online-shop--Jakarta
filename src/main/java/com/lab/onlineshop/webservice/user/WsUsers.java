package com.lab.onlineshop.webservice.user;

import com.lab.onlineshop.model.user.*;
import com.lab.onlineshop.webservice.EntityWebService;
import com.lab.onlineshop.webservice.json.JsonAdd;
import com.lab.onlineshop.webservice.json.JsonUpdate;
import com.lab.onlineshop.webservice.json.response.JsonDataResponse;
import com.lab.onlineshop.ui.user.UserEvents;
import com.lab.onlineshop.webservice.user.json.model.JsonAddUser;
import com.lab.onlineshop.webservice.user.json.model.JsonDeleteUsers;
import com.lab.onlineshop.webservice.user.json.model.JsonUpdateUser;
import com.lab.onlineshop.webservice.user.json.model.JsonUsers;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.function.Supplier;

@Path("/")
public class WsUsers implements EntityWebService<User, UserEvents> {

    @EJB
    private UserEvents userEvents;

    @GET
    @Path("application/users")
    public Response getUsers() {
        Supplier<List<? extends JsonDataResponse>> supplier = () -> userEvents.getUserService().getUsers().stream().map(user ->
                new JsonUsers(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(),
                        user.getPassword(), user.getUserLevel().getDescription(),
                        user.getIsActive(),user.getRegisterDate(), false)).toList();
        return get(supplier);
    }

    @POST
    @Path("application/users/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveUser(String json){
        return save(userEvents,json,JsonAddUser.class);
    }

    @DELETE
    @Path("application/users/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteUser(String json){
        return delete(userEvents, User.class, JsonDeleteUsers.class,json);
    }

    @PUT
    @Path("application/users/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateUser(String json){
        return update(userEvents, JsonUpdateUser.class,json);
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

    @Override
    public User getEntityFromJson(JsonAdd json) {
        JsonAddUser jsonAddUser = (JsonAddUser) json;
        User user = new User();
        return getUser(user, jsonAddUser.firstName(), jsonAddUser.lastName(), jsonAddUser.userName(), jsonAddUser.password(),
                jsonAddUser.email(), jsonAddUser.userLevel(), jsonAddUser.isActive());
    }

    @Override
    public User getEntityFromJson(JsonUpdate json) {
        JsonUpdateUser jsonUpdateUser = (JsonUpdateUser)json;
        User user = userEvents.getEntity(User.class, jsonUpdateUser.id());
        return getUser(user, jsonUpdateUser.firstName(), jsonUpdateUser.lastName(), jsonUpdateUser.userName(), jsonUpdateUser.password(),
                jsonUpdateUser.email(), jsonUpdateUser.userLevel(), jsonUpdateUser.isActive());
    }
}
