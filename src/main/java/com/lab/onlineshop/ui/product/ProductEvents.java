package com.lab.onlineshop.ui.product;

import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.ui.AbstractEntityEvents;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ProductEvents extends AbstractEntityEvents<Product> {

    @Inject
    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }
}
