package com.roma.services;

import com.roma.entities.ProductEntity;
import com.roma.entities.ProductIdentifierEntity;
import com.roma.models.Product;
import com.roma.models.ProductIdentifier;
import com.roma.repositories.ProductDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by taras on 2018-10-03.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {
    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductServiceImpl(productDao);
    }

    @Test(expected = NullPointerException.class)
    public void throwNPEOnNullProduct() {
        when(productDao.productNameExists(null)).thenThrow(NullPointerException.class);
        Map<String, String> productNameExists = productService.productNameExists(null);
    }

    @Test
    public void checkProductNameCoincidence() {
        final String DUPLICATED_NAME = "Спец-имя";

        Product product = new Product();
        product.setName(DUPLICATED_NAME);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(DUPLICATED_NAME);
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productDao.productNameExists(product)).thenReturn(productEntities);

        Map<String, String> map = productService.productNameExists(product);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("name"));
        assertEquals(DUPLICATED_NAME, map.get("name"));
    }

    @Test
    public void checkProductNameNonCoincidence() {
        final String NAME_1 = "Спец-имя";
        final String NAME_2 = "Другое имя";
        Product product = new Product();
        product.setName(NAME_1);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(NAME_2);
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productDao.productNameExists(product)).thenReturn(productEntities);

        Map<String, String> map = productService.productNameExists(product);
        assertEquals(0, map.size());
    }

    @Test
    public void checkOneIdentifierAlreadyExistWithProductNameDifferent() {
        final String NAME_1 = "Спец-имя";
        final String NAME_2 = "Другое имя";
        final String IDENTIFIER_VALUE = "Это уже есть";
        Product product = new Product();
        product.setName(NAME_1);
        ProductIdentifier pi = new ProductIdentifier();
        pi.setProductIdentifier(IDENTIFIER_VALUE);
        Set<ProductIdentifier> identifierSet = new HashSet<>();
        identifierSet.add(pi);
        product.setProductIdentifiers(identifierSet);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(NAME_2);
        ProductIdentifierEntity otherIdentifier = new ProductIdentifierEntity();
        otherIdentifier.setProductIdentifier(IDENTIFIER_VALUE);
        Set<ProductIdentifierEntity> otherSet = new HashSet<>();
        otherSet.add(otherIdentifier);
        productEntity.setProductIdentifiers(otherSet);
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productDao.productNameExists(product)).thenReturn(productEntities);

        Map<String, String> map = productService.productNameExists(product);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("productIdentifier"));
        assertEquals(IDENTIFIER_VALUE, map.get("productIdentifier"));
    }

    @Test
    public void checkTwoIdentifierAlreadyExistWithProductNameDifferent() {
        final String NAME_1 = "Спец-имя";
        final String NAME_2 = "Другое имя";
        final String IDENTIFIER_VALUE_1 = "Это уже есть";
        final String IDENTIFIER_VALUE_2 = "И это есть";
        final String IDENTIFIER_VALUE_3 = "А это только у Product";
        Product product = new Product();
        product.setName(NAME_1);
        ProductIdentifier pi1 = new ProductIdentifier();
        pi1.setProductIdentifier(IDENTIFIER_VALUE_1);
        Set<ProductIdentifier> identifierSet = new HashSet<>();
        identifierSet.add(pi1);
        ProductIdentifier pi2 = new ProductIdentifier();
        pi2.setProductIdentifier(IDENTIFIER_VALUE_2);
        identifierSet.add(pi2);
        ProductIdentifier pi3 = new ProductIdentifier();
        pi3.setProductIdentifier(IDENTIFIER_VALUE_3);
        identifierSet.add(pi3);

        product.setProductIdentifiers(identifierSet);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(NAME_2);
        ProductIdentifierEntity otherIdentifier1 = new ProductIdentifierEntity();
        otherIdentifier1.setProductIdentifier(IDENTIFIER_VALUE_2);
        Set<ProductIdentifierEntity> otherSet = new HashSet<>();
        otherSet.add(otherIdentifier1);
        ProductIdentifierEntity otherIdentifier2 = new ProductIdentifierEntity();
        otherIdentifier2.setProductIdentifier(IDENTIFIER_VALUE_1);
        otherSet.add(otherIdentifier2);
        productEntity.setProductIdentifiers(otherSet);
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(productEntity);

        when(productDao.productNameExists(product)).thenReturn(productEntities);

        Map<String, String> map = productService.productNameExists(product);
        assertEquals(1, map.size());
        assertTrue(map.containsKey("productIdentifier"));
        String longStringFromMap = map.get("productIdentifier");
        assertTrue(longStringFromMap.contains(IDENTIFIER_VALUE_1));
        assertTrue(longStringFromMap.contains(IDENTIFIER_VALUE_2));
        System.out.println(longStringFromMap);
    }


}