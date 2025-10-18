
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public final class OpponentPool {
    private static final Random RNG = new Random();

    private static final List<Supplier<Entity>> OPPONENTS = new ArrayList<>(List.of(
            () -> new Entity("Goblin", 50, 10, 5, 8),
            () -> new Entity("Knight", 90, 18, 12, 6),
            () -> new Entity("Assassin", 60, 22, 4, 12),
            () -> new Entity("Dragon", 200, 40, 20, 7)));

    private static final List<Supplier<Entity>> defeatedOpponents = new ArrayList<>(OPPONENTS);

    public static boolean hasMore() {
        return !defeatedOpponents.isEmpty();
    }// determines if there are opponents left

    public static int remaining() {
        return defeatedOpponents.size();
    }; // returns amount of defeated opponents

    public static Entity random() {
        if (defeatedOpponents.isEmpty())
            throw new IllegalStateException("No more opponents");
        int i = RNG.nextInt(defeatedOpponents.size()); //generates a random number within the opponents
        Entity e = defeatedOpponents.remove(i).get();//retrieves selected opponent
        System.out.println("[Pool] Spawned: " + e.getName());
        DifficultyApplier.applyTo(e); // applies multiplier to the newly spawned opponent
        return e; //returns enemy as entity 
    }

    public static void reset() {
        defeatedOpponents.clear(); //empties list 
        defeatedOpponents.addAll(OPPONENTS); //adds all opponents back to the list
    }
}
