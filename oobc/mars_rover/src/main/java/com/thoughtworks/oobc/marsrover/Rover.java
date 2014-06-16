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
        facing = facing.left();
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

            @Override
            public Facing left() {
                return East;
            }
        }, East {
            @Override
            public Facing right() {
                return South;
            }

            @Override
            public Facing left() {
                return North;
            }
        }, West {
            @Override
            public Facing right() {
                return North;
            }

            @Override
            public Facing left() {
                return South;
            }
        }, North {
            @Override
            public Facing right() {
                return East;
            }

            @Override
            public Facing left() {
                return West;
            }
        };

        public abstract Facing right();
        public abstract Facing left();

        }
}
