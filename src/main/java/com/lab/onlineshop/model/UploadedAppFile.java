package com.lab.onlineshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UploadedAppFile extends AbstractEntity{

    @Lob
    @Column(columnDefinition = "BLOB", length=100000)
    private byte[] data;

    @Column()
    private String name;

    @Column()
    private String mime;

}
