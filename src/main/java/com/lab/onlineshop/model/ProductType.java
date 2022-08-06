package com.lab.onlineshop.model;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ProductType extends AbstractEntity{

    @Column(nullable = false, unique = true)
    @Description(value = "Description")
    private String description;

    @InjectedDate
    @Description(value = "Register Date")
    @Column(nullable = false)
    private LocalDate registerDate;
}
