package utility.enums;

public enum OrderLayer {
    UI(0),
    FOREGROUND(1),
    MIDDLEGROUND(3),
    COLUMNS(5),
    PARALLAX_1(7),
    PARALLAX_2(9),
    BACKGROUND(12);

    public final int layer;

    private OrderLayer(int layer) {
        this.layer = layer;
    }
}
