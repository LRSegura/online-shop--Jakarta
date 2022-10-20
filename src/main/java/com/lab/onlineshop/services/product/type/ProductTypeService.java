package com.lab.onlineshop.services.product.type;

import com.lab.onlineshop.model.product.type.ProductType;
import com.lab.onlineshop.model.Service;

import java.util.List;

public sealed interface ProductTypeService extends Service permits ProductTypeImplementation {

    List<ProductType> getProductsType();
}
