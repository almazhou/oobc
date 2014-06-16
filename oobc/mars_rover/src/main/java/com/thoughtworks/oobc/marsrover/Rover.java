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
        facing = facing.right();
    }

    public enum Facing {
        South {
            @Override
            public Facing right() {
                return West;
            }
        }, East {
            @Override
            public Facing right() {
                return South;
            }
        }, West {
            @Override
            public Facing right() {
                return North;
            }
        }, North {
            @Override
            public Facing right() {
                return East;
            }
        };

        public abstract Facing right();

        }
}
