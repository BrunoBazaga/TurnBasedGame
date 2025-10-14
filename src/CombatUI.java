import javax.swing.*;
import java.awt.*;

public class CombatUI extends JPanel {
    private JLabel playerLabel, enemyLabel;
    private HPBar playerHPBar, enemyHPBar;
    private JLabel playerNameLabel, enemyNameLabel;
    private JTextArea messageBox;
    private Entity player, enemy;

    public CombatUI(GameFrame frame) {
        this.player = player;
        this.enemy = enemy;

        setLayout(new BorderLayout());
        setBackground(new Color(205, 190, 190));

        // Enemy section
        JPanel enemyPanel = new JPanel(new BorderLayout());
        enemyPanel.setOpaque(false);
        enemyPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 40));

        enemyNameLabel = new JLabel("Opponent");
        enemyNameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        enemyHPBar = new HPBar(enemy.getHealth(), enemy.getMaxHealth());

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

        playerHPBar = new HPBar(player.getHealth(), player.getMaxHealth());


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
        playerHPBar.setHealth(hp);
    }

    public void setEnemyHP(int hp) {
        enemyHPBar.setHealth(hp);
    }

    // Update message box
    public void setMessage(String text) {
        messageBox.setText(text);
    }

    private static class HPBar extends JComponent {
        private int health;
        private int maxHealth;

        public HPBar(int health, int maxHealth) {
            this.maxHealth = maxHealth;
            this.health = health;
            setPreferredSize(new Dimension(180, 25));
        }

        public void setHealth(int health) {
            this.health = health;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            // Outline box
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, width, height);

            // Inner background
            g2.setColor(new Color(240, 240, 220));
            g2.fillRect(2, 2, width - 4, height - 4);

            // HP label
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.setColor(new Color(220, 150, 30));
            g2.drawString("HP", 6, height - 7);

            // HP bar
            int barX = 35;
            int barY = 5;
            int barWidth = width - barX - 8;
            int barHeight = height - 10;

            double ratio = (double) health / maxHealth;
            int filledWidth = (int) (barWidth * ratio);

            // HP bar background
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(barX, barY, barWidth, barHeight);

            // HP bar fill
            g2.setColor(new Color(60, 200, 60));
            g2.fillRect(barX, barY, filledWidth, barHeight);

            // HP border
            g2.setColor(Color.BLACK);
            g2.drawRect(barX, barY, barWidth, barHeight);
        }
    }
}
