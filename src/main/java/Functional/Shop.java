package Functional;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static Functional.Methods.getPositiveNumber;

@XmlType(propOrder = {"productList"}, name ="shop")
@XmlRootElement
public class Shop implements Serializable {
    private List<Product> productList = new ArrayList<>();

    public Shop(){}

    public List<Product> getProductList() { return this.productList; }

    public void setProductList(List<Product> productList) { this.productList = productList;}

    private boolean isIdInList(int id) { return this.productList.stream().filter(product -> product.getId()==id).anyMatch(product -> product.getId() == id);}

    public void addProduct(Product product) {
        if (isIdInList(product.getId())) {
            System.out.println("Товар с таким ID уже добавлен!");
        } else {
            this.productList.add(product);
        }
    }

    public void deleteProduct() {
        System.out.println("Введите ID для удаления: ");
        int id = getPositiveNumber();
        if (isIdInList(id)) {
            this.productList.removeIf(n -> (n.getId() == id));
            System.out.println("Товар был успешно удалён!");
        } else {
            System.out.println("Такого товара нет в списке!");
        }
    }

    public void editProduct(Product editedProduct) {
        if (isIdInList(editedProduct.getId())) {
            this.productList.stream()
                    .filter(product -> product.getId() == editedProduct.getId())
                    .forEach(product -> {
                        product.setName(editedProduct.getName());
                        product.setPrice(editedProduct.getPrice());
                    });
            System.out.println("Продукст с ID " + editedProduct.getId() + " успешно изменён!");
        } else {
            System.out.println("Такого ID для редактирования нет в списке товаров!");
        }
    }

    private void showFromTo(List<Product> products) {
        System.out.print("Вывести\nОт: ");
        int minPrice = getPositiveNumber();
        System.out.print("До: ");
        int maxPrice = getPositiveNumber();
        System.out.println();
        if (maxPrice < minPrice) {
            System.out.println("Некорректный ввод диапазона!");
        } else {
            products.stream()
                    .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                    .forEach(Product::show);
        }
    }

    public boolean sort(int choose) {
        List<Product> products = this.getProductList();
        switch (choose) {
            case 1 :{ products.sort(Comparator.comparing(Product::getPrice)); break; }
            case 2 :{ products.sort(Comparator.comparing(Product::getPrice).reversed()); break; }
            case 3 :{ products.sort(Comparator.comparing(Product::getDateTime).reversed()); break; }
            case 4 :{ showFromTo(products); return false;}
            case 0 :{ return true;}
        }
        System.out.println();
        products.forEach(Product::show);
        return false;
    }
}
