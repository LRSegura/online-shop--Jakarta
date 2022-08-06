package com.lab.onlineshop.services.product.type;

import com.lab.onlineshop.model.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

public class ProductTypeImplementation implements ProductTypeService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ProductType> getProductsType() {
        return entityManager.createQuery("FROM ProductType order by registerDate", ProductType.class).getResultList();
    }
}
