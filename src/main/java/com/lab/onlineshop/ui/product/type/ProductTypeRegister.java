package com.lab.onlineshop.ui.product.type;

import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.EventsForms;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Named
@ViewScoped
public class ProductTypeRegister extends RegisterForm<ProductType, ProductTypeService> {

    @Override
    public List<ProductType> getEntitiesFromDataBase() {
        return getService().getProductsType();
    }
}
