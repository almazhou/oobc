package com.thoughtworks.oobc.marsrover;

public class Rover {
    private int x;
    private int y;
    private Facing facing;

    public Rover(int x, int y, Facing facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    public void move() {
        switch (facing) {
            case North:
                y += 1;
                break;
            case South:
                y -= 1;
                break;
            case West:
                x -= 1;
                break;
            case East:
                x += 1;
                break;
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void turnLeft() {
        switch (facing) {
            case North:
                facing = Facing.West;
                break;
            case South:
                facing = Facing.East;
                break;
            case West:
                facing = Facing.South;
                break;
            case East:
                facing = Facing.North;
                break;
        }
    }

    public Facing getFacing() {
        return facing;
    }

    public void turnRight() {
        switch (facing) {
            case North:
                facing = Facing.East;
                break;
            case South:
                facing = Facing.West;
                break;
            case West:
                facing = Facing.North;
                break;
            case East:
                facing = Facing.South;
                break;
        }
    }

    public enum Facing {South, East, West, North}
}
