package net.mirillas.chess.simulation;

/**
 * The {@code BoardManager} class provides operations for managing and manipulating a chess board.
 */
public class BoardManager {

    /**
     * Checks if the given row and column indices are within the boundaries of the chess board.
     *
     * @param rowIndex    the row index of the position to check
     * @param columnIndex the column index of the position to check
     * @param boardSize   the size of the chess board
     * @return true if the position is within the boundaries of the chess board, false otherwise
     */
    public static boolean isWithinBoardBoundaries(int rowIndex, int columnIndex, int boardSize) {
        boolean isRowWithinBoardBoundaries = rowIndex >= 0 && rowIndex < boardSize;
        boolean isColumnWithinBoardBoundaries = columnIndex >= 0 && columnIndex < boardSize;

        return isRowWithinBoardBoundaries && isColumnWithinBoardBoundaries;
    }

    /**
     * Counts the number of threat positions on the chessboard.
     *
     * @param board The chessboard to count the threat positions on.
     * @return The number of threat positions on the chessboard.
     */
    public static long countThreatPositions(ChessBoard board) {
        return board.getCellsUnderThreat()
                .size();
    }

}
