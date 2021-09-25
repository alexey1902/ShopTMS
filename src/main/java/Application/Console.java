package Application;

import Functional.Shop;

import java.sql.SQLException;

import static Functional.Marshaller.unmarshall;
import static Functional.Methods.*;
import static Functional.ProdCons.imitate;
import static Functional.ProductsJdbcDemo.*;

public class Console {

    private static final String mainMenu = "\n" +
            "1 - Вывести товары\n" +
            "2 - Добавить товар\n" +
            "3 - Редактировать товар\n" +
            "4 - Удалить товар\n" +
            "5 - Создать отчёт\n" +
            "6 - Создать XML\n" +
            "7 - Считать XML\n" +
            "8 - Имитировать спрос\n" +
            "9 - Работа с базой данных\n" +
            "0 - Выход\n";

    private static final String sortMenu = "\n" +
            "Сортировать всё по:\n" +
            "1 - Цене(Возрастание)\n" +
            "2 - Цене(Убывание)\n" +
            "3 - Дате добавления(новые -> поздние)\n" +
            "4 - Вывод товаров в диапазоне\n" +
            "0 - Выход\n";

    private static final String databaseMenu = "\n" +
            "1 - Вывести товары со склада\n" +
            "2 - Добавить товары на склад\n" +
            "3 - Удалить\n" +
            "4 - Удалить все товары со склада\n" +
            "5 - Купить товар\n" +
            "6 - Считать список товаров со склада\n" +
            "7 - Добавить товар на склад\n"+
            "0 - Выход\n";

    private static final String reportFilePath = "src/main/java/Files/report.txt";

    public static void startSortMenu(Shop shop){
        boolean exit = false;
        while (!exit) {
           exit = shop.sort (checkNumber(sortMenu, 4));
        }
    }

    public static void startDatabaseMenu(Shop shop){
        boolean exit = false;
        try {
            while (!exit) {
                switch (checkNumber(databaseMenu, 7)) {
                    case 1: { showDatabaseTable(); break; }
                    case 2: { fillTable(shop.getProductList()); break; }
                    case 3: { deleteFromTable(); break; }
                    case 4: { deleteAll(); break; }
                    case 5: { buyProduct(); break;}
                    case 6: { shop.setProductList(getList()); break;}
                    case 7: { addProduct(createNewProduct("Создайте товар: "));break;}
                    case 0: { exit = true;}
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void start() {
        Shop shop = new Shop();
        deserialize(shop);
        boolean exit = false;
        while (!exit) {
            switch (checkNumber(mainMenu, 9)) {
                case 1: { startSortMenu(shop); break;}
                case 2: { shop.addProduct(createNewProduct("Создайте новый товар")); break;}
                case 3: { shop.editProduct(createNewProduct("Создайте новый товар с уже существующим ID, для редактирования"));break;}
                case 4: { shop.deleteProduct();break; }
                case 5: { createReport(shop.getProductList(), reportFilePath); break; }
                case 6: { createXML(shop); break;}
                case 7: { shop = unmarshall(); break;}
                case 8: { imitate(shop); break;}
                case 9: { startDatabaseMenu(shop); break;}
                case 0: { exit = true; serialize(shop); }
                serialize(shop);
            }
        }
    }

    public static void main(String[] args) { start(); }
}
