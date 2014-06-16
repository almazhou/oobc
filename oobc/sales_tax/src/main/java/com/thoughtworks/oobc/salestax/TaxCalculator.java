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

    public double calculateTax(Product product) {
        return product.getPrice() * getTotalTaxRate(product);
    }

    private double getTotalTaxRate(Product product) {
        return getBasicTaxRate(product) + getDutyRate(product);
    }

    private double getDutyRate(Product product) {
        return product.isImported() ? DUTY : 0;
    }

    private double getBasicTaxRate(Product product) {
        return isExempt(product) ? 0 : BASIC_TAX;
    }

    private boolean isExempt(Product product) {
        return exempt.contains(product.getCategory());
    }

}
