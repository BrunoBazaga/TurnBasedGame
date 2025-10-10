
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public final class OpponentPool {
    private static final Random RNG = new Random();

    private static final List<Supplier<Entity>> OPPONENTS  = new ArrayList<>(List.of(
        () -> new Entity("Goblin", 50, 10, 5, 8),
        () -> new Entity("Knight", 90, 18, 12, 6),
        () -> new Entity("Assassin", 60, 22, 4, 12),
        () -> new Entity("Dragon", 200, 40, 20, 7)
    ));

    public static Entity random() {
        return OPPONENTS.get(RNG.nextInt(OPPONENTS.size())).get();

    }
}
