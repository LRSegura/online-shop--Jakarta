package com.lab.onlineshop.services.sales;

import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

public class SalesServiceImplementation implements SalesService{
    @EJB
    private Dao dao;

    @Override
    public Dao getDao() {
        return dao;
    }

    @Override
    public EntityManager getEntityManager() {
        return dao.getEntityManager();
    }
}
