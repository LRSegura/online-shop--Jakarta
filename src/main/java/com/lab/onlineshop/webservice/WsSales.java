package com.lab.onlineshop.webservice;

import com.lab.onlineshop.model.product.ProductRecord;
import com.lab.onlineshop.services.product.ProductService;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

@Path("/")
public class WsSales {

    @Inject
    private ProductService productService;

    @GET
    @Path("products/for/sales")
    @Produces("text/json")
    public String getProductsForSale(){
        List<ProductRecord> productRecords =
                productService.getProducts().stream().map(product -> new ProductRecord(product.getId(),
                        product.getAvailableQuantity(), product.getDescription(), product.getPrice(),
                        product.getProductType().getDescription(), product.getStock().getDescription())).toList();
        Jsonb json = JsonbBuilder.create();
        return json.toJson(productRecords);
    }
}
