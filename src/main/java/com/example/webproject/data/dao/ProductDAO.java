package com.example.webproject.data.dao;

import com.example.webproject.data.entity.Product;
import org.springframework.stereotype.Repository;

public interface ProductDAO {

    Product insertProduct(Product product);

    Product selectProduct(Long number);

    Product updateProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
