class InitMove {
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;

    InitMove(boolean left, boolean right, boolean up, boolean down){
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }


    boolean isLeft() {
        return left;
    }

    boolean isRight() {
        return right;
    }

    boolean isUp() {
        return up;
    }

    boolean isDown() {
        return down;
    }

    void setLeft(boolean left) {
        this.left = left;
    }

    void setRight(boolean right) {
        this.right = right;
    }

    void setUp(boolean up) {
        this.up = up;
    }

    void setDown(boolean down) {
        this.down = down;
    }
}
