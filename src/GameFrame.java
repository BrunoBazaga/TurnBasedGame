import java.awt.*;
import javax.swing.*;


public class GameFrame extends JFrame {
    private StartMenuPanel startmenu;
    private CombatUI combatui;
    private SettingsMenuPanel settingsmenu;
    private Round round;    

    public GameFrame() {
        setTitle("Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        startmenu = new StartMenuPanel(this);
        combatui = new CombatUI(this);
        settingsmenu = new SettingsMenuPanel(this);

        add(startmenu, "Menu");
        add(combatui, "Game");
        add(settingsmenu, "Settings");

        showMenu();
        setVisible(true);

        User player = new User("Hero", 100,15,6,8);
        Entity enemy = OpponentPool.random();

        round = new Round(player, enemy, combatui);
    }

    public Round getRound(){
        return round;
    }

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
