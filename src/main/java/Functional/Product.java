package Functional;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlType(propOrder = {"name","price","id","dateTime"}, name ="product")
@XmlRootElement
public class Product implements Serializable {
    private int id;
    private int price;
    private String name;
    private String dateTime;

    public Product(){}

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

    public int getId() { return id; }

    public int getPrice() { return price; }

    public String getName() { return name; }

    public String getDateTime() { return dateTime; }

    public void setPrice(int price) { this.price = price; }

    public void setName(String name) { this.name = name; }

    public void setId(int id) { this.id = id; }

    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public void show(){
        System.out.println("Наименование: " + this.getName());
        System.out.println("Цена: " + this.getPrice());
        System.out.println("ID: " + this.getId());
        System.out.println("Добавлен: " + this.getDateTime() + '\n');
    }

}