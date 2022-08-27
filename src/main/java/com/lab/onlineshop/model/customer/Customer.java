package com.lab.onlineshop.model.customer;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.model.AbstractPerson;
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

    @Description(value = "Customer Level")
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CustomerLevel customerLevel= CustomerLevel.STANDARD;

    @Description(value = "Address")
    @Column()
    private String address;

    @Description(value = "Phone Number")
    @Column()
    private String phoneNumber;
}
