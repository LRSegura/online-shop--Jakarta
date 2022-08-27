package com.lab.onlineshop.ui.sales;

import com.lab.onlineshop.model.cart.Cart;
import com.lab.onlineshop.model.cart.ItemCart;
import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.services.cart.CartService;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.ui.EventsForms;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ViewScoped
@Named(value = "sales")
public class SalesForm extends EventsForms {

    @Inject
    private ProductService productService;

    @Inject
    private CartService cartService;

    private Cart cart;

    private List<Product> productList;

    @PostConstruct
    public void loadData(){
        productList = productService.getProducts();
        if(loggedCustomer()){
            this.cart = cartService.getCartByCustomer(getContext().getLoggedCustomer().orElse(null));
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addToCart(Product product){
       boolean isProductExist = cartService.getItemByProduct(product,cart).isPresent();
       if(isProductExist){
           showWarningMessage("This product is already in your cart");
           return;
       }
        this.cart = cartService.getCartByCustomer(getContext().getLoggedCustomer().orElse(null));
            ItemCart itemCart = new ItemCart();
            itemCart.setCart(this.cart);
            itemCart.setProduct(product);
            saveEntity(itemCart);
            showInformationMessage("Item added to cart");
    }

    public boolean loggedCustomer(){
        return getContext().isLoggedCustomer();
    }

    public Long getAmountItemsInCart(){
        return cartService.getAmountItemsInCart(this.cart);
    }

}
