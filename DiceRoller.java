import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceRoller extends JFrame {
    private JLabel diceLabel;
    private JButton rollButton;
    private JTextArea historyArea;
    private ImageIcon[] diceFaces; // Array to store dice face images
    private List<Integer> rollHistory; // List to store roll history

    public DiceRoller() {
        // Set up the JFrame
        setTitle("Dice Roller");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add spacing between components
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Load dice face images (1-6)
        loadDiceFaces();

        // Initialize roll history
        rollHistory = new ArrayList<>();

        // Create components
        diceLabel = new JLabel(diceFaces[0], SwingConstants.CENTER); // Start with face 1
        diceLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        rollButton = new JButton("Roll Dice");
        rollButton.setFont(new Font("Arial", Font.BOLD, 16));
        rollButton.setBackground(new Color(70, 130, 180)); // Steel blue button
        rollButton.setForeground(Color.WHITE); // White text
        rollButton.setFocusPainted(false); // Remove focus border
        rollButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Arial", Font.PLAIN, 14));
        historyArea.setBackground(new Color(255, 255, 255)); // White background
        historyArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Border color
                BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding
        ));
        JScrollPane scrollPane = new JScrollPane(historyArea); // Add scroll bar

        // Add action listener to the button
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });

        // Create panels for layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        topPanel.add(diceLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        buttonPanel.add(rollButton);

        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setBackground(new Color(245, 245, 245)); // Light gray background
        historyPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        historyPanel.add(new JLabel("Roll History:"), BorderLayout.NORTH);
        historyPanel.add(scrollPane, BorderLayout.CENTER);

        // Add components to the JFrame
        add(topPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(historyPanel, BorderLayout.EAST);

        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    private void loadDiceFaces() {
        // Load images for dice faces (1-6)
        diceFaces = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            diceFaces[i] = new ImageIcon("dice_" + (i + 1) + ".png"); // Load images from files
        }
    }

    private void rollDice() {
        Random random = new Random();
        int diceValue = random.nextInt(6); // Random index between 0 and 5
        diceLabel.setIcon(diceFaces[diceValue]); // Update dice face

        // Add roll to history
        rollHistory.add(diceValue + 1); // Add 1 to convert index to dice value
        updateHistory();
    }

    private void updateHistory() {
        // Display roll history in the text area
        StringBuilder historyText = new StringBuilder();
        for (int i = rollHistory.size() - 1; i >= 0; i--) {
            historyText.append("Roll #").append(rollHistory.size() - i).append(": ").append(rollHistory.get(i)).append("\n");
        }
        historyArea.setText(historyText.toString());
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DiceRoller().setVisible(true);
            }
        });
    }
}