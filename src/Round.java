public class Round {

   public void start() {
        // Create the player
        User u = new User("Hero", 100, 20, 10, 8);
        System.out.println("Your health is " + u.getHealth());

        // Pick a random opponent
        Entity enemy = OpponentPool.random();
        System.out.println("The opponent is " + enemy.getName());

        // Create a basic attack handler
        BasicAttack attack = new BasicAttack();

        // Decide who goes first (by speed; if tie, user starts)
        Entity attacker = (u.getSpeed() >= enemy.getSpeed()) ? u : enemy;
        Entity defender = (attacker == u) ? enemy : u;

        System.out.println(attacker.getName() + " goes first!");

        // Fight until someone’s health hits 0
        while (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            attack.execute(attacker, defender);

            // Check if the defender is defeated
            if (defender.getHealth() <= 0) {
                System.out.println(defender.getName() + " has been defeated!");
                System.out.println(attacker.getName() + " wins!");
                break;
            }

            // Swap attacker and defender
            Entity temp = attacker;
            attacker = defender;
            defender = temp;
        }

        System.out.println("Battle over.");
    }
}
