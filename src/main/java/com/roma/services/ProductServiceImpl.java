package com.roma.services;

import com.roma.entities.ProductEntity;
import com.roma.entities.ProductIdentifierEntity;
import com.roma.models.Product;
import com.roma.models.ProductIdentifier;
import com.roma.repositories.ProductDao;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by taras on 2018-10-03.
 */

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Map<String, String> productNameExists(Product product) {
        List<ProductEntity> productEntities = productDao.productNameExists(product);
        Map<String, String> duplicateEntries = new HashMap<>();
        if (productEntities.size() != 0) {
            Optional<ProductEntity> productEntityOptional = productEntities.stream()
                    .filter(p -> p.getName()
                            .equals(product.getName()))
                    .findFirst();
            if (productEntityOptional.isPresent()) {
                duplicateEntries.put("name", product.getName());
            }
            List<String> coincidencesList =
                    new ArrayList<>();
            for (ProductEntity entity : productEntities) {
                for (ProductIdentifierEntity identifierEntity : entity.getProductIdentifiers()) {
                    String entityName = identifierEntity.getProductIdentifier();
                    for (ProductIdentifier pi : product.getProductIdentifiers()) {
                        String productIdentifier = pi.getProductIdentifier();
                        if (entityName.equals(productIdentifier)) {
                            coincidencesList.add(entityName);
                        }
                    }
                }
            }
//
//                    productEntities
//                    .stream()
//                    .map(i -> i.getProductIdentifiers())
//                    .flatMap(i -> i.stream())
//                    .map(pie -> pie.getProductIdentifier())
//                    .filter(p -> product.getProductIdentifiers().contains(p))
//                    .collect(Collectors.toList());
            if (!coincidencesList.isEmpty()) {
//                duplicateEntries.put("productIdentifier", coincidencesList.toString());
                duplicateEntries.put("productIdentifier", String.join(", ", coincidencesList));
            }
        }
        return duplicateEntries;
    }
}
