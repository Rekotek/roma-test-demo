package com.roma.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by taras on 2018-10-03.
 */
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ProductName")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductIdentifierEntity> productIdentifiers = new HashSet<>();

    public ProductEntity() {
    }

    public ProductEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductIdentifierEntity> getProductIdentifiers() {
        return productIdentifiers;
    }

    public void setProductIdentifiers(Set<ProductIdentifierEntity> productIdentifiers) {
        this.productIdentifiers = productIdentifiers;
    }
}
