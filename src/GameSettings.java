public class GameSettings {
    private static Difficulty difficulty = Difficulty.NORMAL; // sets default difficulty to normal

    public static Difficulty getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(Difficulty newDifficulty) {
        difficulty = newDifficulty;
    }
}
