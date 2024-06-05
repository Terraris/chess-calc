package net.mirillas.chess.model;

import net.mirillas.chess.simulation.ChessBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChessBoardTest {
    private static final int SIZE = 8;
    private ChessBoard chessBoard;

    @BeforeEach
    public void setup() {
        chessBoard = new ChessBoard(SIZE);
    }

    @Test
    public void testSize() {
        assertEquals(SIZE, chessBoard.getSize(), "Chess board size should be equal to the size it is initialized with");
    }

    @Test
    public void testPlacePiece() {
        Location location = new Location(1, 1);
        Location anotherLocation = new Location(2, 1);
        chessBoard.placePiece(location);
        assertTrue(chessBoard.isLocationOccupied(location), "Location should be occupied after placing piece");
        assertFalse(chessBoard.isLocationOccupied(anotherLocation), "Location should not be occupied after placing piece");
    }

    @Test
    public void testAddLocationUnderThreat() {
        Location location = new Location(2, 2);
        Location anotherLocation = new Location(2, 1);

        chessBoard.addLocationUnderThreat(location);
        assertTrue(chessBoard.isLocationUnderThreat(location), "Location should be under threat after it is added");
        assertFalse(chessBoard.isLocationUnderThreat(anotherLocation), "Location should not be under threat after another thread is added");
    }

    @Test
    public void chessBoardInitializationTest() {
        assertAll("Checking ChessBoard initialization",
                () -> assertNotNull(chessBoard.getCellsUnderThreat(),
                        "CellsUnderThreat should not be null after initialization"),
                () -> assertNotNull(chessBoard.getOccupiedLocation(),
                        "OccupiedLocation should not be null after initialization"),
                () -> assertEquals(new Location(0, 0), chessBoard.getOccupiedLocation(),
                        "OccupiedLocation should be (0,0) after initialization")
        );
    }

}
