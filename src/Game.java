import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game implements Round.Listener {

    private final GameFrame frame;
    private final CombatUI combatUI;
    private User player;
    private Round currentRound;

    private static final double BETWEEN_FIGHTS_HEAL_RATIO = 1; 

    public Game(GameFrame frame, CombatUI combatUI) {
        this.frame = frame;
        this.combatUI = combatUI;
    }

    //starts game when start button is pressed
    public void startNewRun() {
        this.player = new User("Hero", 1000, 150, 100, 8);  
        OpponentPool.reset(); //ensures all opponents are available for selection
        startNextBattle();
    }

    public void startNextBattle() { //starts the next round. If no opponents, left, returns victory message
        if (!OpponentPool.hasMore()) {
            showVictoryScreenAndExit();
            return;
        }

        Entity enemy = OpponentPool.random(); // selects opponent
        currentRound = new Round(player, enemy, null); //creates instance of round
        currentRound.attachUI(combatUI);//links round to combat ui
        currentRound.setListener(this); //initiates listener

        combatUI.initFromRound(currentRound);
        frame.setRound(currentRound);
        frame.showGame();
    }

    @Override
    public void onBattleEnded(boolean playerWon) {
        if (!playerWon) {
            showDefeatScreenAndExit();
            return;
        }

        int heal = (int)Math.round(player.getMaxHealth() * BETWEEN_FIGHTS_HEAL_RATIO); //heals user health back to max health after every round
        player.setHealth(Math.min(player.getHealth() + heal, player.getMaxHealth())); //ensures health does not go over maxhealth
        SwingUtilities.invokeLater(this::startNextBattle); //starts new battle
    }

    private void showVictoryScreenAndExit() {
        JOptionPane.showMessageDialog(frame, "You defeated all opponents. Victory!");
        System.exit(0); // close the program completely
    }

    private void showDefeatScreenAndExit() {
        JOptionPane.showMessageDialog(frame, "You were defeated. Game Over.");
        System.exit(0); // close the program completely
    }

    public Round getCurrentRound() { return currentRound; }
}
