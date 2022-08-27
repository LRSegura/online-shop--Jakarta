package com.lab.onlineshop.services.product;

import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.List;

public final class ProductImplementation implements ProductService {

    @EJB
    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }
    @Override
    public List<Product> getProducts() {
        return getEntityManager().createQuery("FROM Product order by registerDate", Product.class).getResultList();
    }
}
