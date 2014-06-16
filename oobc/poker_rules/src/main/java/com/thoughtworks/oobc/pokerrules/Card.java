package com.thoughtworks.oobc.pokerrules;

public class Card {
    private final Kind kind;
    private final int number;

    public Card(Kind kind, int number) {

        this.kind = kind;
        this.number = number;
    }

    public Kind getKind() {
        return kind;
    }

    public int getNumber() {
        return number;
    }

    public enum Kind {Heart, Diamond, Club, Spade}
}
