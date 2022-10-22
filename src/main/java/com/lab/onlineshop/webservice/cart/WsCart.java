package com.lab.onlineshop.webservice.cart;

import com.lab.onlineshop.model.customer.Customer;
import com.lab.onlineshop.services.cart.CartService;
import com.lab.onlineshop.services.dao.Dao;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/")
public class WsCart {

    @EJB
    private Dao dao;

    @Inject
    private CartService cartService;

    @GET
    @Path("cart/by/customer")
    @Produces(value = "text/json")
    public String getCartByCustomer(Long idCustomer){
        Customer customer = dao.getEntity(Customer.class, idCustomer);
        cartService.getCartByCustomer(customer);
        return null;
    }

}
