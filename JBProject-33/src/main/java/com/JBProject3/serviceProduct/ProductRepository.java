package com.JBProject3.serviceProduct;

import com.JBProject3.modelProduct.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByPriceLessThan(Double settingPrice);

//    @Query(value = "update table_product t set t.type =:newType where t.id = :id ", nativeQuery = true)
//    //обязательно указание нативного(родного для БД) запроса = true
//    Product updateType(@Param(value = "id") int id,
//                                 @Param(value = "newType") String newType);
}

