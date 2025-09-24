package obstacles.grounds;

public enum GroundSetting {
    NORMAL(0),
    OFFSET(800);
    
    public final int value;
    
    private GroundSetting(int value) {
        this.value = value;
    }
}
