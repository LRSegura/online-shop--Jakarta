package com.lab.onlineshop.model;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class Sale extends AbstractEntity{

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "sale")
    private Set<ItemSale> itemSaleSet;

    @InjectedDate
    @Column(nullable = false)
    private LocalDate registerDate;

    @Column(nullable = false)
    private BigDecimal total;
}
