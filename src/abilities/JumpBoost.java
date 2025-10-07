package abilities;

import toys.Toy;

public class JumpBoost implements Ability {

    @Override
    public void useAbility(Toy toy) {
        toy.move(0, -300);
        System.out.println("JumpBoost ability used.");
    }
}
