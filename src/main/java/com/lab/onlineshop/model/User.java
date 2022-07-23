package com.lab.onlineshop.model;

import com.lab.onlineshop.api.annotations.Description;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends AbstractPerson{

    @Description(value = "User Level")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserLevel userLevel = UserLevel.USER;

    @Description(value = "Is Active")
    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;
}
