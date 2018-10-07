package com.roma.entities;

import javax.persistence.*;

/**
 * Created by taras on 2018-10-03.
 */
@Entity
public class ProductIdentifierEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ProductIdentifier_Product_ID")
    private ProductEntity productEntity;

    @Column(name = "ProductIdentifierEntity")
    private String productIdentifier;

    public ProductIdentifierEntity() {
    }

    public ProductIdentifierEntity(ProductEntity productEntity, String productIdentifier) {
        this.productEntity = productEntity;
        this.productIdentifier = productIdentifier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }
}
