package com.thoughtworks.oobc.salestax;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

    public void printTo(OutputStream output) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output);
        for (Product product : products)
            out.append("1 ").append(product.getDescription())
                    .append(": ").append(String.valueOf(product.getPrice())).append("\n");
        out.append("Sales Taxes: ").append(String.valueOf(getSalesTax())).append("\n");
        out.append("Total: ").append(String.valueOf(getTotal())).append("\n");
        out.flush();
    }
}
