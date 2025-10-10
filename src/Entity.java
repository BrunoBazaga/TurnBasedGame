public class Entity {
    private String name;
    private int attackPower;
    private int health;
    private int speed;
    private int defencePower;

    public Entity(String name, int health, int attackPower, int defencePower, int speed) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defencePower = defencePower;
        this.speed = speed;
    }

    public int getAttackPower() { return attackPower; }
    public int  getHealth() { return health; }
    public int getSpeed() { return speed; }
    public int getDefencePower() { return defencePower; }
    public String getName() { return name; }

    public void setHealth(int health) {
        this.health = health;
    }
}
