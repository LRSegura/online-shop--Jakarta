package com.lab.onlineshop.model;

import com.lab.onlineshop.services.dao.Dao;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

public interface Service extends Serializable {

    Dao getDao();

    EntityManager getEntityManager();
}
