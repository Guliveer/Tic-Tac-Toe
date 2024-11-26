import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[][] buttons = new JButton[3][3];
    private String currentPlayer = "X";
    private boolean gameWon = false;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe"); // Window title
        setSize(400, 400); // Window size
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Close operation
        setLayout(new GridLayout(3, 3)); // Layout
        setLocationRelativeTo(null); // Center window
        initializeBoard();
        System.out.println("Turn: " + currentPlayer); // Log initial player
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true); // Show window
        });
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(""); // Create button with empty text
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60)); // Set font properties
                buttons[i][j].setFocusPainted(false); // Remove focus border
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]); // Add button to window
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWon) return; // if game is already won: do nothing

        JButton clickedButton = (JButton) e.getSource(); // Get clicked button
        if (!clickedButton.getText().isEmpty()) return; // if button is already clicked (i.e. not empty): do nothing

        clickedButton.setText(currentPlayer); // Set button text to current player

        if (checkWin()) {
            gameWon = true;
            System.out.println("\n---\nPlayer [" + currentPlayer + "] wins!\n---\n");
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetGame();
        } else if (checkDraw()) {
            System.out.println("\n---\nDraw!\n---\n");
            JOptionPane.showMessageDialog(this, "Draw!");
            resetGame();
        } else {
            switchPlayer();
            System.out.println("Turn: " + currentPlayer);
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // if current player is X: switch to O; else: switch to X
    }

    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (buttons[i][0].getText().equals(currentPlayer) &&
                    buttons[i][1].getText().equals(currentPlayer) &&
                    buttons[i][2].getText().equals(currentPlayer)) return true;

            // Check columns
            if (buttons[0][i].getText().equals(currentPlayer) &&
                    buttons[1][i].getText().equals(currentPlayer) &&
                    buttons[2][i].getText().equals(currentPlayer)) return true;
        }

        // Check diagonals (top-left to bottom-right)
        if (buttons[0][0].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][2].getText().equals(currentPlayer)) return true;

        // Check diagonals (top-right to bottom-left)
        return buttons[0][2].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][0].getText().equals(currentPlayer);
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) return false; // if any button is empty: game is not draw
            }
        }
        return true;
    }

    private void resetGame() {
        currentPlayer = "X";
        gameWon = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Set button text to empty
            }
        }
    }
}
