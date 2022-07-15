package com.lab.onlineshop.model;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //TODO add lombok and sealed class
}
