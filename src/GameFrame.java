import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
    private StartMenuPanel startmenu;
    private CombatUI combatui;
    private SettingsMenuPanel settingsmenu;

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
