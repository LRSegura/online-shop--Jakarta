package com.lab.onlineshop.model;

import com.lab.onlineshop.api.Description;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProductType extends AbstractEntity{

    @Column(nullable = false, unique = true)
    @Description(value = "Description")
    private String description;
}
