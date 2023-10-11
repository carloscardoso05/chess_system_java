package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        UI.printBoard(match.getPieces());
        ChessPosition source = UI.readChessPosition(sc);
        ChessPosition target = UI.readChessPosition(sc);
        ChessPiece piece = match.performChessMove(source, target);
        System.out.println(piece);
        UI.printBoard(match.getPieces());
        sc.close();
    }
}