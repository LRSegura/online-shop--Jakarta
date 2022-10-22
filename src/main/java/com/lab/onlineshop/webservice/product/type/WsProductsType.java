package com.lab.onlineshop.webservice.product.type;

import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.product.type.*;
import com.lab.onlineshop.webservice.EntityWebService;
import com.lab.onlineshop.webservice.json.JsonAdd;
import com.lab.onlineshop.webservice.json.JsonUpdate;
import com.lab.onlineshop.webservice.json.response.JsonDataResponse;
import com.lab.onlineshop.webservice.json.response.SimpleResponse;
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
import java.util.function.Supplier;

@Path("/")
public class WsProductsType implements EntityWebService<ProductType, ProductTypeEvents> {

    @EJB
    private ProductTypeEvents productTypeEvents;

    @GET
    @Path("application/productType")
    public Response getProductType() {
        Supplier<List<? extends JsonDataResponse>> supplier = () -> productTypeEvents.getProductTypeService().getProductsType().stream().map(productType ->
                new JsonProductsType(productType.getId(), productType.getDescription(), productType.getRegisterDate())).toList();
        return get(supplier);
    }

    @POST
    @Path("application/productType/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveProductType(String json) {
        return save(productTypeEvents, json, JsonAddProductType.class);
    }

    @DELETE
    @Path("application/productType/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteProductType(String json) {
        return delete(productTypeEvents, ProductType.class, JsonDeleteProductType.class,json);
    }

    @PUT
    @Path("application/productType/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateProductType(String json) {
        return update(productTypeEvents, JsonUpdateProductType.class,json);
    }

    private ProductType getProductTypeFromJson(JsonUpdateProductType jsonUpdateProductType) {
        ProductType productType = productTypeEvents.getEntity(ProductType.class, jsonUpdateProductType.id());
        return getProductTypeFromJson(productType, jsonUpdateProductType.description());
    }

    private ProductType getProductTypeFromJson(ProductType productType, String description) {
        productType.setDescription(description);
        return productType;
    }

    @Override
    public ProductType getEntityFromJson(JsonAdd json) {
        JsonAddProductType jsonAddProductType = (JsonAddProductType) json;
        ProductType productType = new ProductType();
        return getProductTypeFromJson(productType, jsonAddProductType.description());
    }

    @Override
    public ProductType getEntityFromJson(JsonUpdate json) {
        JsonUpdateProductType jsonUpdateProductType = (JsonUpdateProductType)json;
        ProductType productType = productTypeEvents.getEntity(ProductType.class, jsonUpdateProductType.id());
        return getProductTypeFromJson(productType, jsonUpdateProductType.description());
    }
}
