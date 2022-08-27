package com.lab.onlineshop.model.cart;

import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.model.customer.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Cart extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private Set<ItemCart> productList;
}
