import javax.swing.*;
import java.awt.*;

public class HPBar extends JComponent {
    private int currentHP;
    private int maxHP;

    public HPBar(int currentHP, int maxHP) {
        this.currentHP = currentHP;
        this.maxHP = maxHP;
        setPreferredSize(new Dimension(150, 20));
    }

    public void setHP(int currentHP) {
        this.currentHP = Math.max(0, Math.min(currentHP, maxHP));
        repaint();
    }

    public int getHP() {
        return currentHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
        repaint();
    }

    public int getMaxHP() {
        return maxHP;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Anti-aliasing for smoother edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw black border
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        // Inner background (gray)
        g2.setColor(new Color(60, 60, 60));
        g2.fillRect(2, 2, width - 4, height - 4);

        // Calculate filled width
        int barWidth = (int) ((double) currentHP / maxHP * (width - 4));

        // Draw green HP bar
        g2.setColor(new Color(0, 200, 0)); // classic Pokémon green
        g2.fillRect(2, 2, barWidth, height - 4);

        // Draw HP text (centered)
        String hpText = "HP: " + currentHP + " / " + maxHP;
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(hpText);
        int textHeight = fm.getAscent();

        g2.setColor(Color.WHITE);
        g2.drawString(hpText, (width - textWidth) / 2, (height + textHeight / 2) - 2);

        g2.dispose();
    }
}
