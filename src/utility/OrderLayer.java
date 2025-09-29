package utility;

public enum OrderLayer {
    UI(0),
    FOREGROUND(2),
    MIDDLEGROUND(4),
    COLUMNS(6),
    PARALLAX_1(8),
    PARALLAX_2(10),
    BACKGROUND(12);

    public final int layer;

    private OrderLayer(int layer) {
        this.layer = layer;
    }
}
