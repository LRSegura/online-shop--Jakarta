package com.lab.onlineshop.webservice.product;

import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.product.*;
import com.lab.onlineshop.model.product.type.*;
import com.lab.onlineshop.webservice.EntityWebService;
import com.lab.onlineshop.webservice.customer.json.model.JsonDeleteCustomer;
import com.lab.onlineshop.webservice.json.JsonAdd;
import com.lab.onlineshop.webservice.json.JsonUpdate;
import com.lab.onlineshop.webservice.json.response.JsonDataResponse;
import com.lab.onlineshop.webservice.json.response.SimpleResponse;
import com.lab.onlineshop.ui.product.ProductEvents;
import com.lab.onlineshop.webservice.product.json.model.*;
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
import java.util.function.Supplier;

@Path("/")
public class WsProducts implements EntityWebService<Product, ProductEvents> {

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
        Supplier<List<? extends JsonDataResponse>> supplier = () ->  productEvents.getProductService().getProducts().stream().map(product ->
                new JsonProducts(product.getId(), product.getDescription(), product.getAvailableQuantity(), product.getPrice(),
                        product.getProductType().getId(),product.getIsAvailable(),product.getStock().getDescription(),
                        product.getRegisterDate())).toList();
        return get(supplier);
    }

    @POST
    @Path("application/product/add")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response saveProduct(String json){
        return save(productEvents,json,JsonAddProduct.class);
    }

    @DELETE
    @Path("application/product/delete")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deleteProduct(String json){
        return delete(productEvents,Product.class, JsonDeleteProduct.class,json);
    }

    @PUT
    @Path("application/product/update")
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response updateProduct(String json){
        return update(productEvents, JsonUpdateProduct.class,json);
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

    @Override
    public Product getEntityFromJson(JsonAdd json) {
        Product product = new Product();
        JsonAddProduct jsonAddProduct = (JsonAddProduct) json;
        return getProductFromJson(product, jsonAddProduct.description(), jsonAddProduct.availableQuantity(), jsonAddProduct.price(),
                jsonAddProduct.productType(), jsonAddProduct.isAvailable(), jsonAddProduct.stock());
    }

    @Override
    public Product getEntityFromJson(JsonUpdate json) {
        JsonUpdateProduct jsonUpdateProduct = (JsonUpdateProduct)json;
        Product product = productEvents.getEntity(Product.class, jsonUpdateProduct.id());
        return getProductFromJson(product, jsonUpdateProduct.description(), jsonUpdateProduct.availableQuantity(), jsonUpdateProduct.price(),
                jsonUpdateProduct.productType(), jsonUpdateProduct.isAvailable(), jsonUpdateProduct.stock());
    }
}
