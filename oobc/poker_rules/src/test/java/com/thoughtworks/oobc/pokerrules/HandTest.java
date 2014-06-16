package com.thoughtworks.oobc.pokerrules;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
}
