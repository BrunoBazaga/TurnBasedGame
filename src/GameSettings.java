public class GameSettings {
    private static Difficulty difficulty = Difficulty.NORMAL;

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty newDifficulty) {
        difficulty = newDifficulty;
    }
}
