package com.roma.repositories;

import com.roma.entities.ProductEntity;
import com.roma.models.Product;

import java.util.List;

/**
 * Created by taras on 2018-10-03.
 */
public interface ProductDao {
    List<ProductEntity> productNameExists(Product product);
}
