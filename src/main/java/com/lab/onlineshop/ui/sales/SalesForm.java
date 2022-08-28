package com.lab.onlineshop.ui.sales;

import com.lab.onlineshop.model.cart.Cart;
import com.lab.onlineshop.model.cart.ItemCart;
import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.model.product.ProductRecord;
import com.lab.onlineshop.services.cart.CartService;
import com.lab.onlineshop.services.product.ProductService;
import com.lab.onlineshop.ui.EventsForms;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named(value = "sales")
public class SalesForm extends EventsForms {

    @Inject
    private ProductService productService;

    @Inject
    private CartService cartService;

    private Cart cart;

    List<ProductRecord> productRecords;

    @PostConstruct
    public void loadData(){
        productRecords =getProductsForSale();
        if(loggedCustomer()){
            this.cart = cartService.getCartByCustomer(getContext().getLoggedCustomer().orElse(null));
        }
    }

    public List<ProductRecord> getProductRecords() {
        return productRecords;
    }

    public void addToCart(Long idProduct){
       Product product = getDao().getEntity(Product.class, idProduct);
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

    private List<ProductRecord> getProductsForSale(){
        String urlBase ="http://localhost:8080/Online-Shop/webapi";
        Client client = ClientBuilder.newClient();
        String jsonString = client.target(urlBase).path("/products/for/sales").request().get(String.class);
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(jsonString, new ArrayList<ProductRecord>(){}.getClass().getGenericSuperclass());
    }

}
