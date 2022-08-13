package com.lab.onlineshop.api.util;

import com.lab.onlineshop.model.AbstractEntity;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UISelectItems;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.List;
import java.util.function.Predicate;

@FacesConverter(value = "SelectItemEntityConverter")
public class UIEntityConverter<T extends AbstractEntity> implements Converter<T> {

    @Override
    public T getAsObject(FacesContext facesContext, UIComponent uiComponent, String id) {
        if(id == null || id.isEmpty()){
            return null;
        }
        return getSelectedItemAsEntity(uiComponent, Long.parseLong(id));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, T entity) {
        if(entity == null){
            return "";
        }
        return entity.getId().toString();
    }

    private T getSelectedItemAsEntity(UIComponent comp, Long entityId) {
        for (UIComponent uiComponent : comp.getChildren()) {
            if (uiComponent instanceof UISelectItems uiSelectItems) {
                List<T> selectItems = (List<T>) uiSelectItems.getValue();
                if (entityId != null && selectItems != null && !selectItems.isEmpty()) {
                    Predicate<T> predicate = entity -> entity.getId().equals(entityId);
                    return selectItems.stream().filter(predicate).findFirst().orElse(null);
                }
            }
        }
        return null;
    }
}


