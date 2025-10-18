import java.awt.*;
import javax.swing.*;

public class SettingsMenuPanel extends JPanel {
    public SettingsMenuPanel(GameFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.GRAY);

        JLabel title = new JLabel("Change Difficulty", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBackground(Color.GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JButton easy = new JButton("Easy");
        JButton normal = new JButton("Normal");
        JButton hard = new JButton("Hard");
        JButton back = new JButton("Back");

        Font font = new Font("Monospaced", Font.PLAIN, 20);
        easy.setFont(font);
        normal.setFont(font);
        hard.setFont(font);
        back.setFont(font);

        
        easy.addActionListener(e -> DifficultyApplier.setDifficulty(Difficulty.EASY));
        normal.addActionListener(e -> DifficultyApplier.setDifficulty(Difficulty.NORMAL));
        hard.addActionListener(e -> DifficultyApplier.setDifficulty(Difficulty.HARD));
        back.addActionListener(e -> frame.showMenu());

        panel.add(easy);
        panel.add(normal);
        panel.add(hard);
        panel.add(back);
        add(panel, BorderLayout.CENTER);
    }
}
