package com.lab.onlineshop.ui.sales;

import com.lab.onlineshop.model.Product;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.ui.EventsForms;
import com.lab.onlineshop.ui.RegisterForm;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.PostLoad;
import jakarta.persistence.SecondaryTable;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named(value = "sales")
public class SalesForm extends EventsForms {

    @Inject
    private ProductService productService;

    private List<Product> productList;

    @PostConstruct
    public void loadData(){
        productList = productService.getProducts();
    }

    public List<Product> getProductList() {
        return productList;
    }
}
