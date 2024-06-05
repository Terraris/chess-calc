package net.mirillas.chess.strategies;

import net.mirillas.chess.model.Location;
import net.mirillas.chess.pieces.Rook;
import net.mirillas.chess.simulation.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookThreateningStrategyTest {

    @Test
    public void testCalculateThreads() {
        ChessBoard board = new ChessBoard(8);
        Rook rook = new Rook();
        Location location = new Location(0, 0);

        rook.calculateThreads(board, location);

        for (int i = 1; i < 8; i++) {
            assertTrue(board.isLocationUnderThreat(new Location(0, i)));
            assertTrue(board.isLocationUnderThreat(new Location(i, 0)));
        }
    }

    @Test
    public void testCalculateThreadsAtCenter() {
        ChessBoard board = new ChessBoard(8);
        Rook rook = new Rook();
        Location location = new Location(3, 3);

        rook.calculateThreads(board, location);

        for (int i = 0; i < 8; i++) {
            if (i != 3) {  // Skip the location where rook is placed
                assertTrue(board.isLocationUnderThreat(new Location(3, i)));
                assertTrue(board.isLocationUnderThreat(new Location(i, 3)));
            }
        }
    }

    @Test
    public void testCalculateThreadsOnDifferentBoardSizes() {
        int[] sizes = {3, 5, 7, 10};

        for (int size : sizes) {
            ChessBoard board = new ChessBoard(size);
            Rook rook = new Rook();
            Location location = new Location(size / 2, size / 2);

            rook.calculateThreads(board, location);

            for (int i = 0; i < size; i++) {
                if (i != location.row()) {  // Skip the location where rook is placed
                    assertTrue(board.isLocationUnderThreat(new Location(location.row(), i)));
                    assertTrue(board.isLocationUnderThreat(new Location(i, location.row())));
                }
            }
        }
    }
}
