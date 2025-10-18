public enum Difficulty {
    EASY(0.8),
    NORMAL(1.0),
    HARD(1.2);

    private final double enemyAttackMultiplier;

    Difficulty(double multiplier) {
        this.enemyAttackMultiplier = multiplier;
    }

    public double getEnemyAttackMultiplier() {
        return enemyAttackMultiplier;
    }
}
