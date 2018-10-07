package com.roma.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by taras on 2018-10-03.
 */

public class Product {
    private Long id;

    private String name;

    private Set<ProductIdentifier> productIdentifiers = new HashSet<>();

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

    public Set<ProductIdentifier> getProductIdentifiers() {
        return productIdentifiers;
    }

    public void setProductIdentifiers(Set<ProductIdentifier> productIdentifiers) {
        this.productIdentifiers = productIdentifiers;
    }
}
