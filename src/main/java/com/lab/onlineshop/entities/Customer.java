package com.lab.onlineshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer extends AbstractPerson {

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CustomerLevel customerLevel= CustomerLevel.STANDARD;

    @Column()
    private String address;

    @Column()
    private String phoneNumber;
}
