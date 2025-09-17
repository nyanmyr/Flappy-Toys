package abilities;

import toys.Toy;


public class Dash implements Ability {

    @Override
    public void useAbility(Toy toy) {
        toy.move(50, 0);
        System.out.println("Dash ability used.");
    }
}
