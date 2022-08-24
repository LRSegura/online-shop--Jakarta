package com.lab.onlineshop.services.product;

import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.model.Service;
import com.lab.onlineshop.model.UploadedAppFile;

import java.util.List;
import java.util.Optional;

public interface ProductService extends Service{
    List<Product> getProducts();

}
