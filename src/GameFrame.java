import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GameFrame extends JFrame {

    private StartMenuPanel startmenu;
    private CombatUI combatui;
    private SettingsMenuPanel settingsmenu;
    private Game game;
    private Round round;

    public GameFrame() {
        setTitle("Game");
<<<<<<< HEAD
        setSize(500, 350);
=======
        setSize(800, 600);
>>>>>>> 7cfcd9c04a5a30a01153bb7404a69a081325270c
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new CardLayout());

        this.combatui     = new CombatUI(this);
        this.startmenu    = new StartMenuPanel(this);
        this.settingsmenu = new SettingsMenuPanel(this);
        this.game         = new Game(this, combatui);

        add(startmenu, "Menu");
        add(combatui,  "Game");
        add(settingsmenu, "Settings");

        // Show menu first; difficulty is normal by default so start button works even if user never opens Settings.
        showMenu();
        setVisible(true);
    }

    public void startNewRun() {
        game.startNewRun();
    }

    public void setRound(Round r) { this.round = r; }
    public Round getRound() { return round; }

    public void showMenu() {
        ((CardLayout)getContentPane().getLayout()).show(getContentPane(), "Menu");
    }
    public void showGame() {
        ((CardLayout)getContentPane().getLayout()).show(getContentPane(), "Game");
    }
    public void showSettings() {
        ((CardLayout)getContentPane().getLayout()).show(getContentPane(), "Settings");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
