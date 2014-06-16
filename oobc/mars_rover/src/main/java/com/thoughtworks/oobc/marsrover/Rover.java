package com.thoughtworks.oobc.marsrover;

public class Rover {
    private Positions positions;
    private Facing facing;

    public Rover(Positions positions, Facing facing) {
        this.positions = positions;
        this.facing = facing;
    }

    public void move() {
        this.positions = facing.move(positions);
    }

    public int getY() {
        return positions.y;
    }

    public int getX() {
        return positions.x;
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

            @Override
            public Positions move(Positions positions) {
                positions.y -= 1;
                return positions;
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

            @Override
            public Positions move(Positions positions) {
                positions.x += 1;
                return positions;
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

            @Override
            public Positions move(Positions positions) {
                positions.x -= 1;
                return positions;
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

            @Override
            public Positions move(Positions positions) {
                positions.y += 1;
                return positions;
            }
        };

        public abstract Facing right();

        public abstract Facing left();

        public abstract Positions move(Positions positions);

    }
}
