package com.thoughtworks.oobc.salestax;

public class ShoppingCart {
    private final TaxCalculator calculator;
    private final Product[] products;

    public ShoppingCart(TaxCalculator calculator, Product... products) {
        this.calculator = calculator;
        this.products = products;
    }


    public double getSalesTax() {
        double total = 0.0;
        for (Product product : products) {
            total += calculator.calculate(product);
        }
        return Math.round(total * 100 / 5) * 5 / 100.00;
    }

    public double getTotal() {
        double total = 0.0;
        for (Product product : products)
            total += product.getPrice();
        return total + getSalesTax();
    }
}
