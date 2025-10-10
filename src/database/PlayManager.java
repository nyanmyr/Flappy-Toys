package database;

import toys.Foldy;
import toys.Rocketron;
import toys.Teddycopter;
import toys.Toy;

public class PlayManager {

    public static void gameOver(Toy toy) {
        
        System.out.print("Character: ");
        if (toy instanceof Foldy) {
            System.out.print("Foldy\n");
        } else if (toy instanceof Rocketron) {
            System.out.print("Rocketron\n");
        } else if (toy instanceof Teddycopter) {
            System.out.print("Teddycopter\n");
        } else {
            throw new RuntimeException("Toy does not exist");
        }

        System.out.println("Score: " + toy.getScore());
        System.out.println("Tokens Collected: " + toy.getTokensCollected());
        System.out.println("Charges Used: " + toy.getChargesUsed());
    }
}
