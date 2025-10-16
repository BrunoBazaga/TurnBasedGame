import java.awt.*;
import javax.swing.*;

public class CombatUI extends JPanel {
    private JLabel playerLabel, enemyLabel;
    private JProgressBar playerHPBar, enemyHPBar;
    private JLabel playerNameLabel, enemyNameLabel;
    private JTextArea messageBox;

    public CombatUI(GameFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(205, 190, 190));

        //Enemy
        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.setOpaque(false);
        enemyPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

        enemyNameLabel = new JLabel("Opponent");
        enemyNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        enemyHPBar = new JProgressBar(0, 100);
        enemyHPBar.setValue(100);
        enemyHPBar.setStringPainted(true);
        enemyHPBar.setPreferredSize(new Dimension(150, 15)); // smaller HP bar

        JPanel enemyInfo = new JPanel(new GridLayout(2, 1));
        enemyInfo.setOpaque(false);
        enemyInfo.add(enemyNameLabel);
        enemyInfo.add(enemyHPBar);

        enemyLabel = new JLabel(new ImageIcon(" "));
        enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        enemyPanel.add(enemyInfo, BorderLayout.WEST);
        enemyPanel.add(enemyLabel, BorderLayout.EAST);

        //User
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
        playerHPBar.setPreferredSize(new Dimension(150, 15));

        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
        playerInfo.setOpaque(false);
        playerInfo.add(Box.createVerticalGlue());
        playerInfo.add(playerNameLabel);
        playerInfo.add(Box.createVerticalStrut(5));
        playerInfo.add(playerHPBar);
        playerInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);

       
        JButton attackButton = new JButton("Attack");
        attackButton.setPreferredSize(new Dimension(120, 40));
        attackButton.setFont(new Font("Arial", Font.BOLD, 16));
        attackButton.addActionListener(e -> frame.getRound().onPlayerClickAttack());


        JButton healButton = new JButton("Heal");
        healButton.setPreferredSize(new Dimension(120, 40));
        healButton.setFont(new Font("Arial", Font.BOLD, 16));
        healButton.addActionListener(e -> frame.getRound().onPlayerClickHeal());

        // makes buttons be side by side
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(attackButton);
        buttonPanel.add(healButton);

        // Combine player info (bottom right HP) and buttons below
        JPanel playerRightPanel = new JPanel(new BorderLayout());
        playerRightPanel.setOpaque(false);
        playerRightPanel.add(playerInfo, BorderLayout.EAST);
        playerRightPanel.add(buttonPanel, BorderLayout.SOUTH);

        playerPanel.add(playerLabel, BorderLayout.WEST);
        playerPanel.add(playerRightPanel, BorderLayout.CENTER);

       //message box
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

        //adds all panels
        add(enemyPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    
    public void setPlayerHP(int hp) {
        playerHPBar.setValue(Math.max(hp, 0));
    }

    public void setEnemyHP(int hp) {
        enemyHPBar.setValue(Math.max(hp, 0));
    }

    public void setMessage(String text) {
        messageBox.setText(text);
    }

    public void showAttackMessage(String attackerName, int damage) {
        setMessage(attackerName + " attacked for " + damage + " damage!");
    }

}
