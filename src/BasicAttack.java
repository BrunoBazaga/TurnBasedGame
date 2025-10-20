import java.util.Random;

//uses interface created in Ability.java
public class BasicAttack implements Ability {
    private final String name = "Basic Attack"; //sets name for the ability
    private final Random rng = new Random(); //initialises random method

    @Override
    public String getName() { return name; } //returns name of the ability

    @Override
    //creation of attack method from Ability interface with attacker and defender as pa
    public int execute(Entity attacker, Entity defender) {
        int percent = 85 + rng.nextInt(31); //generates a number between 85 and 115 to act as a multiplier
        int base = attacker.getAttackPower() - defender.getDefencePower();
        int rawDamage = (base * percent) / 100 * critMultiplier(); //computes damage including multiplier
        int damage = Math.max(1, rawDamage); //ensures that damage is at least 1

        defender.setHealth(Math.max(0, defender.getHealth() - damage)); //ensures that negative health does not occur

        //prints attack
        System.out.println(attacker.getName() + " used Basic Attack on " + defender.getName() + " for " + damage + " damage!");
        System.out.println(attacker.getName() + " HP: " + attacker.getHealth() + " | "
                   + defender.getName() + " HP: " + defender.getHealth());


        return damage;
    }

    public int critMultiplier() {
        int multiplier = 1; //sets crit multiplier to 1 by default
        if (rng.nextDouble() < 0.1) { //10% chance to crit
            multiplier = 2;
        }
        return multiplier;
    }

    public String critMesssage() {
        if (critMultiplier() == 2) {
            return " A critical hit!";
        } else {
            return "";
        }
    }
}