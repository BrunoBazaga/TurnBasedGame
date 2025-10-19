import java.awt.*;
import javax.swing.*;

public class CombatUI extends JPanel {
    private JLabel playerLabel, enemyLabel;
    private JProgressBar playerHPBar, enemyHPBar;
    private JLabel playerNameLabel, enemyNameLabel;
    private JButton attackButton, healButton;
    private JTextArea messageBox;

    private final GameFrame frame;

    public CombatUI(GameFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(205, 190, 190));

        // Enemy panel
        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.setOpaque(false);
        enemyPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

        enemyNameLabel = new JLabel("Opponent");
        enemyNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        enemyHPBar = new JProgressBar(0, 100);
        enemyHPBar.setValue(100);
        enemyHPBar.setForeground(new Color(0, 200, 0));
        enemyHPBar.setStringPainted(true);
        enemyHPBar.setPreferredSize(new Dimension(150, 15));
        enemyHPBar.setString("100 / 100");

        JPanel enemyInfo = new JPanel(new GridLayout(2, 1));
        enemyInfo.setOpaque(false);
        enemyInfo.add(enemyNameLabel);
        enemyInfo.add(enemyHPBar);

        enemyLabel = new JLabel(new ImageIcon(" "));
        enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        enemyPanel.add(enemyInfo, BorderLayout.WEST);
        enemyPanel.add(enemyLabel, BorderLayout.EAST);

        // Player panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setOpaque(false);
        playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        playerLabel = new JLabel(new ImageIcon("PlayerBACK.png"));
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        playerNameLabel = new JLabel("Player");
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        playerHPBar = new JProgressBar(0, 100);
        playerHPBar.setValue(100);
        playerHPBar.setForeground(new Color(0, 200, 0));
        playerHPBar.setStringPainted(true);
        playerHPBar.setPreferredSize(new Dimension(150, 15));
        playerHPBar.setString("100 / 100");

        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
        playerInfo.setOpaque(false);
        playerInfo.add(playerNameLabel);
        playerInfo.add(Box.createVerticalStrut(5));
        playerInfo.add(playerHPBar);

        attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            Round r = frame.getRound();
            if (r != null) {
                r.onPlayerClickAttack();
                syncFromRound();
            }
        });

        healButton = new JButton("Heal");
        healButton.addActionListener(e -> {
            Round r = frame.getRound();
            if (r != null) {
                r.onPlayerClickHeal();
                syncFromRound();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(attackButton);
        buttonPanel.add(healButton);

        JPanel playerRight = new JPanel(new BorderLayout());
        playerRight.setOpaque(false);
        playerRight.add(playerInfo, BorderLayout.EAST);
        playerRight.add(buttonPanel, BorderLayout.SOUTH);

        playerPanel.add(playerLabel, BorderLayout.WEST);
        playerPanel.add(playerRight, BorderLayout.CENTER);

        // Message box
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

        add(enemyPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);

    }

    // Set buttons enabled/disabled
    public void setButtonsEnabled(boolean enabled) {
        attackButton.setEnabled(enabled);
        healButton.setEnabled(enabled);
    }   

    // Initialize the UI with a fresh Round
    public void initFromRound(Round round) {
        if (round == null) return;
        playerNameLabel.setText(round.getPlayerName());
        enemyNameLabel.setText(round.getEnemyName());
        setPlayerMaxHP(round.getPlayer().getMaxHealth());
        setEnemyMaxHP(round.getEnemy().getMaxHealth());
        setPlayerHP(round.getPlayer().getHealth());
        setEnemyHP(round.getEnemy().getHealth());
        setMessage("A wild " + round.getEnemyName() + " appeared!");
    }

    private void syncFromRound() {
        Round round = frame.getRound();
        if (round == null) return;
        setPlayerHP(round.getPlayer().getHealth());
        setEnemyHP(round.getEnemy().getHealth());
    }

    // Flash the sprite of an entity
    public void flashSprite(JLabel spriteLabel) {
        final int[] count = {0};

        Timer flashTimer = new Timer(100, e -> {
            // toggle visibility
            spriteLabel.setVisible(!spriteLabel.isVisible());
            count[0]++;

            // stop after 6 toggles (3 flashes)
            if (count[0] >= 6) {
                ((Timer) e.getSource()).stop();
                spriteLabel.setVisible(true); // ensure visible at the end
            }
        });

        flashTimer.start();
    }

    public void setPlayerMaxHP(int max) {
        playerHPBar.setMaximum(max);
    }
    public void setEnemyMaxHP(int max) {
        enemyHPBar.setMaximum(max);
    }
    public void setPlayerHP(int hp) {
        playerHPBar.setValue(hp);
        playerHPBar.setString(hp + " / " + playerHPBar.getMaximum());
    }
    public void setEnemyHP(int hp) {
        enemyHPBar.setValue(hp);
        enemyHPBar.setString(hp + " / " + enemyHPBar.getMaximum());
    }
    public void setMessage(String text) {
        messageBox.setText(text);
    }
}
