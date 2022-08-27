package com.lab.onlineshop.model;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ItemCart extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @InjectedDate
    @Column(nullable = false)
    private LocalDate registerDate;

}
