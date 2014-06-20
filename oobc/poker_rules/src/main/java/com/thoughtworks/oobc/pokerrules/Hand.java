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

        Set<Card.Kind> kinds = getKinds();

        Set<Integer> numbers = getNumbers();

        Integer max = getMaxCardValue();


        if (hasNoRepeat(numbers)) {
            if (isFlush(kinds)) {
                if (isStraight(numbers)) return Rank.StraightFlush;
                if (isRoyal(numbers)) return Rank.RoyalFlush;
                return Rank.Flush;
            }
            if (isStraight(numbers) || (isRoyal(numbers))) return Rank.Straight;
            return Rank.HighCard;
        }

        if (isOnePair(numbers, max)) return Rank.OnePair;
        if (isTwoPair(numbers, max)) return Rank.TwoPair;
        if (isFourOfAKind(numbers, max)) return Rank.FourOfAKind;

        if (isThreeOfAKind(numbers, max)) return Rank.ThreeOfAKind;
        if (isFullHouse(numbers, max)) return Rank.FullHouse;

        return Rank.HighCard;
    }

    private boolean isFullHouse(Set<Integer> numbers, Integer max) {
        return numbers.size() == 2 && max == 3;
    }

    private boolean isThreeOfAKind(Set<Integer> numbers, Integer max) {
        return numbers.size() == 3 && max == 3;
    }

    private boolean isFourOfAKind(Set<Integer> numbers, Integer max) {
        return numbers.size() == 2 && max != 3;
    }

    private boolean isTwoPair(Set<Integer> numbers, Integer max) {
        return numbers.size() == 3 && max != 3;
    }

    private boolean isOnePair(Set<Integer> numbers, Integer max) {
        return numbers.size() == 4 && max != 3;
    }

    private boolean isRoyal(Set<Integer> numbers) {
        return max(numbers) == 13 && min(numbers) == 1;
    }

    private boolean isStraight(Set<Integer> numbers) {
        return max(numbers) - min(numbers) == 4;
    }

    private boolean isFlush(Set<Card.Kind> kinds) {
        return kinds.size() == 1;
    }

    private boolean hasNoRepeat(Set<Integer> numbers) {
        return numbers.size() == 5;
    }

    private Integer getMaxCardValue() {
        return Collections.max(cards.stream().map(Card::getNumber).collect(Collectors.groupingBy(s -> s))
                .values().stream().map(s -> s.size()).collect(Collectors.toList()));
    }

    private Set<Integer> getNumbers() {
        return cards.stream().map(Card::getNumber).collect(Collectors.toSet());
    }

    private Set<Card.Kind> getKinds() {
        return cards.stream().map(Card::getKind).collect(Collectors.toSet());
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
