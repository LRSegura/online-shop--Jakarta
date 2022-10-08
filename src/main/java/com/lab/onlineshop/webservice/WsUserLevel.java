package com.lab.onlineshop.webservice;

import com.lab.onlineshop.model.user.JsonUserLevel;
import com.lab.onlineshop.model.user.UserLevel;
import com.lab.onlineshop.model.webservices.DataResponse;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.List;

@Path("/")
public class WsUserLevel {

    @GET
    @Path("application/users/userlevel")
    public Response getUserLevel(){
        Jsonb jsonb = JsonbBuilder.create();
        List<String> userLevels = Arrays.stream(UserLevel.values()).map(UserLevel::getDescription).toList();
        JsonUserLevel jsonUserLevel = new JsonUserLevel(userLevels);
        String json = jsonb.toJson(new DataResponse(true,"Success", jsonUserLevel));
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
