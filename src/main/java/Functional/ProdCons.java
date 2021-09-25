package Functional;

import java.util.List;
import java.util.Random;

class Producer implements Runnable{
    Store store;
    Producer(Store store){
        this.store = store;
    }
    public void run(){
        for(int i = 0; i<10; i++){
            store.put();
        }
    }
}

class Consumer implements Runnable{
    Store store;
    Consumer(Store store){
        this.store = store;
    }
    public void run(){
        for(int i = 0; i<10; i++){
            store.get();
        }
    }
}

class Store{
    private int productsAmount;
    List<Product> products;

    public Store(Shop shop) {
        this.products = shop.getProductList();
        this.productsAmount = shop.getProductList().size();
    }
    public synchronized void get() {
        while(productsAmount < 1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        this.products.remove(products.size()-1);
        productsAmount = products.size(); //в файл перезапишется список без удалённого продукта
        Methods.createReport(this.products, "src/main/java/Files/storeReport.txt");
        System.out.println("Покупатель купил один товар");
        System.out.println("Товаров на складе: " + productsAmount);
        notify();
    }
    public synchronized void put(){
        while(productsAmount >= 10){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.products.add(generateProduct());
        productsAmount = products.size();
        Methods.createReport(this.products, "src/main/java/Files/storeReport.txt");
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + productsAmount);
        notify();
    }

    public Product generateProduct(){
        Random random = new Random();
        String name = "Товар" + productsAmount;
        int price = random.nextInt(1000);
        int id = random.nextInt(1000) + random.nextInt(1000);
        return new Product(id, price, name);
    }
}

public class ProdCons {
    public static void imitate(Shop shop) {
        Store store = new Store(shop);
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}