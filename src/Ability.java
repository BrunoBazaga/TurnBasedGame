public interface Ability {
    String getName();
    int execute(Entity attacker, Entity defender);
}
