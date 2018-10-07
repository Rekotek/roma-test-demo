package com.roma.repositories;

import com.roma.entities.ProductEntity;
import com.roma.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by taras on 2018-10-03.
 */

@Repository
public class ProductDaoImpl implements ProductDao {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ProductEntity> productNameExists(Product product) {
        TypedQuery<ProductEntity> namedQuery = entityManager.createQuery("select p from ProductEntity p where p.name " +
                "= :name", ProductEntity.class);
        namedQuery.setParameter("name", product.getName());
        return namedQuery.getResultList();
    }

}
