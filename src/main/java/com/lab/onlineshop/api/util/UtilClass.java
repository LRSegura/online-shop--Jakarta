package com.lab.onlineshop.api.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UtilClass {

    private UtilClass(){

    }

    public static  <T> Set<Field> getFieldsFromEntity(T entity){
        Class<?> entityClass = entity.getClass();
        Set<Field> fields = Arrays.stream(entityClass.getDeclaredFields()).collect(Collectors.toSet());
        Class<?> entityClassParent = entityClass.getSuperclass();
        while(!entityClassParent.equals(Object.class)){
            fields.addAll(Arrays.stream(entityClassParent.getDeclaredFields()).collect(Collectors.toSet()));
            entityClassParent = entityClassParent.getSuperclass();
        }
        return fields;
    }
}
