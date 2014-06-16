package com.thoughtworks.oobc.marsrover;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RoverTest {

    @Test
    public void should_move_forward_when_facing_north() {
        Rover rover = new Rover(1, 1, Rover.Facing.North);
        rover.move();

        assertThat(rover.getY(), is(2));
    }

    @Test
    public void should_move_forward_when_facing_south() {
        Rover rover = new Rover(1, 1, Rover.Facing.South);
        rover.move();

        assertThat(rover.getY(), is(0));
    }

    @Test
    public void should_move_forward_when_facing_west() {
        Rover rover = new Rover(1, 1, Rover.Facing.West);
        rover.move();

        assertThat(rover.getX(), is(0));
    }

    @Test
    public void should_move_forward_when_facing_east() {
        Rover rover = new Rover(1, 1, Rover.Facing.East);
        rover.move();

        assertThat(rover.getX(), is(2));
    }


    @Test
    public void should_turn_left_when_facing_north() {
        Rover rover = new Rover(1, 1, Rover.Facing.North);
        rover.turnLeft();

        assertThat(rover.getFacing(), is(Rover.Facing.West));
    }

    @Test
    public void should_turn_left_when_facing_south() {
        Rover rover = new Rover(1, 1, Rover.Facing.South);
        rover.turnLeft();

        assertThat(rover.getFacing(), is(Rover.Facing.East));
    }

    @Test
    public void should_turn_left_when_facing_west() {
        Rover rover = new Rover(1, 1, Rover.Facing.West);
        rover.turnLeft();

        assertThat(rover.getFacing(), is(Rover.Facing.South));
    }

    @Test
    public void should_turn_left_when_facing_east() {
        Rover rover = new Rover(1, 1, Rover.Facing.East);
        rover.turnLeft();

        assertThat(rover.getFacing(), is(Rover.Facing.North));
    }

    @Test
    public void should_turn_right_when_facing_north() {
        Rover rover = new Rover(1, 1, Rover.Facing.North);
        rover.turnRight();

        assertThat(rover.getFacing(), is(Rover.Facing.East));
    }

    @Test
    public void should_turn_right_when_facing_south() {
        Rover rover = new Rover(1, 1, Rover.Facing.South);
        rover.turnRight();

        assertThat(rover.getFacing(), is(Rover.Facing.West));
    }

    @Test
    public void should_turn_right_when_facing_west() {
        Rover rover = new Rover(1, 1, Rover.Facing.West);
        rover.turnRight();

        assertThat(rover.getFacing(), is(Rover.Facing.North));
    }

    @Test
    public void should_turn_right_when_facing_east() {
        Rover rover = new Rover(1, 1, Rover.Facing.East);
        rover.turnRight();

        assertThat(rover.getFacing(), is(Rover.Facing.South));
    }
}
