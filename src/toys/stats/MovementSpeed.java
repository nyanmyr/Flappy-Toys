package toys.stats;

public enum MovementSpeed {
    SLOW(2),
    NORMAL(3),
    FAST(4);

    public final int speed;

    private MovementSpeed(int speed) {
        this.speed = speed;
    }
}
