import java.awt.*;
import javax.swing.*;

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(GameFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        JLabel title = new JLabel("Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        //button panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JButton start = new JButton("Start");
        JButton settings = new JButton("Settings");
        JButton exit = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        start.setFont(buttonFont);
        settings.setFont(buttonFont);
        exit.setFont(buttonFont);

        //actions
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
}
