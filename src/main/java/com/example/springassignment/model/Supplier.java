package com.example.springassignment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "supplier",fetch=FetchType.EAGER)
    @JsonManagedReference
    @Builder.Default
    Set<Inventory> inventoryList=new HashSet<>();
    public void addInventoryItem(Inventory inventory){
        inventory.setSupplier(this);
        inventoryList.add(inventory);
    }
}
