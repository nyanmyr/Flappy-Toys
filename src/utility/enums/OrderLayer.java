package utility.enums;

public enum OrderLayer {
    UI(0),
    FOREGROUND(1),
    MIDDLEGROUND(2),
    COLUMNS(3),
    PARALLAX_1(4),
    PARALLAX_2(5),
    BACKGROUND(3);

    public final int layer;

    private OrderLayer(int layer) {
        this.layer = layer;
    }
}
