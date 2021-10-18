package org.example.controller;

import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public ModelAndView home() throws SQLException {
        List<Product> listProducts = ProductService.listAll();
        ModelAndView mav = new ModelAndView("index.jsp");
        mav.addObject("listCustomer", listProducts);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @RequestMapping("/new")
    public String newProductForm(Map<String, Object> model) {
        Product product = new Product();
        model.put("customer", product);
        return "new_product.jsp";
    }
}
