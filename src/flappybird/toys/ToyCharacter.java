package flappybird.toys;

public enum ToyCharacter {
    TEDDYCOPTER,
    ROCKETRON,
    FOLDY;

    public ToyCharacter nextCharacter() {
        ToyCharacter[] characters = ToyCharacter.values();
        int currentOrdinal = this.ordinal();
        int nextOrdinal = (currentOrdinal + 1) % characters.length; // Handle wrap-around
        return characters[nextOrdinal];
    }
}
