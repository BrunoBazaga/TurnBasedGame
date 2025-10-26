import java.util.Random;

public class PowerAttack implements Ability {
    private final String name = "Power Attack";
    private final Random rng = new Random();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int execute(Entity attacker, Entity defender) {

        int percent = 130 + rng.nextInt(61); // 130-190 inclusive

        int base = attacker.getAttackPower() - defender.getDefencePower();

        // ensures base is at least 1
        if (base < 1) {
            base = 1;
        }

        // scale the damage
        int rawDamage = (base * percent) / 100;

        // minimum hit is 3
        int damage = Math.max(3, rawDamage);

        // apply damage to defender HP
        defender.setHealth(Math.max(0, defender.getHealth() - damage));

        // recoil between 5% and 15% of attacker max hp
        double recoilPercent = 0.05 + rng.nextDouble() * 0.10; // 0.05 .. 0.15
        int recoil = (int) Math.max(1,
                Math.round(attacker.getMaxHealth() * recoilPercent));

        attacker.setHealth(Math.max(0, attacker.getHealth() - recoil));

        // console logs
        System.out.println(attacker.getName() + " used Power Attack on " + defender.getName() +
                " for " + damage + " damage!");
        System.out.println(attacker.getName() + " took " + recoil + " recoil damage!");
        System.out.println(attacker.getName() + " HP: " + attacker.getHealth() +
                " | " + defender.getName() + " HP: " + defender.getHealth());

        return damage;
    }

    public int critMultiplier() {
        int multiplier = 1; // sets crit multiplier to 1 by default
        if (rng.nextDouble() < 0.1) { // 10% chance to crit
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

    public boolean missed() {
        return rng.nextDouble() < 0.1; // 10% chance to miss
    }
}
