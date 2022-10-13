package shopping.cart.tasks.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shopping.cart.tasks.entity.Product;
import shopping.cart.tasks.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();
    private final Product tea = Product.newBuilder()
        .name("Tea")
        .pricePerUnit(BigDecimal.valueOf(5.0))
        .quantity(4)
        .category("drinks")
        .build();
    private final Product coffee = Product.newBuilder()
        .name("Coffee")
        .pricePerUnit(BigDecimal.valueOf(5.0))
        .quantity(1)
        .category("drinks")
        .build();
    private final Product cheese = Product.newBuilder()
        .name("Cheese")
        .pricePerUnit(BigDecimal.valueOf(5))
        .quantity(1)
        .category("food")
        .build();

    //task 1
    @Test
    public void calcPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(tea, coffee));

        BigDecimal result = service.calculateTotalPrice(cart).setScale(0, RoundingMode.HALF_DOWN);

        Assertions.assertEquals(result, BigDecimal.valueOf(25));
    }

    //task 2
    @Test
    void calculateTotalPriceWithDiscount() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(tea, coffee));

        BigDecimal result = service.calcTotalPriceWithDiscount(cart).setScale(0, RoundingMode.HALF_DOWN);

        Assertions.assertEquals(result, BigDecimal.valueOf(23));
    }

    //task 3
    @Test
    void calcTotalPriceWithDiscountForTheSameCategory() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(tea, coffee, cheese));

        BigDecimal result = service.calcTotalPriceWithDiscountForTheSameCategory(cart).setScale(1, RoundingMode.HALF_DOWN);

        Assertions.assertEquals(result, BigDecimal.valueOf(27.5));
    }

    //task 4
    @Test
    void calcTotalPriceWithCustomDiscountConfig() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(tea, coffee, cheese));

        BigDecimal result = service.calcTotalPriceWithCustomDiscountConfig(cart, 3, 0.1)
            .setScale(1, RoundingMode.HALF_DOWN);

        Assertions.assertEquals(result, BigDecimal.valueOf(27.5));
    }
}
