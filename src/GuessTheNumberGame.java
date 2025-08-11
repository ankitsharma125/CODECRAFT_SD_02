import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {
    private int randomNumber;
    private int attempts;
    private final JTextField guessField;
    private final JLabel feedbackLabel;
    private final JButton guessButton, resetButton;

    public GuessTheNumberGame() {
        setTitle("Number Guessing Game");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(4, 1, 5, 5));

        // Generate first random number
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;

        // Input Panel
        JPanel inputPanel = new JPanel();
        JLabel promptLabel = new JLabel("Enter your guess between 1 to 100");
        promptLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        guessField = new JTextField(6);
        guessField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(promptLabel);
        inputPanel.add(guessField);
        add(inputPanel);

        // Feedback Label
        feedbackLabel = new JLabel("Make a guess!", SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        feedbackLabel.setForeground(Color.BLUE);
        add(feedbackLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.setFocusable(false);
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setFocusable(false);
        buttonPanel.add(guessButton);
        buttonPanel.add(resetButton);
        add(buttonPanel);

        // Guess Button Action
        guessButton.addActionListener(e -> {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if (guess < 1 || guess > 100) {
                    feedbackLabel.setText("Enter a number between 1 and 100.");
                    feedbackLabel.setForeground(Color.RED);
                } else if (guess < randomNumber) {
                    feedbackLabel.setText("Too Low! Try again.");
                    feedbackLabel.setForeground(Color.BLUE);
                } else if (guess > randomNumber) {
                    feedbackLabel.setText(" Too High! Try again.");
                    feedbackLabel.setForeground(Color.BLUE);
                } else {
                    feedbackLabel.setText("Correct ! Total " + attempts + " attempts taken to guess");
                    feedbackLabel.setForeground(Color.GREEN); // Green
                    guessButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("⚠ Please enter a valid number.");
                feedbackLabel.setForeground(Color.RED);
            }
            guessField.setText("");
        });

        // Reset Button Action
        resetButton.addActionListener(e -> {
            randomNumber = new Random().nextInt(100) + 1;
            attempts = 0;
            feedbackLabel.setText("Make a guess!");
            feedbackLabel.setForeground(Color.BLUE);
            guessButton.setEnabled(true);
            guessField.setText("");
        });

        setVisible(true);
    }
}