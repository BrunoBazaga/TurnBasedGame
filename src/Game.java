public class Game {

    public boolean gameStart(boolean startGame) {
        return startGame;
    }

    public static void main(String[] args) {
        User u = new User("Hero", 100, 20, 10, 8);
        System.out.println("Your health is " + u.getHealth());

        Entity enemy = OpponentPool.random();
        System.out.println("The opponent is " + enemy.getName());

        BasicAttack attack = new BasicAttack();

        // Decide who goes first (by speed; if tie then user)
        Entity attacker = (u.getSpeed() >= enemy.getSpeed()) ? u : enemy;
        Entity defender = (attacker == u) ? enemy : u;

        System.out.println(attacker.getName() + " goes first!");

        // Fight until someone drops to 0
        while (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            attack.execute(attacker, defender);

            //check if its KO
            if (defender.getHealth() <= 0) {
                System.out.println(defender.getName() + " has been defeated!");
                System.out.println(attacker.getName() + " wins!");
                break;
            }

            // Swap roles for next turn
            Entity temp = attacker;
            attacker = defender;
            defender = temp;
        }

        System.out.println("Battle over.");
    }
}
