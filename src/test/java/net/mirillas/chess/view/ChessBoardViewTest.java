package net.mirillas.chess.view;

import net.mirillas.chess.model.Location;
import net.mirillas.chess.pieces.Rook;
import net.mirillas.chess.simulation.ChessBoard;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChessBoardViewTest {

    final ChessBoardView chessBoardView = new ChessBoardView();

    @Test
    public void testThatTheChessBoardViewDrawsTheBoard() {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        ChessBoard chessBoard = new ChessBoard(8);
        chessBoardView.drawBoard(chessBoard, new Rook());

        assertThat(consoleOutput.toString()).contains(" 8 .  .  .  .  .  .  .  .  8");
        assertThat(consoleOutput.toString()).contains(" 7 .  .  .  .  .  .  .  .  7");
        assertThat(consoleOutput.toString()).contains(" 6 .  .  .  .  .  .  .  .  6");
        assertThat(consoleOutput.toString()).contains(" 5 .  .  .  .  .  .  .  .  5");
        assertThat(consoleOutput.toString()).contains(" 4 .  .  .  .  .  .  .  .  4");
        assertThat(consoleOutput.toString()).contains(" 3 .  .  .  .  .  .  .  .  3");
        assertThat(consoleOutput.toString()).contains(" 2 .  .  .  .  .  .  .  .  2");
        assertThat(consoleOutput.toString()).contains(" 1 R  .  .  .  .  .  .  .  1");
        assertThat(consoleOutput.toString()).contains("   A  B  C  D  E  F  G  H  ");
    }

    @Test
    public void testThatTheChessBoardViewDrawsTheBoardAtDifferentPositions() {
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));

        ChessBoard chessBoard = new ChessBoard(8);
        chessBoard.placePiece(new Location(4, 4));
        chessBoardView.drawBoard(chessBoard, new Rook());

        assertThat(consoleOutput.toString()).contains(" 5 .  .  .  .  R  .  .  .  5");
    }

    @Test
    public void testThatTheChessBoardViewDrawsTheBoardOfDifferentSizes() {
        for (int size = 3; size <= 8; size += 2) {
            ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(consoleOutput));

            ChessBoard chessBoard = new ChessBoard(size);
            int mid = size / 2;
            chessBoard.placePiece(new Location(mid, mid));
            chessBoardView.drawBoard(chessBoard, new Rook());

            for (int i = 0; i < size; i++) {
                String row = Integer.toString(size - i);
                for (int j = 0; j < size; j++) {
                    String file = Character.toString(j);
                    String expected = (((mid == i) && (mid == j)) ? " R " : " . ");
                    assertThat(consoleOutput.toString()).contains(expected);
                }
            }
        }
    }

}