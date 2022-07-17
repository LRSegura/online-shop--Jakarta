package com.lab.onlineshop.model;

import com.lab.onlineshop.api.Description;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product extends AbstractEntity{

    @Description(value = "Available Quantity")
    @Column(nullable = false)
    private Integer availableQuantity;

    @Description(value = "Price")
    @Column(nullable = false)
    private BigDecimal price;

    @Description(value = "Product Type")
    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductType productType;

    @Description(value = "Is Available")
    @Column(nullable = false)
    private Boolean isAvailable;

    @Description(value = "Register Date")
    @Column(nullable = false)
    private LocalDate registerDate = LocalDate.now();

}
