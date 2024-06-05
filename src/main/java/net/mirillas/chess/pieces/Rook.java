package net.mirillas.chess.pieces;

import net.mirillas.chess.model.FieldState;
import net.mirillas.chess.model.Location;
import net.mirillas.chess.simulation.BoardManager;
import net.mirillas.chess.simulation.ChessBoard;

/**
 * The Rook class represents a Rook chess piece in a chess game.
 *
 * <p>It implements the ChessPiece interface and provides functionality
 * for calculating threat positions on a chessboard for a Rook piece.</p>
 */
public class Rook implements ChessPiece {

    /**
     * Returns the type of the chess piece.
     *
     * @return The type of the chess piece represented by the FieldState enumeration.
     */
    @Override
    public FieldState getPieceType() {
        return FieldState.ROOK;
    }

    /**
     * Calculates the threads of a Rook chess piece on a chessboard.
     *
     * <p>This method updates the chessboard by adding threatened locations for a Rook chess piece represented by the FieldState.ROOK enum value.
     * The method takes a ChessBoard object and a Location object representing the current state of the chessboard and the location of the Rook piece respectively.
     * It adds the valid thread locations for the Rook piece based on its current location and board boundaries.</p>
     *
     * @param board    The current state of the chessboard.
     * @param location The location of the Rook chess piece on the chessboard.
     */
    @Override
    public void calculateThreads(ChessBoard board, Location location) {
        for (int[] offset : getMoveOffsets()) {
            int newRowPosition = location.row() + offset[0];
            int newColumnPosition = location.column() + offset[1];
            while (BoardManager.isWithinBoardBoundaries(newRowPosition, newColumnPosition, board.getSize())) {
                Location newLocation = new Location(newRowPosition, newColumnPosition);
                if (!board.isLocationOccupied(newLocation)) {
                    board.addLocationUnderThreat(newLocation);
                }
                newRowPosition += offset[0];
                newColumnPosition += offset[1];
            }
        }
    }

    /**
     * Returns the move offsets for a Rook chess piece.
     *
     * <p>The move offsets represent the possible moves that a Rook can make
     * on a chessboard. Each move offset is a pair of integers representing
     * the change in row and column position when making the move.</p>
     *
     * @return The move offsets for a Rook chess piece as a 2D integer array.
     */
    private int[][] getMoveOffsets() {
        return new int[][]{
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
    }
}
