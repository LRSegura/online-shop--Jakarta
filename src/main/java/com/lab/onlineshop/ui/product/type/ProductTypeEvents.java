package com.lab.onlineshop.ui.product.type;

import com.lab.onlineshop.model.product.type.ProductType;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.AbstractEntityEvents;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ProductTypeEvents extends AbstractEntityEvents<ProductType> {

    @Inject
    private ProductTypeService productTypeService;

    public ProductTypeService getProductTypeService() {
        return productTypeService;
    }
}
