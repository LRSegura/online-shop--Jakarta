package com.lab.onlineshop.api.util;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import com.lab.onlineshop.api.exceptions.EntityFieldDeclarationException;
import com.lab.onlineshop.api.exceptions.EntityFieldException;
import com.lab.onlineshop.api.exceptions.EntityFieldValueException;
import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.model.UploadedAppFile;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpServletResponse;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UtilClass {

    public static <T extends AbstractEntity> void validateEntityField(T entity) throws EntityFieldException {
        validateEntityFieldDeclaration(entity);
        validateEntityFieldValue(entity);
    }
    public static <T extends AbstractEntity> void validateEntityFieldValue(T entity) throws EntityFieldException {
        EntityFieldValueException validationException = new EntityFieldValueException();
        BiConsumer<Field,EntityFieldException> consumer = (field , exception) -> {
            Description description = field.getAnnotation(Description.class);
            String messageError;
            InjectedDate injectedDate = field.getAnnotation(InjectedDate.class);
            if(injectedDate == null){
                messageError = String.format("The field '%s' is empty", description.value());
                exception.getSpecificErrorList().add(messageError);
            }
        };
        validateEntityField(entity,consumer,validationException);
    }
    public static <T extends AbstractEntity> void validateEntityFieldDeclaration(T entity) throws EntityFieldException {
        EntityFieldDeclarationException declarationException = new EntityFieldDeclarationException();
        BiConsumer<Field,EntityFieldException> consumer = (field , exception) -> {
            Description description = field.getAnnotation(Description.class);
            String messageError;
            if(description == null){
                messageError= String.format("The field '%s' from the entity '%s' should have the Description Annotation",
                        field.getName(), entity.getClass().getName());
                exception.getSpecificErrorList().add(messageError);
            }
        };
        validateEntityField(entity,consumer,declarationException);
    }

    private static <T extends AbstractEntity> void validateEntityField(T entity, BiConsumer<Field,EntityFieldException> consumer,
                                                                      EntityFieldException exception) throws EntityFieldException {
        for (Field field : getFieldsFromEntity(entity)){
            Column column = field.getAnnotation(Column.class);
            if(column != null && !column.name().equalsIgnoreCase("id") && !column.nullable()){
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    if(value == null || (value instanceof String stringValue && stringValue.isEmpty())) {
                        consumer.accept(field, exception);
                    }
                } catch (IllegalAccessException illegalAccessException) {
                    String errorMessage = String.format("Reflection error during Field validation in the class: %s ",
                            entity.getClass().getName());
                    throw new RuntimeException(errorMessage);
                }
            }
        }
        exception.throwIfThereIsSpecificError();
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
