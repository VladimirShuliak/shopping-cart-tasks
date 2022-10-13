package shopping.cart.tasks.service;

import shopping.cart.tasks.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCartService {
    private static final int PRODUCTS_QUANTITY_FOR_DISCOUNT = 3;

    public BigDecimal calculateTotalPrice(final ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
            .stream()
            .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calcTotalPriceWithDiscount(final ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
            .stream()
            .map(product -> {
                if (product.getQuantity() > PRODUCTS_QUANTITY_FOR_DISCOUNT) {
                    final BigDecimal sum = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
                    return sum.subtract(sum.multiply(BigDecimal.valueOf(0.1)));
                }
                return product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
            })
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calcTotalPriceWithDiscountForTheSameCategory(final ShoppingCart shoppingCart) {
        Set<String> categoriesWithDiscount = new HashSet<>();
        return shoppingCart.getProducts()
            .stream()
            .map(product -> {
                if (product.getQuantity() > PRODUCTS_QUANTITY_FOR_DISCOUNT) {
                    categoriesWithDiscount.add(product.getCategory());
                }
                if (categoriesWithDiscount.contains(product.getCategory())) {
                    final BigDecimal sum = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
                    return sum.subtract(sum.multiply(BigDecimal.valueOf(0.1)));
                }
                return product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
            })
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }

    public BigDecimal calcTotalPriceWithCustomDiscountConfig(final ShoppingCart shoppingCart,
                                                             final int productQuantityForDiscount,
                                                             final Double percentageDiscount) {
        Set<String> categoriesWithDiscount = new HashSet<>();
        return shoppingCart.getProducts()
            .stream()
            .map(product -> {
                if (product.getQuantity() > productQuantityForDiscount) {
                    categoriesWithDiscount.add(product.getCategory());
                }
                if (categoriesWithDiscount.contains(product.getCategory())) {
                    final BigDecimal sum = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
                    return sum.subtract(sum.multiply(BigDecimal.valueOf(percentageDiscount)));
                }
                return product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
            })
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
    }
}
