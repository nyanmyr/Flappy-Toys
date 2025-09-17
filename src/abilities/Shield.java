package abilities;

import toys.Toy;

public class Shield implements Ability {

    @Override
    public void useAbility(Toy toy) {
        toy.shield = true;
        System.out.println("Shield ability used.");
    }
}
