package com.lab.onlineshop.model;

import com.lab.onlineshop.api.annotations.Description;
import com.lab.onlineshop.api.annotations.InjectedDate;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@MappedSuperclass
public abstract class AbstractPerson extends AbstractEntity{

    @Description(value = "First Name")
    @Column(nullable = false)
    private String firstName;

    @Description(value = "Last Name")
    @Column(nullable = false)
    private String lastName;

    @Description(value = "Password")
    @Column(nullable = false)
    private String password;

    @Description(value = "Email")
    @Column(nullable = false)
    private String email;

    @InjectedDate
    @Description(value = "Register Date")
    @Column(nullable = false)
    private LocalDate registerDate;
}
