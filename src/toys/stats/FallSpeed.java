package toys.stats;

public enum FallSpeed {
    SLOW(1),
    NORMAL(2),
    FAST(3);

    public final int speed;

    private FallSpeed(int speed) {
        this.speed = speed;
    }
}
