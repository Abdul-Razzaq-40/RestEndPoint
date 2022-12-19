package com.example.springassignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @OneToOne
    @JoinColumn(name="product_id")
    Product product;

    String batch;
    int stock,deal,free;
    float mrp, rate;
    Date expire;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="supplier_id")
    Supplier supplier;
}
