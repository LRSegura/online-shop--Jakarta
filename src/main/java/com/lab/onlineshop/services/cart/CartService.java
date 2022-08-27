package com.lab.onlineshop.services.cart;

import com.lab.onlineshop.model.*;

import java.util.Optional;

public interface CartService extends Service {
    Cart getCartByCustomer(Customer customer);

    Long getAmountItemsInCart(Cart customer);

    Optional<ItemCart> getItemByProduct(Product product);
}
