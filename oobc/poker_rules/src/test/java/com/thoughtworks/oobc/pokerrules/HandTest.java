package com.thoughtworks.oobc.pokerrules;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandTest {
    @Test
    public void should_identify_royal_flush() {
        Hand hand = createFlushHand(Card.Kind.Spade, 1, 13, 12, 11, 10);

        assertThat(hand.getRank(), is(Hand.Rank.RoyalFlush));
    }

    @Test
    public void should_identify_straight_flush() {
        Hand hand = createFlushHand(Card.Kind.Spade, 1, 2, 3, 4, 5);

        assertThat(hand.getRank(), is(Hand.Rank.StraightFlush));
    }

    @Test
    public void should_identify_flush() {
        Hand hand = createFlushHand(Card.Kind.Spade, 1, 4, 6, 7, 9);

        assertThat(hand.getRank(), is(Hand.Rank.Flush));
    }

    @Test
    public void should_identify_straight() {
        Hand hand = createNonFlushhand(1, 2, 3, 4, 5);

        assertThat(hand.getRank(), is(Hand.Rank.Straight));
    }

    @Test
    public void should_identify_straight_with_king_and_ace() {
        Hand hand = createNonFlushhand(1, 13, 12, 11, 10);

        assertThat(hand.getRank(), is(Hand.Rank.Straight));
    }

    @Test
    public void should_identify_one_pair() {
        Hand hand = createNonFlushhand(1, 1, 3, 4, 5);
        assertThat(hand.getRank(), is(Hand.Rank.OnePair));
    }

    @Test
    public void should_identify_two_pair() {
        Hand hand = createNonFlushhand(1, 1, 3, 3, 5);
        assertThat(hand.getRank(), is(Hand.Rank.TwoPair));
    }

    @Test
    public void should_identify_four_of_a_kind() {
        Hand hand = createNonFlushhand(1, 1, 1, 1, 5);
        assertThat(hand.getRank(), is(Hand.Rank.FourOfAKind));
    }

    @Test
    public void should_identify_three_of_a_kind() {
        Hand hand = createNonFlushhand(1, 1, 1, 4, 5);
        assertThat(hand.getRank(), is(Hand.Rank.ThreeOfAKind));
    }

    @Test
    public void should_identify_full_house() {
        Hand hand = createNonFlushhand(1, 1, 1, 5, 5);
        assertThat(hand.getRank(), is(Hand.Rank.FullHouse));
    }

    @Test
    public void should_identify_high_card() {
        Hand hand = createNonFlushhand(1, 3, 5, 6, 9);
        assertThat(hand.getRank(), is(Hand.Rank.HighCard));
    }

    @Test
    public void higher_rank_should_be_greater_than_low_rank() {
        Hand hand1 = createNonFlushhand(1, 1, 1, 5, 5);

        Hand hand2 = createNonFlushhand(1, 3, 5, 6, 9);

        assertTrue(hand1.compareTo(hand2) > 0);
    }

    @Test
    public void royal_flush_should_be_equal() {
        Hand hand1 = createFlushHand(Card.Kind.Heart, 1, 13, 12, 11, 10);

        Hand hand2 = createFlushHand(Card.Kind.Spade, 1, 13, 12, 11, 10);

        assertTrue(hand1.compareTo(hand2) == 0);
    }

    @Test
    public void straight_flush_compare_greatest_card() {
        Hand hand1 = createFlushHand(Card.Kind.Heart, 9, 8, 7, 6, 5);

        Hand hand2 = createFlushHand(Card.Kind.Spade, 10, 9, 8, 7, 6);

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void straight_compare_greatest_card() {
        Hand hand1 = createNonFlushhand(9, 8, 7, 6, 5);
        Hand hand2 = createNonFlushhand(10, 9, 8, 7, 6);

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void flush_compare_greatest_card() {
        Hand hand1 = createFlushHand(Card.Kind.Heart, 9, 8, 4, 3, 2);

        Hand hand2 = createFlushHand(Card.Kind.Spade, 10, 9, 8, 4, 2);

        assertTrue(hand2.compareTo(hand1) > 0);
    }

    @Test
    public void high_card_compare_greatest_card() {
        Hand nonFlushhand1 = createNonFlushhand(9, 8, 4, 3, 2);
        Hand nonFlushhand2 = createNonFlushhand(10, 9, 8, 4, 2);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void three_of_a_kind_compare_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 9, 3, 2);
        Hand nonFlushhand2 = createNonFlushhand(10, 10, 10, 4, 2);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void full_house_compare_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 9, 3, 3);
        Hand nonFlushhand2 = createNonFlushhand(10, 10, 10, 4, 4);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void four_of_a_kind_compare_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 9, 9, 3);
        Hand nonFlushhand2 = createNonFlushhand(10, 10, 10, 10, 4);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void two_pair_compare_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 10, 10, 3);
        Hand nonFlushhand2 = createNonFlushhand(10, 10, 11, 11, 4);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void two_pair_compare_card_if_same_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 10, 10, 3);
        Hand nonFlushhand2 = createNonFlushhand(9, 9, 10, 10, 4);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void one_pair_compare_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 10, 7, 3);
        Hand nonFlushhand2 = createNonFlushhand(10, 10, 12, 11, 4);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    @Test
    public void one_pair_compare_card_if_same_pair() {
        Hand nonFlushhand1 = createNonFlushhand(9, 9, 10, 7, 3);
        Hand nonFlushhand2 = createNonFlushhand(9, 9, 11, 8, 4);

        assertTrue(nonFlushhand2.compareTo(nonFlushhand1) > 0);
    }

    private Hand createFlushHand(Card.Kind kind, int number, int number1, int number2, int number3, int number4) {
        return new Hand(new Card(kind, number),
                new Card(kind, number1),
                new Card(kind, number2),
                new Card(kind, number3),
                new Card(kind, number4));
    }

    private Hand createNonFlushhand(int number, int number1, int number2, int number3, int number4) {
        return new Hand(new Card(Card.Kind.Spade, number),
                new Card(Card.Kind.Heart, number1),
                new Card(Card.Kind.Diamond, number2),
                new Card(Card.Kind.Club, number3),
                new Card(Card.Kind.Heart, number4));
    }
}
