package com.roma.repositories;

import com.roma.entities.ProductEntity;
import com.roma.models.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by taras on 2018-10-07.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = ProductDaoImplTestConfig.class)
public class ProductDaoImplTest {
    private static final String NAME_1 = "ИМЯ_1";
    private static final String NAME_2 = "ИМЯ_2";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductDao productDao;

    private ProductEntity productEntity1;
    private ProductEntity productEntity2;

    @Before
    public void setUp() {
        productEntity1 = new ProductEntity();
        productEntity1.setName(NAME_1);
        entityManager.persist(productEntity1);

        productEntity2 = new ProductEntity();
        productEntity2.setName(NAME_2);
        entityManager.persist(productEntity2);
    }

    @Test
    public void dataBaseWorks() {
        List<ProductEntity> productEntityList =
                entityManager.getEntityManager()
                        .createQuery("select p from ProductEntity p", ProductEntity.class)
                        .getResultList();
        assertEquals(2, productEntityList.size());
        assertTrue(productEntity1.getId() != 0);
        assertTrue(productEntity2.getId() != 0);
        assertNotSame("ID should be different", productEntity1.getId(), productEntity2.getId());
    }

    @Test
    public void productNameNotExists() {
        Product product = new Product();
        product.setName("Ку-ку");
        List<ProductEntity> entityList = productDao.productNameExists(product);
        assertNotNull(entityList);
        assertEquals(0, entityList.size());
    }

    @Test
    public void productNameExist() {
        Product product = new Product();
        product.setName(NAME_2);
        List<ProductEntity> entityList = productDao.productNameExists(product);
        assertNotNull(entityList);
        assertEquals(1, entityList.size());
    }
}