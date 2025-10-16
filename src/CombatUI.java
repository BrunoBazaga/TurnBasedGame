import java.awt.*;
import javax.swing.*;

public class CombatUI extends JPanel {
    private JLabel playerLabel, enemyLabel;
    private JProgressBar playerHPBar, enemyHPBar;
    private JLabel playerNameLabel, enemyNameLabel;
    private JTextArea messageBox;

    private final GameFrame frame;
    private final Round round;

    public CombatUI(GameFrame frame) {
        this.frame = frame;
        this.round = frame.getRound(); // <- use the SAME Round instance the frame created

        setLayout(new BorderLayout());
        setBackground(new Color(205, 190, 190));

        // --- Enemy ---
        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.setOpaque(false);
        enemyPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

        String enemyName = (round != null && round.getEnemy() != null) ? round.getEnemy().getName() : "Opponent";
        int enemyMax = (round != null && round.getEnemy() != null) ? Math.max(1, round.getEnemy().getMaxHealth()) : 100;
        int enemyHp = (round != null && round.getEnemy() != null) ? Math.max(0, round.getEnemy().getHealth())
                : enemyMax;

        enemyNameLabel = new JLabel(enemyName);
        enemyNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        enemyHPBar = new JProgressBar(0, enemyMax);
        enemyHPBar.setValue(enemyHp);
        enemyHPBar.setStringPainted(true);
        enemyHPBar.setPreferredSize(new Dimension(150, 15));
        enemyHPBar.setString(enemyHp + " / " + enemyMax);

        JPanel enemyInfo = new JPanel(new GridLayout(2, 1));
        enemyInfo.setOpaque(false);
        enemyInfo.add(enemyNameLabel);
        enemyInfo.add(enemyHPBar);

        enemyLabel = new JLabel(); // placeholder for an image if you want
        enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        enemyPanel.add(enemyInfo, BorderLayout.WEST);
        enemyPanel.add(enemyLabel, BorderLayout.EAST);

        // --- Player ---
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setOpaque(false);
        playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        playerLabel = new JLabel();
        playerLabel.setHorizontalAlignment(SwingConstants.LEFT);

        String playerName = (round != null && round.getPlayer() != null) ? round.getPlayer().getName() : "Player";
        int playerMax = (round != null && round.getPlayer() != null) ? Math.max(1, round.getPlayer().getMaxHealth())
                : 100;
        int playerHp = (round != null && round.getPlayer() != null) ? Math.max(0, round.getPlayer().getHealth())
                : playerMax;

        playerNameLabel = new JLabel(playerName);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        playerHPBar = new JProgressBar(0, playerMax);
        playerHPBar.setValue(playerHp);
        playerHPBar.setStringPainted(true);
        playerHPBar.setPreferredSize(new Dimension(150, 15));
        playerHPBar.setString(playerHp + " / " + playerMax);

        JPanel playerInfo = new JPanel();
        playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.Y_AXIS));
        playerInfo.setOpaque(false);
        playerInfo.add(Box.createVerticalGlue());
        playerInfo.add(playerNameLabel);
        playerInfo.add(Box.createVerticalStrut(5));
        playerInfo.add(playerHPBar);
        playerInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JButton attackButton = new JButton("Attack");
        attackButton.addActionListener(e -> {
            System.out.println("[UI] Attack clicked");
            Round r = frame.getRound();
            System.out.println("[UI] frame.getRound() = " + r);
            if (r != null) {
                System.out.println("[UI] Round phase before: " + r.getPhase());
                r.onPlayerClickAttack();
                System.out.println("[UI] Round phase after: " + r.getPhase());
                syncFromRound();
            }
        });

        JButton healButton = new JButton("Heal");
        healButton.addActionListener(e -> {
            System.out.println("[UI] Heal clicked");
            Round r = frame.getRound();
            System.out.println("[UI] frame.getRound() = " + r);
            if (r != null) {
                System.out.println("[UI] Round phase before: " + r.getPhase());
                r.onPlayerClickHeal();
                System.out.println("[UI] Round phase after: " + r.getPhase());
                syncFromRound();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(attackButton);
        buttonPanel.add(healButton);

        JPanel playerRightPanel = new JPanel(new BorderLayout());
        playerRightPanel.setOpaque(false);
        playerRightPanel.add(playerInfo, BorderLayout.EAST);
        playerRightPanel.add(buttonPanel, BorderLayout.SOUTH);

        playerPanel.add(playerLabel, BorderLayout.WEST);
        playerPanel.add(playerRightPanel, BorderLayout.CENTER);

        // --- Message box ---
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

        // --- Add panels ---
        add(enemyPanel, BorderLayout.NORTH);
        add(playerPanel, BorderLayout.CENTER);
        add(messagePanel, BorderLayout.SOUTH);
    }

    /** Pull current values from Round and reflect them in the UI. */
    private void syncFromRound() {
        if (round == null)
            return;

        // Player
        if (round.getPlayer() != null) {
            int max = Math.max(1, round.getPlayer().getMaxHealth());
            int hp = Math.max(0, Math.min(round.getPlayer().getHealth(), max));
            if (playerHPBar.getMaximum() != max)
                playerHPBar.setMaximum(max);
            playerHPBar.setValue(hp);
            playerHPBar.setString(hp + " / " + max);
        }

        // Enemy
        if (round.getEnemy() != null) {
            int max = Math.max(1, round.getEnemy().getMaxHealth());
            int hp = Math.max(0, Math.min(round.getEnemy().getHealth(), max));
            if (enemyHPBar.getMaximum() != max)
                enemyHPBar.setMaximum(max);
            enemyHPBar.setValue(hp);
            enemyHPBar.setString(hp + " / " + max);
        }

        // Optional: basic status message
        if (round.isOver()) {
            if (round.getPlayer() != null && round.getPlayer().getHealth() <= 0) {
                setMessage("You were defeated!");
            } else if (round.getEnemy() != null && round.getEnemy().getHealth() <= 0) {
                setMessage("Enemy defeated!");
            }
        }
    }

    // Public setters if you want to update manually elsewhere
    public void setPlayerMaxHP(int max) {
        playerHPBar.setMaximum(Math.max(1, max));
        playerHPBar.setString(playerHPBar.getValue() + " / " + playerHPBar.getMaximum());
    }

    public void setEnemyMaxHP(int max) {
        enemyHPBar.setMaximum(Math.max(1, max));
        enemyHPBar.setString(enemyHPBar.getValue() + " / " + enemyHPBar.getMaximum());
    }

    public void setPlayerHP(int hp) {
        int clamped = Math.max(0, Math.min(hp, playerHPBar.getMaximum()));
        playerHPBar.setValue(clamped);
        playerHPBar.setString(clamped + " / " + playerHPBar.getMaximum());
    }

    public void setEnemyHP(int hp) {
        int clamped = Math.max(0, Math.min(hp, enemyHPBar.getMaximum()));
        enemyHPBar.setValue(clamped);
        enemyHPBar.setString(clamped + " / " + enemyHPBar.getMaximum());
    }

    public void setMessage(String text) {
        messageBox.setText(text);
    }
}
