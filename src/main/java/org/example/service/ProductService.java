package org.example.service;

import org.example.entity.Product;

import java.sql.SQLException;
import java.util.List;

import static org.example.service.ProductsJdbcDemo.getProductsFromDataBase;

public class ProductService {

    public void save(Product product) {
        System.out.println(product.toString());
    }

    public static List<Product> listAll() throws SQLException {
        return getProductsFromDataBase();
    }

}
