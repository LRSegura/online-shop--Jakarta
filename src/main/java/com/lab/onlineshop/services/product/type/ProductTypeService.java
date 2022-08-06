package com.lab.onlineshop.services.product.type;

import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.model.Service;

import java.util.List;

public interface ProductTypeService extends Service {

    List<ProductType> getProductsType();
}
