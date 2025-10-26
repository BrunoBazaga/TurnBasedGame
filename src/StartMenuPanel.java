import java.awt.*;
import javax.swing.*;

public class StartMenuPanel extends JPanel {
    private Image backgroundImage;

    public StartMenuPanel(GameFrame frame) {
        backgroundImage = new ImageIcon("src/images/wallpaper.jpg").getImage();

        setLayout(new BorderLayout());
        // setBackground(Color.GRAY);

        JLabel title = new JLabel("Welcome", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 20));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(100, 75));

        JButton start = new JButton("Start");
        JButton settings = new JButton("Settings");
        JButton exit = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        start.setFont(buttonFont);
        start.setBackground(new Color(0, 0, 0));
        start.setForeground(Color.WHITE);
        start.setOpaque(true);
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        settings.setFont(buttonFont);
        settings.setBackground(new Color(0, 0, 0));
        settings.setForeground(Color.WHITE);
        settings.setOpaque(true);
        settings.setBorderPainted(false);
        settings.setFocusPainted(false);
        exit.setFont(buttonFont);
        exit.setBackground(new Color(0, 0, 0));
        exit.setForeground(Color.WHITE);
        exit.setOpaque(true);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);

        // actions
        start.addActionListener(e -> {
            // starts new run of the game
            frame.startNewRun();
        });

        settings.addActionListener(e -> frame.showSettings());
        exit.addActionListener(e -> System.exit(0));

        // --- Add buttons to panel ---
        buttonPanel.add(start);
        buttonPanel.add(settings);
        buttonPanel.add(exit);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
