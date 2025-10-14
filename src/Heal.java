public class Heal implements Ability {
    private final String name = "Heal"; 

    @Override
    public String getName(){
        return name;
    }
    public int execute(Entity attacker, Entity defender){
        int health = attacker.getHealth();
        attacker.setHealth(health+=10);
        return attacker.getHealth();
    }
}
