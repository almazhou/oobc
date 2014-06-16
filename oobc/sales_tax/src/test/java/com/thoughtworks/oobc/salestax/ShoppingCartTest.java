package com.thoughtworks.oobc.salestax;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ShoppingCartTest {
    @Test
    public void should_calculate_total_sales_tax() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);

        ShoppingCart cart = new ShoppingCart(calculator,
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.5),
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.5));

        assertEquals(0.1, cart.getSalesTax(), 0.0001);
    }

    @Test
    public void should_round_up_to_nearest_0_05() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);

        ShoppingCart cart = new ShoppingCart(calculator,
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.3));

        assertEquals(0.05, cart.getSalesTax(), 0.0001);
    }

    @Test
    public void should_round_down_to_nearest_0_05() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);

        ShoppingCart cart = new ShoppingCart(calculator,
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.6));

        assertEquals(0.05, cart.getSalesTax(), 0.0001);
    }

    @Test
    public void should_round_up_to_nearest_0_05_when_greater_than_0_05() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);

        ShoppingCart cart = new ShoppingCart(calculator,
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.8));

        assertEquals(0.1, cart.getSalesTax(), 0.0001);
    }

    @Test
    public void should_get_total_price() {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);

        ShoppingCart cart = new ShoppingCart(calculator,
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.5),
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.5));

        assertEquals(1.1, cart.getTotal(), 0.0001);
    }

    @Test
    public void should_print_out_shopping_cart() throws IOException {
        TaxCalculator calculator = new TaxCalculator(Product.Category.Food);

        ShoppingCart cart = new ShoppingCart(calculator,
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.5),
                new Product(Product.Source.Domestic, Product.Category.Etc, "music CD", 0.5));

        ByteArrayOutputStream output = new ByteArrayOutputStream();


        cart.printTo(output);

        assertThat(new String(output.toByteArray()), is("1 music CD: 0.5\n1 music CD: 0.5\nSales Taxes: 0.1\nTotal: 1.1\n"));
    }
}
