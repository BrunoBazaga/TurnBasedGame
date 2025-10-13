public class Game {

    public static void main(String[] args) {
        User player = new User("Hero", 100, 20, 10, 8); //initialises user and stats
        int wave = 1; //starts in the first round

        //Rounds logic. Rounds continue until user dies or all opponents are defeated.
        while (player.getHealth() > 0 && OpponentPool.hasMore()) {
            System.out.println("\n=== Wave " + wave + " (remaining: " + OpponentPool.remaining() + ") ===");

            Entity enemy = OpponentPool.random(); // selects a random opponent from the opponent pool
            Round round = new Round(); //initialises round
            Round.Result result = round.start(player, enemy);

            if (result == Round.Result.PLAYER_DEAD) {
                System.out.println("Game Over! You reached wave " + wave + ".");    // if the player dies, the game ends.
                break;
            }

            player.setHealth(100); //resets user health to 100 after the end of every round.

            wave++; 
        }

        if (player.getHealth() > 0 && !OpponentPool.hasMore()) {
            System.out.println("\nYou defeated all opponents! 🎉"); //printed if the user defeats all opponents.
        }
    }
}
