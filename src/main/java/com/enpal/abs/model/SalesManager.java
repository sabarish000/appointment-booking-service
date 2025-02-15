package com.enpal.abs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sales_managers")
public class SalesManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "languages", columnDefinition = "varchar[]")
    private List<String> languages; // Maps the PostgreSQL array column "languages"

    @Column(name = "products", columnDefinition = "varchar[]")
    private List<String> products; // Maps the PostgreSQL array column "products"

    @Column(name = "customer_ratings", columnDefinition = "varchar[]")
    private List<String> customerRatings; // Maps the PostgreSQL array column "customer_ratings"

}
