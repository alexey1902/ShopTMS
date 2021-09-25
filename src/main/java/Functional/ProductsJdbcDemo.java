package Functional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Functional.Methods.getIntNumber;
import static Functional.Methods.writeHistory;

public class ProductsJdbcDemo {
    private static final String DATABASE_URL ="jdbc:mysql://localhost:3306/store?" + "allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&serverTimeZone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "serveraaasql964385431";

   public static void showDatabaseTable() throws SQLException {

        String sql = "SELECT * FROM products";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("\n====================");
            while (resultSet.next()){
                System.out.println("Название: " + resultSet.getString("name"));
                System.out.println("Цена: " + resultSet.getInt("price"));
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Время добавления: " + resultSet.getString("time"));
                System.out.println("====================");
            }
        }
    }

    public static void deleteFromTable() throws SQLException {
        System.out.println("Введите ID товара: ");
        int idForDelete = getIntNumber();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM products WHERE id = " + idForDelete + ";";
            statement.executeUpdate(sql);
            System.out.println("Closing connection...");
        }
    }

    public static void deleteAll() throws SQLException {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM products;";
            statement.executeUpdate(sql);
            System.out.println("Closing connection...");
        }
    }

    public static void addProduct(Product product) throws SQLException {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO products (name, price, id, time) VALUES ('"+product.getName()+"'," +product.getPrice()+"," +product.getId()+",'" + product.getDateTime()+"');";
            statement.executeUpdate(sql);
        }
    }

    public static void fillTable(List<Product> productList) throws SQLException{

        deleteAll();

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            for (Product product: productList) {
                String sql = "INSERT INTO products (name, price, id, time) VALUES ('"+product.getName()+"'," +product.getPrice()+"," +product.getId()+",'" + product.getDateTime()+"');";
                statement.executeUpdate(sql);
            }
            System.out.println("Closing connection...");
        }
    }

    public static List<Product> getList() throws SQLException {

        List<Product> storeProducts = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                storeProducts.add(new Product(resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("id"),
                        resultSet.getString("time")));
            }
        }
       return storeProducts;
    }

    public static void buyProduct() throws SQLException{
        System.out.println("Введите ID товара: ");
        int idForDelete = getIntNumber();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM products WHERE id = " + idForDelete;
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                Product boughtProduct = new Product(resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("id"),
                        resultSet.getString("time"));
                writeHistory(boughtProduct);
            }
            sql = "DELETE FROM products WHERE id = " + idForDelete + ";";
            statement.executeUpdate(sql);
            System.out.println("Closing connection...");
        }
    }

}
