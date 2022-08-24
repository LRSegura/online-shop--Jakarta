package com.lab.onlineshop.ui.product.type;

import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@ViewScoped
public class ProductTypeRegister extends RegisterForm<ProductType, ProductTypeService> {

    @Transactional
    public void saveProductType(){
        if(saveWithValidation(getFormEntity(),"Product type Created")){
            setFormEntity(new ProductType());
        }
    }

    @Transactional
    public void deleteSelectedProductsType(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "Product type removed" : "Products type removed";
        showInformationMessage(message);
    }

    public void clearFields(){
        getFormEntity().setDescription(null);
        getFormEntity().setRegisterDate(null);
    }

    @Override
    public List<ProductType> getEntitiesFromDataBase() {
        return getFormEntityService().getProductsType();
    }
}
