package com.lab.onlineshop.services.product;

import com.lab.onlineshop.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProductImplementation implements ProductService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> getProducts() {
        return entityManager.createQuery("FROM Product order by registerDate", Product.class).getResultList();
    }
}
