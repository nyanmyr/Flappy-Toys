package abilities;

import static game.Game.MILISECOND_DELAY;
import toys.Toy;

public class Shield implements Ability {

    @Override
    public void useAbility(Toy toy) {
        // translates to 3 seconds of immunity
        toy.setImmunity((MILISECOND_DELAY * 10) * 3);
        System.out.println("Shield ability used.");
    }
}
