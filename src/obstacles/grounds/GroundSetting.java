package obstacles.grounds;

public enum GroundSetting {
    NORMAL(0),
    OFFSET(800);
    
    public final int offset;
    
    private GroundSetting(int offset) {
        this.offset = offset;
    }
}
