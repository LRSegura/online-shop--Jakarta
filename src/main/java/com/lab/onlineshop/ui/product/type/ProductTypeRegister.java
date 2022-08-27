package com.lab.onlineshop.ui.product.type;

import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@ViewScoped
public class ProductTypeRegister extends RegisterForm<ProductType> {

    @Inject
    private ProductTypeService service;

    @Transactional
    public void saveProductType(){
        if(saveWithValidation(getFormEntity(),"Product type Created")){
            setFormEntity(new ProductType());
        }
    }

    @Transactional
    public void deleteSelectedProductsType(){
        String message = getEntitiesSelected().size() == 1 ? "Product type removed" : "Products type removed";
        deleteSelectedEntities(message);
    }

    public void clearFields(){
        getFormEntity().setDescription(null);
        getFormEntity().setRegisterDate(null);
    }

    @Override
    public List<ProductType> getEntitiesFromDataBase() {
        return service.getProductsType();
    }
}
