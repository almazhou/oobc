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
        return roundToNearest5Percent(getTotalSalesTax());
    }

    public double roundToNearest5Percent(double total) {
        return Math.round(total * 100 / 5) * 5 / 100.00;
    }

    public double getTotalSalesTax() {
        double total = 0.0;
        for (Product product : products) {
            total += calculator.calculateTax(product);
        }
        return total;
    }

    public double getTotal() {
        return getTotalPrice() + getSalesTax();
    }

    private double getTotalPrice() {
        double total = 0.0;
        for (Product product : products)
            total += product.getPrice();
        return total;
    }

    public void printTo(OutputStream output) throws IOException {
        StringBuffer printInfo = preparePrintInfo();
        printGivenInfo(output, printInfo);
    }

    private StringBuffer preparePrintInfo() {
        StringBuffer printInfo = new StringBuffer();
        printInfo.append(getItemInfor()).append(getSalesTaxesInfo()).append(getTotalPrintInfo());
        return printInfo;
    }

    private void printGivenInfo(OutputStream output, StringBuffer printInfo) throws IOException {
        OutputStreamWriter out = new OutputStreamWriter(output);
        out.append(printInfo);
        out.flush();
    }

    private StringBuffer getTotalPrintInfo() {
        StringBuffer total = new StringBuffer();
        total.append("Total: ").append(String.valueOf(getTotal())).append("\n");
        return total;
    }

    private StringBuffer getSalesTaxesInfo() {
        StringBuffer salesTaxes = new StringBuffer();
        salesTaxes.append("Sales Taxes: ").append(String.valueOf(getSalesTax())).append("\n");
        return salesTaxes;
    }

    private StringBuffer getItemInfor() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Product product : products)
            stringBuffer.append("1 ").append(product.getDescription())
                    .append(": ").append(String.valueOf(product.getPrice())).append("\n");
        return stringBuffer;
    }
}
