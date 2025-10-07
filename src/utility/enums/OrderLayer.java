package utility.enums;

public enum OrderLayer {
    UI(0),
    FOREGROUND(2),
    MIDDLEGROUND(4),
    COLUMNS(6),
    PARALLAX_1(7),
    PARALLAX_2(9),
    BACKGROUND(13);

    public final int layer;

    private OrderLayer(int layer) {
        this.layer = layer;
    }
}
