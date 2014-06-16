package com.thoughtworks.oobc.pokerrules;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandTest {
    @Test
    public void should_identify_royal_flush() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Spade, 13),
                new Card(Card.Kind.Spade, 12),
                new Card(Card.Kind.Spade, 11),
                new Card(Card.Kind.Spade, 10));

        assertThat(hand.getRank(), is(Hand.Rank.RoyalFlush));
    }

    @Test
    public void should_identify_straight_flush() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Spade, 2),
                new Card(Card.Kind.Spade, 3),
                new Card(Card.Kind.Spade, 4),
                new Card(Card.Kind.Spade, 5));

        assertThat(hand.getRank(), is(Hand.Rank.StraightFlush));
    }

    @Test
    public void should_identify_flush() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Spade, 4),
                new Card(Card.Kind.Spade, 6),
                new Card(Card.Kind.Spade, 7),
                new Card(Card.Kind.Spade, 9));

        assertThat(hand.getRank(), is(Hand.Rank.Flush));
    }

    @Test
    public void should_identify_straight() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 2),
                new Card(Card.Kind.Diamond, 3),
                new Card(Card.Kind.Club, 4),
                new Card(Card.Kind.Heart, 5));

        assertThat(hand.getRank(), is(Hand.Rank.Straight));
    }

    @Test
    public void should_identify_straight_with_king_and_ace() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 13),
                new Card(Card.Kind.Diamond, 12),
                new Card(Card.Kind.Club, 11),
                new Card(Card.Kind.Heart, 10));

        assertThat(hand.getRank(), is(Hand.Rank.Straight));
    }

    @Test
    public void should_identify_one_pair() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Diamond, 3),
                new Card(Card.Kind.Club, 4),
                new Card(Card.Kind.Heart, 5));
        assertThat(hand.getRank(), is(Hand.Rank.OnePair));
    }

    @Test
    public void should_identify_two_pair() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Diamond, 3),
                new Card(Card.Kind.Club, 3),
                new Card(Card.Kind.Heart, 5));
        assertThat(hand.getRank(), is(Hand.Rank.TwoPair));
    }

    @Test
    public void should_identify_four_of_a_kind() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Diamond, 1),
                new Card(Card.Kind.Club, 1),
                new Card(Card.Kind.Heart, 5));
        assertThat(hand.getRank(), is(Hand.Rank.FourOfAKind));
    }

    @Test
    public void should_identify_three_of_a_kind() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Diamond, 1),
                new Card(Card.Kind.Club, 4),
                new Card(Card.Kind.Heart, 5));
        assertThat(hand.getRank(), is(Hand.Rank.ThreeOfAKind));
    }

    @Test
    public void should_identify_full_house() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Diamond, 1),
                new Card(Card.Kind.Club, 5),
                new Card(Card.Kind.Heart, 5));
        assertThat(hand.getRank(), is(Hand.Rank.FullHouse));
    }

    @Test
    public void should_identify_high_card() {
        Hand hand = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 3),
                new Card(Card.Kind.Diamond, 5),
                new Card(Card.Kind.Club, 6),
                new Card(Card.Kind.Heart, 9));
        assertThat(hand.getRank(), is(Hand.Rank.HighCard));
    }

    @Test
    public void higher_rank_should_be_greater_than_low_rank() {
        Hand hand1 = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Diamond, 1),
                new Card(Card.Kind.Club, 5),
                new Card(Card.Kind.Heart, 5));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Heart, 3),
                new Card(Card.Kind.Diamond, 5),
                new Card(Card.Kind.Club, 6),
                new Card(Card.Kind.Heart, 9));

        assertTrue(hand1.compareTo(hand2) > 0);
    }

    @Test
    public void royal_flush_should_be_equal() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 1),
                new Card(Card.Kind.Heart, 13),
                new Card(Card.Kind.Heart, 12),
                new Card(Card.Kind.Heart, 11),
                new Card(Card.Kind.Heart, 10));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 1),
                new Card(Card.Kind.Spade, 13),
                new Card(Card.Kind.Spade, 12),
                new Card(Card.Kind.Spade, 11),
                new Card(Card.Kind.Spade, 10));

        assertTrue(hand1.compareTo(hand2) == 0);
    }

    @Test
    public void straight_flush_compare_greatest_card() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Heart, 8),
                new Card(Card.Kind.Heart, 7),
                new Card(Card.Kind.Heart, 6),
                new Card(Card.Kind.Heart, 5));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Spade, 8),
                new Card(Card.Kind.Spade, 7),
                new Card(Card.Kind.Spade, 6));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void straight_compare_greatest_card() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 8),
                new Card(Card.Kind.Heart, 7),
                new Card(Card.Kind.Spade, 6),
                new Card(Card.Kind.Heart, 5));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 8),
                new Card(Card.Kind.Heart, 7),
                new Card(Card.Kind.Spade, 6));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void flush_compare_greatest_card() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Heart, 8),
                new Card(Card.Kind.Heart, 4),
                new Card(Card.Kind.Heart, 3),
                new Card(Card.Kind.Heart, 2));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Spade, 8),
                new Card(Card.Kind.Spade, 4),
                new Card(Card.Kind.Spade, 2));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void high_card_compare_greatest_card() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 8),
                new Card(Card.Kind.Heart, 4),
                new Card(Card.Kind.Spade, 3),
                new Card(Card.Kind.Heart, 2));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 8),
                new Card(Card.Kind.Heart, 4),
                new Card(Card.Kind.Spade, 2));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void three_of_a_kind_compare_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 9),
                new Card(Card.Kind.Spade, 3),
                new Card(Card.Kind.Heart, 2));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 10),
                new Card(Card.Kind.Club, 10),
                new Card(Card.Kind.Heart, 4),
                new Card(Card.Kind.Spade, 2));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void full_house_compare_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 9),
                new Card(Card.Kind.Spade, 3),
                new Card(Card.Kind.Heart, 3));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 10),
                new Card(Card.Kind.Club, 10),
                new Card(Card.Kind.Heart, 4),
                new Card(Card.Kind.Spade, 4));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void four_of_a_kind_compare_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 9),
                new Card(Card.Kind.Club, 9),
                new Card(Card.Kind.Heart, 3));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 10),
                new Card(Card.Kind.Club, 10),
                new Card(Card.Kind.Diamond, 10),
                new Card(Card.Kind.Spade, 4));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void two_pair_compare_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 10),
                new Card(Card.Kind.Club, 10),
                new Card(Card.Kind.Heart, 3));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 10),
                new Card(Card.Kind.Club, 11),
                new Card(Card.Kind.Diamond, 11),
                new Card(Card.Kind.Spade, 4));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void two_pair_compare_card_if_same_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 10),
                new Card(Card.Kind.Club, 10),
                new Card(Card.Kind.Heart, 3));

        Hand hand2 = new Hand(new Card(Card.Kind.Club, 9),
                new Card(Card.Kind.Diamond, 9),
                new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 10),
                new Card(Card.Kind.Spade, 4));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void one_pair_compare_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 10),
                new Card(Card.Kind.Club, 7),
                new Card(Card.Kind.Heart, 3));

        Hand hand2 = new Hand(new Card(Card.Kind.Spade, 10),
                new Card(Card.Kind.Heart, 10),
                new Card(Card.Kind.Club, 12),
                new Card(Card.Kind.Diamond, 11),
                new Card(Card.Kind.Spade, 4));

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void one_pair_compare_card_if_same_pair() {
        Hand hand1 = new Hand(new Card(Card.Kind.Heart, 9),
                new Card(Card.Kind.Spade, 9),
                new Card(Card.Kind.Diamond, 10),
                new Card(Card.Kind.Club, 7),
                new Card(Card.Kind.Heart, 3));

        Hand hand2 = new Hand(new Card(Card.Kind.Club, 9),
                new Card(Card.Kind.Diamond, 9),
                new Card(Card.Kind.Spade, 11),
                new Card(Card.Kind.Heart, 8),
                new Card(Card.Kind.Spade, 4));

        assertTrue(hand2.compareTo(hand1) > 0);
    }
}
