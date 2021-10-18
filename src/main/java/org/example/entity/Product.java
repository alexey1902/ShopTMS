package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Product implements Serializable {
    private int id;
    private int price;
    private String name;
    private String dateTime;

    public Product(int id, int price, String name) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        this.id = id;
        this.price = price;
        this.name = name;
        this.dateTime = dtf.format(LocalDateTime.now());
    }

    public Product(String name, int price, int id, String dateTime) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.dateTime = dateTime;
    }

}
