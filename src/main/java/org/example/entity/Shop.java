package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {

    private List<Product> productList = new ArrayList<>();

    @Override
    public String toString(){
        StringBuilder tableString = new StringBuilder("|| ");
        for (Product product: productList) {
            tableString.append(product.getId()).append(". ").append(product.getName()).append(" | ").append(product.getPrice()).append(" | ").append(product.getDateTime()).append(" || ").append(System.lineSeparator());
        }
        return tableString.toString();
    }
}