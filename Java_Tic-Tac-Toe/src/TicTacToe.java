import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private final JButton[][] buttons = new JButton[3][3];
    private String currentPlayer = "X";
    private boolean gameWon = false;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        setLocationRelativeTo(null);
        initializeBoard();
        System.out.println("Turn: " + currentPlayer);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameWon) return;

        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().isEmpty()) return;

        clickedButton.setText(currentPlayer);

        if (checkWin()) {
            gameWon = true;
            System.out.println("\n---\nPlayer [" + currentPlayer + "] wins!\n---");
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            resetGame();
        } else if (checkDraw()) {
            System.out.println("\n---\nDraw!\n---");
            JOptionPane.showMessageDialog(this, "Draw!");
            resetGame();
        } else {
            switchPlayer();
            System.out.println("Turn: " + currentPlayer);
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("X") ? "O" : "X";
    }

    private boolean checkWin() {
        // Sprawdź wiersze i kolumny
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(currentPlayer) &&
                    buttons[i][1].getText().equals(currentPlayer) &&
                    buttons[i][2].getText().equals(currentPlayer)) return true;

            if (buttons[0][i].getText().equals(currentPlayer) &&
                    buttons[1][i].getText().equals(currentPlayer) &&
                    buttons[2][i].getText().equals(currentPlayer)) return true;
        }

        // Sprawdź przekątne
        if (buttons[0][0].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][2].getText().equals(currentPlayer)) {
            return true;
        }

        return buttons[0][2].getText().equals(currentPlayer) &&
                buttons[1][1].getText().equals(currentPlayer) &&
                buttons[2][0].getText().equals(currentPlayer);
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().isEmpty()) return false;
            }
        }
        return true;
    }

    private void resetGame() {
        currentPlayer = "X";
        gameWon = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
