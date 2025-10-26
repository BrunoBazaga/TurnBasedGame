public final class DifficultyApplier {
    // sets default difficulty to normal
    private static Difficulty difficulty = Difficulty.NORMAL;

    public static void setDifficulty(Difficulty d) {
        difficulty = d;
        System.out.println("[Applier] setDifficulty -> " + d); //terminal output to test 
    }

    public static Difficulty getDifficulty() { return difficulty; }

    public static void applyTo(Entity enemy) {
        double m = difficulty.getEnemyAttackMultiplier(); //gets respective multiplier from difficulty.java

        enemy.setAttackPower((int)Math.round(enemy.getAttackPower() * m)); //applies multiplier to enemies attackpower
       

        System.out.println("[Applier] Applied multiplier " + m + " to " + enemy.getName()); //terminal output to test system
    }
}
