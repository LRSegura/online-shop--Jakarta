package com.lab.onlineshop.services.cart;

import com.lab.onlineshop.model.*;
import com.lab.onlineshop.model.cart.Cart;
import com.lab.onlineshop.model.cart.ItemCart;
import com.lab.onlineshop.model.customer.Customer;
import com.lab.onlineshop.model.product.Product;

import java.util.Optional;

public sealed interface CartService extends Service permits CartServiceImplementation  {
    Cart getCartByCustomer(Customer customer);

    Long getAmountItemsInCart(Cart customer);

    Optional<ItemCart> getItemByProduct(Product product, Cart cart);
}
