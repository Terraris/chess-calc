package net.mirillas.chess.model;

import net.mirillas.chess.simulation.ChessBoard;
import org.junit.jupiter.api.Test;

import static net.mirillas.chess.simulation.BoardManager.countThreatPositions;
import static net.mirillas.chess.simulation.BoardManager.isWithinBoardBoundaries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardManagerTest {

    @Test
    public void testIsWithinBoardBoundaries() {
        int size = 8;

        assertTrue(isWithinBoardBoundaries(0, 0, size));
        assertFalse(isWithinBoardBoundaries(8, 8, size));
    }

    @Test
    public void testCountThreatPositions() {
        ChessBoard chessBoard = new ChessBoard(3);
        chessBoard.addLocationUnderThreat(new Location(0, 1));
        chessBoard.addLocationUnderThreat(new Location(1, 0));
        chessBoard.addLocationUnderThreat(new Location(1, 2));
        chessBoard.addLocationUnderThreat(new Location(3, 1));

        long count = countThreatPositions(chessBoard);
        assertEquals(4, count);
    }

    @Test
    public void testIsWithinBoardBoundariesForNegativeCoordinates() {
        int size = 8;
        assertFalse(isWithinBoardBoundaries(-1, 0, size));
        assertFalse(isWithinBoardBoundaries(0, -1, size));
        assertFalse(isWithinBoardBoundaries(-1, -1, size));
    }

    @Test
    public void testIsWithinBoardBoundariesForBorders() {
        int size = 8;
        assertTrue(isWithinBoardBoundaries(7, 7, size));
        assertTrue(isWithinBoardBoundaries(0, 7, size));
        assertTrue(isWithinBoardBoundaries(7, 0, size));
    }

    @Test
    public void testIsWithinBoardBoundariesForOutsideBorders() {
        int size = 8;
        assertFalse(isWithinBoardBoundaries(8, 7, size));
        assertFalse(isWithinBoardBoundaries(7, 8, size));
        assertFalse(isWithinBoardBoundaries(8, 8, size));
    }

    @Test
    public void testCountThreatPositionsForEmptyBoard() {
        ChessBoard chessBoard = new ChessBoard(3);
        long count = countThreatPositions(chessBoard);
        assertEquals(0, count);
    }

    @Test
    public void testCountThreatPositionsForFullBoard() {
        ChessBoard chessBoard = new ChessBoard(3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                chessBoard.addLocationUnderThreat(new Location(i, j));
            }
        }
        long count = countThreatPositions(chessBoard);
        assertEquals(9, count);
    }
}
