package shopping.cart.tasks.entity;

import java.util.List;

public class ShoppingCart {
    private final List<Product> products;

    public ShoppingCart(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
