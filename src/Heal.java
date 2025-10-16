public class Heal implements PlayerAbility {
    private final String name = "Heal"; 

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int execute(Entity player){
        int health = player.getHealth();
        player.setHealth(health+=10);
        System.out.println("Your health is now " + player.getHealth());

        return player.getHealth();
    }
}
