package utility;

public enum OrderLayer {
    UI(0),
    FOREGROUND(1),
    MIDDLEGROUND(3),
    COLUMNS(6),
    PARALLAX_1(8),
    PARALLAX_2(11),
    BACKGROUND(12);

    public final int layer;

    private OrderLayer(int layer) {
        this.layer = layer;
    }
}
