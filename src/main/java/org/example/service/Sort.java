package org.example.service;

import org.example.entity.Product;

import java.util.Comparator;
import java.util.List;

public class Sort{

    public static List<Product> sortListByPriceAscending(List<Product> products){
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    public static List<Product> sortListByPriceDescending(List<Product> products){
        products.sort(Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    public static List<Product> sortListByDateAscending(List<Product> products){
        products.sort(Comparator.comparing(Product::getDateTime).reversed());
        return products;
    }

    public static List<Product> sortListByDateDescending(List<Product> products){
        products.sort(Comparator.comparing(Product::getDateTime));
        return products;
    }
}
