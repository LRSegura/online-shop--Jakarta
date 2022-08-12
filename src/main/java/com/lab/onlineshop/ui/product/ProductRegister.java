package com.lab.onlineshop.ui.product;

import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.model.ProductType;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.services.product.type.ProductTypeService;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Named
@ViewScoped
public class ProductRegister extends RegisterForm<Product, ProductService> {


    @Inject
    private ProductTypeService productTypeService;

    @Transactional
    public void saveProduct(){
        if(saveWithValidation(getEntity(),"Product type Created")){
            setEntity(new Product());
        }
    }

    @Transactional
    public void deleteSelectedProducts(){
        getEntitiesSelected().forEach(this::deleteEntity);
        String message = getEntitiesSelected().size() == 1 ? "Product removed" : "Products removed";
        showInformationMessage(message);
    }

    public void clearFields(){
        getEntity().setAvailableQuantity(null);
        getEntity().setProductType(null);
        getEntity().setRegisterDate(null);
        getEntity().setIsAvailable(false);
        getEntity().setPrice(BigDecimal.ZERO);
    }


    @Override
    public List<Product> getEntitiesFromDataBase() {
        return getService().getProducts();
    }
    public List<ProductType> getProductsType(){
        return productTypeService.getProductsType();
    }

}
