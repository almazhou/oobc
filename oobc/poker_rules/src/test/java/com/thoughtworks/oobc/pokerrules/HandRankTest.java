package com.thoughtworks.oobc.pokerrules;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HandRankTest {
    @Test
    public void one_pair_should_be_higher_than_high_card() {
        assertTrue(Hand.Rank.OnePair.compareTo(Hand.Rank.HighCard) > 0);
    }

    @Test
    public void two_pair_should_be_higher_than_one_pair() {
        assertTrue(Hand.Rank.TwoPair.compareTo(Hand.Rank.OnePair) > 0);
    }

    @Test
    public void three_of_a_kind_should_be_higher_than_two_pair() {
        assertTrue(Hand.Rank.ThreeOfAKind.compareTo(Hand.Rank.TwoPair) > 0);
    }

    @Test
    public void straight_should_be_higher_than_three_of_a_kind() {
        assertTrue(Hand.Rank.Straight.compareTo(Hand.Rank.ThreeOfAKind) > 0);
    }

    @Test
    public void flush_should_be_higher_than_straight() {
        assertTrue(Hand.Rank.Flush.compareTo(Hand.Rank.Straight) > 0);
    }

    @Test
    public void full_house_should_be_higher_than_flush() {
        assertTrue(Hand.Rank.FullHouse.compareTo(Hand.Rank.Flush) > 0);
    }

    @Test
    public void four_of_a_kind_house_should_be_higher_than_full_house() {
        assertTrue(Hand.Rank.FourOfAKind.compareTo(Hand.Rank.FullHouse) > 0);
    }

    @Test
    public void straight_flush_should_be_higher_than_four_of_a_kind() {
        assertTrue(Hand.Rank.StraightFlush.compareTo(Hand.Rank.FourOfAKind) > 0);
    }

    @Test
    public void royal_flush_should_be_higher_than_straight_flush() {
        assertTrue(Hand.Rank.RoyalFlush.compareTo(Hand.Rank.StraightFlush) > 0);
    }
}
