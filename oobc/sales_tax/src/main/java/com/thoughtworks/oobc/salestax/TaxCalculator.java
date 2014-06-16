package com.thoughtworks.oobc.salestax;

import java.util.Arrays;
import java.util.List;

public class TaxCalculator {
    public static final double BASIC_TAX = 0.1;
    public static final double DUTY = 0.05;
    private final List<Product.Category> exempt;

    public TaxCalculator(Product.Category... exempt) {
        this.exempt = Arrays.asList(exempt);
    }

    public double calculate(Product product) {
        double tax = BASIC_TAX;
        if (exempt.contains(product.getCategory())) tax = 0.0;
        if (product.getSource() == Product.Source.Imported) tax += DUTY;
        return product.getPrice() * tax;
    }
}
