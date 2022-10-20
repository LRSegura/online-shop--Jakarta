package com.lab.onlineshop.webservice.product;

import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.product.*;
import com.lab.onlineshop.model.product.type.*;
import com.lab.onlineshop.model.webservices.SimpleResponse;
import com.lab.onlineshop.ui.product.ProductEvents;
import com.lab.onlineshop.webservice.util.ResponseStatus;
import jakarta.ejb.EJB;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class WsProducts {

    @EJB
    private ProductEvents productEvents;

    @GET
    @Path("products/for/sales")
    @Produces("text/json")
    public String getProductsForSale(){
        List<ProductRecord> productRecords =
                productEvents.getProductService().getProducts().stream().map(product -> new ProductRecord(product.getId(),
                        product.getAvailableQuantity(), product.getDescription(), product.getPrice(),
                        product.getProductType().getDescription(), product.getStock().getDescription())).toList();
        Jsonb json = JsonbBuilder.create();
        return json.toJson(productRecords);
    }

    @GET
    @Path("application/products")
    public Response getProducts() {
        Jsonb jsonb = JsonbBuilder.create();
        List<JsonProducts> jsonProductsList = productEvents.getProductService().getProducts().stream().map(product ->
                new JsonProducts(product.getId(), product.getDescription(), product.getAvailableQuantity(), product.getPrice(),
                        product.getProductType().getId(),product.getIsAvailable(),product.getStock().getDescription(),
                        product.getRegisterDate())).toList();
        return Response.status(Response.Status.OK).entity(jsonb.toJson(jsonProductsList)).build();
    }

    @POST
    @Path("application/product/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveProduct(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonAddProduct jsonAddProduct = jsonb.fromJson(json, JsonAddProduct.class);
        SimpleResponse response;
        try {
            Product newProductType = getProductFromJson(jsonAddProduct);
            productEvents.saveWithValidation(newProductType);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),exception.getMessage(),exception.getSpecificErrorList());
        } catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error saving product type: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private Product getProductFromJson(JsonAddProduct jsonAddUser){
        Product product = new Product();
        return getProductFromJson(product, jsonAddUser.description(), jsonAddUser.availableQuantity(), jsonAddUser.price(),
                jsonAddUser.productType(), jsonAddUser.isAvailable(), jsonAddUser.stock());
    }

    @DELETE
    @Path("application/product/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteProduct(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonDeleteProduct jsonDeleteProduct = jsonb.fromJson(json, JsonDeleteProduct.class);
        List<Product> products = new ArrayList<>();
        jsonDeleteProduct.productsId().forEach(id -> products.add(productEvents.getEntity(Product.class, id)));
        productEvents.delete(products);
        SimpleResponse response = new SimpleResponse(true,"");
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    @PUT
    @Path("application/product/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateProduct(String json){
        Jsonb jsonb = JsonbBuilder.create();
        JsonUpdateProduct jsonUpdateProductType = jsonb.fromJson(json, JsonUpdateProduct.class);
        SimpleResponse response;
        try {
            Product product = getProductFromJson(jsonUpdateProductType);
            productEvents.updateWithValidation(product);
            response = new SimpleResponse(ResponseStatus.SUCCESS.getStatus(),"");
        } catch (EntityFieldValueException exception) {
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(), exception.getMessage(), exception.getSpecificErrorList());
        }
        catch (Exception exception){
            response = new SimpleResponse(ResponseStatus.FAILED.getStatus(),"Error updating Product: " + exception.getMessage());
        }
        return Response.status(Response.Status.OK).entity(jsonb.toJson(response)).build();
    }

    private Product getProductFromJson(JsonUpdateProduct jsonUpdateProduct){
        Product product = productEvents.getEntity(Product.class, jsonUpdateProduct.id());
        return getProductFromJson(product, jsonUpdateProduct.description(), jsonUpdateProduct.availableQuantity(), jsonUpdateProduct.price(),
                jsonUpdateProduct.productType(), jsonUpdateProduct.isAvailable(), jsonUpdateProduct.stock());
    }

    private Product getProductFromJson(Product product, String description, Integer availableQuantity, BigDecimal price,
                                           Long productType, Boolean isAvailable, String stock) {
        product.setDescription(description);
        product.setAvailableQuantity(availableQuantity);
        product.setPrice(price);
        product.setProductType(productEvents.getDao().getEntity(ProductType.class, productType));
        product.setIsAvailable(isAvailable);
        product.setStock(Stock.getStock(stock));
        return product;
    }
}
