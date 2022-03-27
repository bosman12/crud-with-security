package com.example.spring_example.model;

import com.sun.istack.NotNull;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "text",nullable = false)
    private String description;
    @Column(columnDefinition = "bigint",nullable = false)
    private Integer price;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String author;

    private final LocalDateTime dateOfCreated = LocalDateTime.now();

    @Lob
    @Column(nullable = false)
    private byte[] bytesImage;

    @PrePersist
    private void init() {
        LocalDateTime dateOfCreated = getDateOfCreated();
    }


}
