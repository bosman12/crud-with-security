package com.example.spring_example.model;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "bigint")
    private Integer price;

    private String city;

    private String author;



}
