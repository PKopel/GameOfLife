package main;

public enum  Neighbours {
        N(0, 1), NE(1, 1), E(1, 0), SE(1, -1),
        S(0, -1), SW(-1, -1), W(-1, 0), NW(-1, 1);

        private int x;
        private int y;

        Neighbours(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position nextPosition(Position current) {
            int nextX = current.x + this.x;
            int nextY = current.y + this.y;
            return new Position(nextX, nextY);
        }


        @Override
        public String toString() {
            return this.ordinal() + "";
        }

}
