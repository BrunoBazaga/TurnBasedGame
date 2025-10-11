import javax.swing.*;
import java.awt.*;

public class SettingsMenuPanel extends JPanel {
    public SettingsMenuPanel(GameFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);  

        JLabel settingsTitle = new JLabel("Change difficulty", SwingConstants.CENTER);
        settingsTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        settingsTitle.setForeground(Color.WHITE);
        add(settingsTitle, BorderLayout.NORTH);

        // Create panle for buttons
        JLabel settingsPanel = new JLabel();
        settingsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        settingsPanel.setBackground(Color.GRAY);
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        // Settings menu buttons
        JButton easy = new JButton("Easy");
        JButton normal = new JButton("Normal");
        JButton hard = new JButton("Hard");
        JButton back = new JButton("Back");

        Font font = new Font("Monospaced", Font.PLAIN, 20);
        easy.setFont(font);
        normal.setFont(font);
        hard.setFont(font);
        back.setFont(font);

        // Actions
        easy.addActionListener(e -> frame.showMenu());
        normal.addActionListener(e -> frame.showMenu());
        hard.addActionListener(e -> frame.showMenu());
        back.addActionListener(e -> frame.showMenu());

        // Add buttons to settings panel
        settingsPanel.add(easy);
        settingsPanel.add(normal);
        settingsPanel.add(hard);
        settingsPanel.add(back);

        add(settingsPanel, BorderLayout.CENTER);
    }
}
