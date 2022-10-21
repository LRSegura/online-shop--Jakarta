package com.lab.onlineshop.webservice.product.type;

import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.product.type.*;
import com.lab.onlineshop.webservice.response.SimpleResponse;
import com.lab.onlineshop.ui.product.type.ProductTypeEvents;
import com.lab.onlineshop.webservice.product.type.json.model.JsonAddProductType;
import com.lab.onlineshop.webservice.product.type.json.model.JsonDeleteProductType;
import com.lab.onlineshop.webservice.product.type.json.model.JsonProductsType;
import com.lab.onlineshop.webservice.product.type.json.model.JsonUpdateProductType;
import com.lab.onlineshop.webservice.util.ResponseStatus;
import jakarta.ejb.EJB;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/")
public class WsProductsType {

    @EJB
    private ProductTypeEvents productTypeEvents;

    @GET
    @Path("application/productType")
    public Response getProductType() {
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonProductsType> jsonUsersList = productTypeEvents.getProductTypeService().getProductsType().stream().map(productType ->
                new JsonProductsType(productType.getId(), productType.getDescription(), productType.getRegisterDate())).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonUsersList)).build();
    }

    @POST
    @Path("application/productType/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveProductType(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonAddProductType jsonAddUser = jsonb.fromJson(json, JsonAddProductType.class);
        SimpleResponse response;
        try {
            ProductType newProductType = getProductTypeFromJson(jsonAddUser);
            productTypeEvents.saveWithValidation(newProductType);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),exception.getMessage(),exception.getSpecificErrorList());
        } catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error saving product type: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private ProductType getProductTypeFromJson(JsonAddProductType jsonAddUser){
        ProductType productType = new ProductType();
        return getProductTypeFromJson(productType, jsonAddUser.description());
    }

    @DELETE
    @Path("application/productType/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteProductType(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonDeleteProductType jsonDeleteProductType = jsonb.fromJson(json, JsonDeleteProductType.class);
        List<ProductType> productTypes = new ArrayList<>();
        jsonDeleteProductType.productsTypeId().forEach(id -> productTypes.add(productTypeEvents.getEntity(ProductType.class, id)));
        productTypeEvents.delete(productTypes);
        SimpleResponse response = new SimpleResponse(true,"");
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    @PUT
    @Path("application/productType/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateProductType(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonUpdateProductType jsonUpdateProductType = jsonb.fromJson(json, JsonUpdateProductType.class);
        SimpleResponse response;
        try {
            ProductType productType = getProductTypeFromJson(jsonUpdateProductType);
            productTypeEvents.updateWithValidation(productType);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(), exception.getMessage(), exception.getSpecificErrorList());
        }
        catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error updating Product Type: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private ProductType getProductTypeFromJson(JsonUpdateProductType jsonUpdateProductType){
        ProductType productType = productTypeEvents.getEntity(ProductType.class, jsonUpdateProductType.id());
        return getProductTypeFromJson(productType, jsonUpdateProductType.description());
    }

    private ProductType getProductTypeFromJson(ProductType productType, String description) {
        productType.setDescription(description);
        return productType;
    }

}
