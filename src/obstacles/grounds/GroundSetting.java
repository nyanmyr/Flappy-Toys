package obstacles.grounds;

public enum GroundSetting {
    NORMAL(0),
    OFFSET(800),
    LOWERED(200);
    
    public final int value;
    
    private GroundSetting(int value) {
        this.value = value;
    }
}
