package org.example.controller;

import org.example.entity.Shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

import static org.example.service.ProductsJdbcDemo.getProductsFromDataBase;
import static org.example.service.Sort.*;

@Controller
public class SortController {

    @GetMapping("/priceAscending")
    public String sortByPriceAscending(Model model) throws SQLException {
        Shop shop = new Shop(sortListByPriceAscending(getProductsFromDataBase()));
        model.addAttribute("listOfProducts",shop.toString());
        return "showProducts";
    }

    @GetMapping("/priceDescending")
    public String sortByPriceDescending(Model model) throws SQLException {
        Shop shop = new Shop(sortListByPriceDescending(getProductsFromDataBase()));
        model.addAttribute("listOfProducts",shop.toString());
        return "showProducts";
    }

    @GetMapping("/dateAscending")
    public String sortByDateAscending(Model model) throws SQLException {
        Shop shop = new Shop(sortListByDateDescending(getProductsFromDataBase()));
        model.addAttribute("listOfProducts",shop.toString());
        return "showProducts";
    }

    @GetMapping("/dateDescending")
    public String sortByDateDescending(Model model) throws SQLException {
        Shop shop = new Shop(sortListByDateAscending(getProductsFromDataBase()));
        model.addAttribute("listOfProducts",shop.toString());
        return "showProducts";
    }

}
