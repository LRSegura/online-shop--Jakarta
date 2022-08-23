package com.lab.onlineshop.services.product;

import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.model.Service;

import java.util.List;

public interface ProductService extends Service{
    List<Product> getProducts();

}
