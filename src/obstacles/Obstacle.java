package obstacles;

public abstract class Obstacle {
    
    // make private later on
    public int lifetime;
    
    public Obstacle(int lifetime) {
        this.lifetime = lifetime;
    }
    
    public abstract String killEffect();
    
    public boolean decay() {
      if (lifetime > 0)  {
          lifetime--;
          return false;
      } else {
          return true;
      }
    }
}
