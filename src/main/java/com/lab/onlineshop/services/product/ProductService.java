package com.lab.onlineshop.services.product;

import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.model.Service;

import java.util.List;

public sealed interface ProductService extends Service permits ProductImplementation{
    List<Product> getProducts();

}
