package toys.stats;

public enum JumpHeight {
    LOW(40),
    NORMAL(50),
    HIGH(60);

    public final int height;

    private JumpHeight(int height) {
        this.height = height;
    }
}
