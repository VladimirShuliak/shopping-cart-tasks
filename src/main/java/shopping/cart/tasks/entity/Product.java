package shopping.cart.tasks.entity;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal pricePerUnit;
    private int quantity;
    private String category;

    public Product() {
    }

    private Product(Builder builder) {
        name = builder.name;
        pricePerUnit = builder.pricePerUnit;
        quantity = builder.quantity;
        category = builder.category;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPricePerUnit() {
        return this.pricePerUnit;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getCategory() {
        return this.category;
    }

    public static final class Builder {
        private String name;
        private BigDecimal pricePerUnit;
        private int quantity;
        private String category;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder pricePerUnit(BigDecimal val) {
            pricePerUnit = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder category(String val) {
            category = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
