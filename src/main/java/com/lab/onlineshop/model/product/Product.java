package com.lab.onlineshop.model.product;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import com.lab.onlineshop.model.AbstractEntity;
import com.lab.onlineshop.model.UploadedAppFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @Description(value = "Description")
    private String description;

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

    @InjectedDate
    @Description(value = "Register Date")
    @Column(nullable = false)
    private LocalDate registerDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "id_file")
    private UploadedAppFile file;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Stock stock;
}
