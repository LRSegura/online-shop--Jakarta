package com.lab.onlineshop.model;

import com.lab.onlineshop.api.persistence.validations.HibernateEventHandlers;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;


@Getter
@MappedSuperclass
@EntityListeners(HibernateEventHandlers.class)
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //TODO add lombok and sealed class
}
