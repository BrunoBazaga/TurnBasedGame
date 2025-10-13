public class Round {

    public enum Result { PLAYER_DEAD, ENEMY_DEAD }

    public Result start(User u, Entity enemy) {
        System.out.println("Your health is " + u.getHealth());
        System.out.println("The opponent is " + enemy.getName());

        BasicAttack attack = new BasicAttack();

        // Decide who goes first (by speed; if tie, user starts)
        Entity attacker = (u.getSpeed() >= enemy.getSpeed()) ? u : enemy;
        Entity defender = (attacker == u) ? enemy : u;

        System.out.println(attacker.getName() + " goes first!");

        while (attacker.getHealth() > 0 && defender.getHealth() > 0) {
            attack.execute(attacker, defender);

            if (defender.getHealth() <= 0) {
                System.out.println(defender.getName() + " has been defeated!");
                System.out.println(attacker.getName() + " wins!");
                System.out.println("Battle over.");
                return (defender == u) ? Result.PLAYER_DEAD : Result.ENEMY_DEAD;
            }

            // swap
            Entity tmp = attacker;
            attacker = defender;
            defender = tmp;
        }

        // Fallback (shouldn’t happen)
        System.out.println("Battle over.");
        return (u.getHealth() <= 0) ? Result.PLAYER_DEAD : Result.ENEMY_DEAD;
    }
}
