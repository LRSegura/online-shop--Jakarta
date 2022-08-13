package com.lab.onlineshop.model;

import com.lab.onlineshop.api.persistence.validations.HibernateEventHandlers;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@MappedSuperclass
@EntityListeners(HibernateEventHandlers.class)
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    //TODO add lombok and sealed class

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
