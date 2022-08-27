package com.lab.onlineshop.api.persistence.validations;

import com.lab.onlineshop.api.annotations.InjectedDate;
import com.lab.onlineshop.api.util.UtilClass;
import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.model.product.Stock;
import jakarta.persistence.PrePersist;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class HibernateEventHandlers {

    @PrePersist
    void prePersist(Object entity){
        injectDate(entity);
        injectProductStock(entity);
    }

    private void injectDate(Object entity){
        for (Field field: UtilClass.getFieldsFromEntity(entity)) {
            InjectedDate injectedDate = field.getAnnotation(InjectedDate.class);
            if(injectedDate != null){
                try {
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    if(value != null && !(value instanceof LocalDate)){
                        throw new IllegalArgumentException("The type of the field for the annotation InjectedDate should be LocalDate");
                    }
                    field.set(entity, LocalDate.now());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Injected Date Error");
                }
            }
        }
    }

    private void injectProductStock(Object entity){
       if(entity instanceof Product product){
           product.setStock(Stock.getStock(product.getAvailableQuantity()));
       }
    }

}
