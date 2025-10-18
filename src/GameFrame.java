import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame {
    private StartMenuPanel startmenu;
    private CombatUI combatui;
    private SettingsMenuPanel settingsmenu;

    private Round round;
    private User player;
    private Entity enemy;

    public GameFrame() {
        setTitle("Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());

        // 1) Create player & enemy FIRST (assign to fields, not locals)
        this.player = new User("Hero", 100, 15, 6, 8);
        this.enemy  = OpponentPool.random();

        // Make sure current HPs are valid so the round isn’t "over" at start
        if (player.getHealth() <= 0) player.setHealth(100);                 // fallback
        if (enemy.getHealth()  <= 0) enemy.setHealth(Math.max(1, enemy.getMaxHealth()));

        // 2) Create Round BEFORE the UI (UI will be attached right after it’s built)
        this.round = new Round(this.player, this.enemy, /* combatUI */ null);

        // 3) Build UI panels AFTER round exists
        this.startmenu    = new StartMenuPanel(this);
        this.combatui     = new CombatUI(this);      // inside it, it uses frame.getRound()
        this.settingsmenu = new SettingsMenuPanel(this);

        // 4) Attach the UI to the round so the round can push updates safely
        this.round.attachUI(this.combatui);

        // 5) Add cards to the frame
        add(startmenu, "Menu");
        add(combatui,  "Game");
        add(settingsmenu, "Settings");

        // 6) Seed initial UI state from the model
        combatui.setPlayerMaxHP(player.getMaxHealth() > 0 ? player.getMaxHealth() : player.getHealth());
        combatui.setPlayerHP(player.getHealth());
        combatui.setEnemyMaxHP(enemy.getMaxHealth() > 0 ? enemy.getMaxHealth() : enemy.getHealth());
        combatui.setEnemyHP(enemy.getHealth());
        combatui.setMessage("A wild " + enemy.getName() + " appeared!");

        // 7) Show the screen you want first
        showMenu(); // or showGame();
        setVisible(true);
    }

    public Round getRound() {
        return round;
    }

    public void showMenu() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Menu");
    }

    public void showGame() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Game");
    }

    public void showSettings() {
        ((CardLayout) getContentPane().getLayout()).show(getContentPane(), "Settings");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
