import javax.swing.*;
import java.awt.*;

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(GameFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        JLabel title = new JLabel("Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        // Create panel for buttons
        JLabel ButtonPanel = new JLabel();
        ButtonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        ButtonPanel.setBackground(Color.GRAY);
        ButtonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        // Start menu buttons
        JButton start = new JButton("Start");
        JButton settings = new JButton("Settings");
        JButton exit = new JButton("Exit");

        Font ButtonFont = new Font("Arial", Font.PLAIN, 20);
        start.setFont(ButtonFont);
        settings.setFont(ButtonFont);
        exit.setFont(ButtonFont);

        // Actions
        start.addActionListener(e -> frame.showGame());
        settings.addActionListener(e -> frame.showSettings());
        exit.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        ButtonPanel.add(start);
        ButtonPanel.add(settings);
        ButtonPanel.add(exit);

        add(ButtonPanel, BorderLayout.CENTER);

    }
}