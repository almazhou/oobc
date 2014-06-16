package com.thoughtworks.oobc.pokerrules;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class Hand {
    private final List<Card> cards;

    public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.cards = Arrays.asList(card1, card2, card3, card4, card5);
    }

    public Rank getRank() {

        Set<Card.Kind> kinds = cards.stream().map(Card::getKind).collect(Collectors.toSet());

        Set<Integer> numbers = cards.stream().sorted((s1, s2) -> s1.getNumber() - s2.getNumber())
                .map(Card::getNumber).collect(Collectors.toSet());

        Integer max = Collections.max(cards.stream().map(Card::getNumber).collect(Collectors.groupingBy(s -> s))
                .values().stream().map(s -> s.size()).collect(Collectors.toList()));


        if (numbers.size() == 5) {
            if (kinds.size() == 1) {
                if (max(numbers) - min(numbers) == 4) return Rank.StraightFlush;
                if (max(numbers) == 13 && min(numbers) == 1) return Rank.RoyalFlush;
                return Rank.Flush;
            }
            if (max(numbers) - min(numbers) == 4 || (max(numbers) == 13 && min(numbers) == 1)) return Rank.Straight;
            return Rank.HighCard;
        }

        if (numbers.size() == 4 && max != 3) return Rank.OnePair;
        if (numbers.size() == 3 && max != 3) return Rank.TwoPair;
        if (numbers.size() == 2 && max != 3) return Rank.FourOfAKind;

        if (numbers.size() == 3 && max == 3) return Rank.ThreeOfAKind;
        if (numbers.size() == 2 && max == 3) return Rank.FullHouse;

        return Rank.HighCard;
    }

    public enum Rank {
        HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush
    }

}
