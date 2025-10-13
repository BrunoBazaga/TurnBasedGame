public class Game {

    public static void main(String[] args) {
        User player = new User("Hero", 100, 20, 10, 8);
        int wave = 1;

        while (player.getHealth() > 0 && OpponentPool.hasMore()) {
            System.out.println("\n=== Wave " + wave + " (remaining: " + OpponentPool.remaining() + ") ===");

            Entity enemy = OpponentPool.random();
            Round round = new Round();
            Round.Result result = round.start(player, enemy);

            if (result == Round.Result.PLAYER_DEAD) {
                System.out.println("Game Over! You reached wave " + wave + ".");
                break;
            }

            player.setHealth(100);

            wave++;
        }

        if (player.getHealth() > 0 && !OpponentPool.hasMore()) {
            System.out.println("\nYou defeated all opponents! 🎉");
        }
    }
}
