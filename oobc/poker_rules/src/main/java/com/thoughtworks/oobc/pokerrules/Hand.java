package com.thoughtworks.oobc.pokerrules;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class Hand implements Comparable<Hand> {
    private final List<Card> cards;

    public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.cards = Arrays.asList(card1, card2, card3, card4, card5);
    }

    public Rank getRank() {

        Set<Card.Kind> kinds = cards.stream().map(Card::getKind).collect(Collectors.toSet());

        Set<Integer> numbers = cards.stream().map(Card::getNumber).collect(Collectors.toSet());

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

    @Override
    public int compareTo(Hand o) {
        Rank rank = getRank();
        int rankCompare = rank.compareTo(o.getRank());
        if (rankCompare != 0) return rankCompare;
        if (rank == Rank.Straight || rank == Rank.StraightFlush
                || rank == Rank.Flush || rank == Rank.HighCard)
            return getMaxCard().getNumber() - o.getMaxCard().getNumber();

        List<Integer> pairs = getPairs();
        List<Integer> otherPairs = o.getPairs();
        for (int i = 0; i < pairs.size(); i++)
            if (pairs.get(i) - otherPairs.get(i) != 0) return pairs.get(i) - otherPairs.get(i);
        return 0;
    }

    private List<Integer> getPairs() {
        Map<Integer, List<Integer>> collect = cards.stream().map(Card::getNumber).collect(Collectors.groupingBy(s -> s));
        return collect.keySet().stream().sorted(
                (a, b) -> {
                    int result = collect.get(a).size() - collect.get(b).size();
                    return -(result != 0 ? result : a - b);
                }
        ).collect(Collectors.toList());
    }


    private Card getMaxCard() {
        return cards.stream().max((c1, c2) -> c1.getNumber() - c2.getNumber()).get();
    }

    public enum Rank {
        HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush, RoyalFlush
    }

}
