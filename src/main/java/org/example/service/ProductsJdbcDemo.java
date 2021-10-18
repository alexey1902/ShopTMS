package org.example.service;

import org.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsJdbcDemo {
    private static final String DATABASE_URL ="jdbc:mysql://localhost:3306/store?" + "allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&serverTimeZone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "serveraaasql964385431";

   public static List<Product> getProductsFromDataBase() throws SQLException {

        String sql = "SELECT * FROM products";

        List<Product> productsFromDataBase = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                productsFromDataBase.add(new Product(resultSet.getString("Name"),
                        resultSet.getInt("Price"),
                        resultSet.getInt("Id"),
                        resultSet.getString("Date")));
            }
        }
        return productsFromDataBase;
    }

    //@TODO
    public static void deleteProductFromDataBase() throws SQLException{

    }

    public static void addProductToDataBase() throws SQLException{

    }

}
