package com.thoughtworks.oobc.salestax;

public class Product {
    private final Source source;
    private final Category category;
    private final String description;
    private final double price;

    public Product(Source source, Category category, String description, double price) {
        this.source = source;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Source getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public enum Category {Etc, Food}

    public enum Source {Imported, Domestic}
}
