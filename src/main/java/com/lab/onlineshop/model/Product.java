package com.lab.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product extends AbstractEntity{

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductType productType;

    @Column(nullable = false)
    private Boolean isAvailable;

    @Column(nullable = false)
    private LocalDate registerDate = LocalDate.now();

}
