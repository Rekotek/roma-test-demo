package com.roma.services;

import com.roma.models.Product;

import java.util.Map;

/**
 * Created by taras on 2018-10-03.
 */

public interface ProductService {
    Map<String, String> productNameExists(Product product);
}
