package com.lab.onlineshop.webservice;

import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.ui.AbstractEntityEvents;
import com.lab.onlineshop.webservice.json.JsonAdd;
import com.lab.onlineshop.webservice.json.JsonDelete;
import com.lab.onlineshop.webservice.json.JsonUpdate;
import com.lab.onlineshop.webservice.json.response.JsonDataResponse;
import com.lab.onlineshop.webservice.json.response.SimpleResponse;
import com.lab.onlineshop.webservice.util.ResponseStatus;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public interface EntityWebService<T extends AbstractEntity, E extends AbstractEntityEvents<T>> {

    default Response get(Supplier<List<? extends JsonDataResponse>> supplier){
        Jsonb jsonb = JsonbBuilder.create();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(supplier.get())).build();
    }

    default Response save(E entityEvents, String json, Class<? extends JsonAdd> jsonClass){
        Jsonb jsonb = JsonbBuilder.create();
        JsonAdd jsonAdd = jsonb.fromJson(json, jsonClass);
        SimpleResponse response;
        try {
            T newEntity = getEntityFromJson(jsonAdd);
            entityEvents.saveWithValidation(newEntity);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),exception.getMessage(),exception.getSpecificErrorList());
        } catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error saving user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    default Response delete(E entityEvents, Class<T> entityClass, Class<? extends JsonDelete> jsonClass, String json ){
        Jsonb jsonb = JsonbBuilder.create();
        JsonDelete deleteUsers = jsonb.fromJson(json, jsonClass);
        List<T> entities = new ArrayList<>();
        deleteUsers.getIdList().forEach(id -> entities.add(entityEvents.getEntity(entityClass, id)));
        entityEvents.delete(entities);
        SimpleResponse response = new SimpleResponse(true,"");
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    default Response update(E entityEvents,Class<? extends JsonUpdate> jsonClass,  String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonUpdate jsonAdd = jsonb.fromJson(json, jsonClass);
        SimpleResponse response;
        try {
            T updateEntity = getEntityFromJson(jsonAdd);
            entityEvents.updateWithValidation(updateEntity);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(), exception.getMessage(), exception.getSpecificErrorList());
        }
        catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error updating user: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }
    T getEntityFromJson(JsonAdd json);
    T getEntityFromJson(JsonUpdate json);
}
