package com.lab.onlineshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductType extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String description;
}
