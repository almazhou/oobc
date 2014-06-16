package com.thoughtworks.oobc.salestax;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TaxCalculatorTest {
    @Test
    public void should_charge_basic_tax_for_non_exempt_product() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);
        assertThat(calculator.calculateTax(new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 14.99))
                , is(14.99 * 0.1));
    }

    @Test
    public void should_charge_import_duty_for_non_exempt_product() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);
        assertEquals(calculator.calculateTax(new Product(Product.Source.Imported, Product.Category.Etc, "music CD", 14.99)),
                14.99 * 0.15, 0.00001);
    }

    @Test
    public void should_charge_no_tax_for_exempt_product() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);
        assertThat(calculator.calculateTax(new Product(Product.Source.Domestic, Product.Category.Food, "chocolate bar", 0.85))
                , is(0.0));
    }

    @Test
    public void should_charge_import_duty_for_exempt_product() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);
        assertThat(calculator.calculateTax(new Product(Product.Source.Imported, Product.Category.Food, "chocolate bar", 0.85))
                , is(0.85 * 0.05));
    }
}
