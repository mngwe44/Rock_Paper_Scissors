import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private final JButton rockButton, paperButton, scissorsButton, quitButton;
    private final JTextField playerWinsField, computerWinsField, tiesField;
    private final JTextArea resultArea;
    private int playerWins = 0, computerWins = 0, ties = 0;
    private final Random random;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        quitButton = new JButton("Quit");

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(1, 6));
        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 3);
        playerWinsField.setEditable(false);
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 3);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 3);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        // Result Area
        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Add components to frame
        add(buttonPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Event Listeners
        rockButton.addActionListener(new GameAction("Rock"));
        paperButton.addActionListener(new GameAction("Paper"));
        scissorsButton.addActionListener(new GameAction("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        random = new Random();
    }

    private class GameAction implements ActionListener {
        private final String playerChoice;

        public GameAction(String choice) {
            this.playerChoice = choice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] choices = {"Rock", "Paper", "Scissors"};
            String computerChoice = choices[random.nextInt(3)];
            String result = determineWinner(playerChoice, computerChoice);
            resultArea.append(result + "\n");
            updateStats();
        }
    }

    private String determineWinner(String player, String computer) {
        if (player.equals(computer)) {
            ties++;
            return player + " vs " + computer + " - It's a tie!";
        }
        if ((player.equals("Rock") && computer.equals("Scissors")) ||
                (player.equals("Paper") && computer.equals("Rock")) ||
                (player.equals("Scissors") && computer.equals("Paper"))) {
            playerWins++;
            return player + " beats " + computer + " - You win!";
        }
        computerWins++;
        return computer + " beats " + player + " - Computer wins!";
    }

    private void updateStats() {
        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));
    }
}
