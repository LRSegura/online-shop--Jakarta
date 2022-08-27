package com.lab.onlineshop.services.product.type;

import com.lab.onlineshop.model.product.ProductType;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.List;

public final class ProductTypeImplementation implements ProductTypeService {
    @EJB
    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }
    @Override
    public List<ProductType> getProductsType() {
        return getEntityManager().createQuery("FROM ProductType order by registerDate", ProductType.class).getResultList();
    }
}
