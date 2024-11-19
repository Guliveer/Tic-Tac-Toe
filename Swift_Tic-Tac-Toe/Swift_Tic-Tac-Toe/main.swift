//
//  main.swift
//  Swift_Tic-Tac-Toe
//
//  Created by Oliwer Pawelski on 21/11/2024.
//

import Foundation

class TicTacToe {
    private var board: [[String]]
    private var currentPlayer: String
    
    init() {
        board = Array(repeating: Array(repeating: " ", count: 3), count: 3)
        currentPlayer = "X"
    }
    
    func play() {
        print("Tic-Tac-Toe")
        printBoard()
        
        while true {
            print("\nPlayer \(currentPlayer), select your move (row col, e.g. 1 2):")
            if let input = readLine(), let (row, col) = parseInput(input) {
                if makeMove(row: row, col: col) {
                    printBoard()
                    
                    if checkWin() {
                        print("Player \(currentPlayer) wins!")
                        break
                    } else if checkDraw() {
                        print("Draw!")
                        break
                    }
                    
                    switchPlayer()
                } else {
                    print("Wrong move. Try again.")
                }
            } else {
                print("Wrong format. Try again.")
            }
        }
    }
    
    private func printBoard() {
        print("\n  0 1 2")
        for (i, row) in board.enumerated() {
            print("\(i) \(row.map { $0 }.joined(separator: "|"))")
            if i < 2 { print("  -----") }
        }
    }
    
    private func parseInput(_ input: String) -> (Int, Int)? {
        let components = input.split(separator: " ").compactMap { Int($0) }
        if components.count == 2 && components[0] >= 0 && components[0] < 3 && components[1] >= 0 && components[1] < 3 {
            return (components[0], components[1])
        }
        return nil
    }
    
    private func makeMove(row: Int, col: Int) -> Bool {
        if board[row][col] == " " {
            board[row][col] = currentPlayer
            return true
        }
        return false
    }
    
    private func switchPlayer() {
        currentPlayer = (currentPlayer == "X") ? "O" : "X"
    }
    
    private func checkWin() -> Bool {
        // Sprawdź wiersze, kolumny i przekątne
        for i in 0..<3 {
            if board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer { return true }
            if board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer { return true }
        }
        if board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer { return true }
        if board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer { return true }
        return false
    }
    
    private func checkDraw() -> Bool {
        return board.flatMap { $0 }.allSatisfy { $0 != " " }
    }
}

// Uruchomienie gry
let game = TicTacToe()
game.play()


