package abilities;

import toys.Toy;

public class Shield implements Ability {

    @Override
    public void useAbility(Toy toy) {
        toy.setShield(true);
        System.out.println("Shield ability used.");
    }
}
