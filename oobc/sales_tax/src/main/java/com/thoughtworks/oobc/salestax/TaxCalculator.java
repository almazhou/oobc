package com.thoughtworks.oobc.salestax;

import java.util.Arrays;
import java.util.List;

public class TaxCalculator {
    private final List<Product.Category> exempt;

    public TaxCalculator(Product.Category... exempt) {
        this.exempt = Arrays.asList(exempt);
    }

    public double calculate(Product product) {
        double tax = 0.1;
        if (exempt.contains(product.getCategory())) tax = 0.0;
        if (product.getSource() == Product.Source.Imported) tax += 0.05;
        return product.getPrice() * tax;
    }
}
