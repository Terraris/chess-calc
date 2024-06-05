package net.mirillas.chess.strategies;

import net.mirillas.chess.model.Location;
import net.mirillas.chess.pieces.Knight;
import net.mirillas.chess.simulation.ChessBoard;
import org.junit.jupiter.api.Test;

import static net.mirillas.chess.simulation.BoardManager.countThreatPositions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightThreateningStrategyTest {


    @Test
    public void testCalculateThreads() {
        Knight knight = new Knight();
        Location location = new Location(3, 3);

        ChessBoard chessBoard = new ChessBoard(8);
        knight.calculateThreads(chessBoard, location);

        long numThreatPositions = countThreatPositions(chessBoard);
        assertEquals(8, numThreatPositions);
    }

    @Test
    public void testCalculateThreadsAtEdges() {
        Knight knight = new Knight();
        Location[] edgePositions = {new Location(0, 3), new Location(7, 3), new Location(3, 0), new Location(3, 7)};
        int[] expectedThreats = {4, 4, 4, 4};

        for (int i = 0; i < edgePositions.length; i++) {
            ChessBoard chessBoard = new ChessBoard(8);
            knight.calculateThreads(chessBoard, edgePositions[i]);
            long numThreatPositions = countThreatPositions(chessBoard);
            assertEquals(expectedThreats[i], numThreatPositions);
        }

    }

    @Test
    public void testCalculateThreadsAtCorners() {
        Knight knight = new Knight();

        Location[] cornerPositions = {new Location(0, 0), new Location(7, 0), new Location(0, 7), new Location(7, 7)};
        int[] expectedThreats = {2, 2, 2, 2};

        for (int i = 0; i < cornerPositions.length; i++) {
            ChessBoard chessBoard = new ChessBoard(8);
            knight.calculateThreads(chessBoard, cornerPositions[i]);
            long numThreatPositions = countThreatPositions(chessBoard);
            assertEquals(expectedThreats[i], numThreatPositions);
        }
    }
}
