package com.lab.onlineshop.services.cart;

import com.lab.onlineshop.model.cart.Cart;
import com.lab.onlineshop.model.customer.Customer;
import com.lab.onlineshop.model.cart.ItemCart;
import com.lab.onlineshop.model.product.Product;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public final class CartServiceImplementation implements CartService{
    @EJB
    private Dao dao;

    @Override
    public Dao getDao() {
        return dao;
    }

    @Override
    public EntityManager getEntityManager() {
        return dao.getEntityManager();
    }

    @Override
    public Cart getCartByCustomer(Customer customer) {
        return getEntityManager().createQuery("from Cart where customer = :customer", Cart.class)
                .setParameter("customer", customer).getSingleResult();
    }

    @Override
    public Long getAmountItemsInCart(Cart cart) {
        return getEntityManager().createQuery("select count(*) from ItemCart where cart = :cart", Long.class)
                .setParameter("cart", cart).getSingleResult();

    }

    @Override
    public Optional<ItemCart> getItemByProduct(Product product, Cart cart) {
        return getEntityManager().createQuery("from ItemCart where product = :product and cart = :cart", ItemCart.class)
                .setParameter("product", product)
                .setParameter("cart", cart)
                .getResultList().stream().findFirst();

    }
}
