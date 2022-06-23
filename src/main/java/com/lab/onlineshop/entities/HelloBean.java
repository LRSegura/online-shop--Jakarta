package com.lab.onlineshop.entities;

import jakarta.ejb.TransactionAttribute;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@Named
@RequestScoped
public class HelloBean {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    private String name;
    private String greeting;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    @Transactional
    public void listener(){
        ProductType productType = new ProductType();
        productType.setDescription("Electronic");
        entityManager.persist(productType);

        Product product = new Product();
        product.setIsAvailable(true);
        product.setPrice(BigDecimal.ZERO);
        product.setAvailableQuantity(10);

        product.setProductType(productType);
        entityManager.persist(product);
        greeting = "hello " + product.getId();

    }
}
