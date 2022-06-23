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
public class User extends AbstractPerson{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserLevel userLevel = UserLevel.USER;

    @Column(nullable = false)
    private Boolean isActive = Boolean.TRUE;
}
