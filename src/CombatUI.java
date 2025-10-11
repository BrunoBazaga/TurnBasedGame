import javax.swing.*;
import java.awt.*;

public class CombatUI extends JPanel {
    private JLabel playerLabel, enemyLabel;
    private JProgressBar playerHPBar, enemyHPBar;
    private JLabel playerNameLabel, enemyNameLabel;
    private JTextArea messageBox;

    public CombatUI(GameFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(205, 190, 190));

        // Enemy section
        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.setOpaque(false);
        enemyPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

        enemyNameLabel = new JLabel("Opponent");
        enemyNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        enemyHPBar = new JProgressBar(0, 100);
        enemyHPBar.setValue(100);
        enemyHPBar.setStringPainted(true);

        JPanel enemyInfo = new JPanel(new GridLayout(2, 1));
        enemyInfo.setOpaque(false);
        enemyInfo.add(enemyNameLabel);
        enemyInfo.add(enemyHPBar);

        enemyLabel = new JLabel(new ImageIcon(" "));
        enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        enemyPanel.add(enemyInfo, BorderLayout.WEST);
        enemyPanel.add(enemyLabel, BorderLayout.EAST);

        // Player section
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setOpaque(false);
        playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        playerLabel = new JLabel(new ImageIcon(" "));
        playerLabel.setHorizontalAlignment(SwingConstants.LEFT);

        playerNameLabel = new JLabel("Player");
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        playerHPBar = new JProgressBar(0, 100);
        playerHPBar.setValue(100);
        playerHPBar.setStringPainted(true);

        JPanel playerInfo = new JPanel(new GridLayout(2, 1));
        playerInfo.setOpaque(false);
        playerInfo.add(playerNameLabel);
        playerInfo.add(playerHPBar);

        playerPanel.add(playerLabel, BorderLayout.WEST);
        playerPanel.add(playerInfo, BorderLayout.EAST);

        // Message box section
        messageBox = new JTextArea("A wild enemy appeared!");
        messageBox.setEditable(false);
        messageBox.setLineWrap(true);
        messageBox.setWrapStyleWord(true);
        messageBox.setFont(new Font("Monospaced", Font.PLAIN, 14));
        messageBox.setBackground(Color.WHITE);
        messageBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        messageBox.setMargin(new Insets(10, 10, 10, 10));

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        messagePanel.add(messageBox, BorderLayout.CENTER);

        // Add all panels
        add(enemyPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    // Update healthbar
    public void setPlayerHP(int hp) {
        playerHPBar.setValue(Math.max(hp, 0));
    }

    public void setEnemyHP(int hp) {
        enemyHPBar.setValue(Math.max(hp, 0));
    }

    // Update message box
    public void setMessage(String text) {
        messageBox.setText(text);
    }
}
