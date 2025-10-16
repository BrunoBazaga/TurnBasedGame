public class Entity {
    private String name;
    private int attackPower;
    private int health;
    private int speed;
    private int defencePower;
    private int maxHealth;

    public Entity(String name, int health, int attackPower, int defencePower, int speed) {
        this.name = name;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;

        // Treat the provided health as BOTH current and max at start
        this.maxHealth = Math.max(1, health);
        this.health = Math.max(0, Math.min(health, this.maxHealth));
    }

    // --- Getters ---
    public String getName()        { return name; }
    public int getAttackPower()    { return attackPower; }
    public int getDefencePower()   { return defencePower; }
    public int getSpeed()          { return speed; }
    public int getHealth()         { return health; }
    public int getMaxHealth()      { return maxHealth; }

    // --- Setters (with safe clamping) ---
    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(health, this.maxHealth));
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = Math.max(1, maxHealth);
        // Keep current health within the new bounds
        this.health = Math.min(this.health, this.maxHealth);
    }
}
