package com.lab.onlineshop.api.util;

import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.model.UploadedAppFile;
import jakarta.servlet.http.HttpServletResponse;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UtilClass {

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

    public static boolean downloadFile(FacesContext facesContext, UploadedAppFile uploadedAppFile){
        if(uploadedAppFile == null) {
            return false;
        }
        byte[] image = uploadedAppFile.getData();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=" + uploadedAppFile.getName());
        response.setContentLength(image.length);
        response.setContentType(uploadedAppFile.getMime());
        try {
            response.getOutputStream().write(image);
        } catch (IOException e) {
            return false;
        }
        facesContext.responseComplete();
        facesContext.renderResponse();
        return true;
    }

}
