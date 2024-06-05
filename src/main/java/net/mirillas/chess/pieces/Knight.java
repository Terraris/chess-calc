package net.mirillas.chess.pieces;

import net.mirillas.chess.model.FieldState;
import net.mirillas.chess.model.Location;
import net.mirillas.chess.simulation.BoardManager;
import net.mirillas.chess.simulation.ChessBoard;

/**
 * The Knight class represents a knight chess piece in a chess game.
 * It implements the ChessPiece interface.
 */
public class Knight implements ChessPiece {

    /**
     * Retrieves the type of the chess piece.
     *
     * <p>This method returns the type of the chess piece represented by the {@code FieldState} enumeration.
     * In this case, it always returns {@code FieldState.KNIGHT} as the type of the chess piece.</p>
     *
     * @return The type of the chess piece as a {@code FieldState} value.
     */
    @Override
    public FieldState getPieceType() {
        return FieldState.KNIGHT;
    }

    /**
     * Calculates the possible threat locations for a knight on a chessboard based on its current location.
     *
     * <p>The method calculates the possible threat locations for a knight based on its current location on the chessboard.
     * It takes a {@link ChessBoard} object and a {@link Location} object as parameters.
     * The method iterates over the move offsets of a knight and checks if the resulting location is within the boundaries of the chessboard.
     * If the resulting location is within the boundaries, it adds the location to the set of cells under threat on the chessboard.</p>
     *
     * @param board    The chessboard on which the knight is placed.
     * @param location The current location of the knight on the chessboard.
     */
    @Override
    public void calculateThreads(ChessBoard board, Location location) {
        for (int[] offset : getMoveOffsets()) {
            int newRowPosition = location.row() + offset[0];
            int newColumnPosition = location.column() + offset[1];
            if (BoardManager.isWithinBoardBoundaries(newRowPosition, newColumnPosition, board.getSize())) {
                board.addLocationUnderThreat(new Location(newRowPosition, newColumnPosition));
            }
        }
    }

    /**
     * Retrieves the move offsets for a Knight chess piece.
     *
     * <p>The move offsets represent the possible moves that a Knight can make on a chessboard.
     * Each move offset is represented by a pair of integers: {rowOffset, columnOffset}.
     * The Knight can move to a field that is {@code rowOffset} rows up or down and {@code columnOffset} columns left or right.
     * The Knight can make a total of 8 possible moves.</p>
     *
     * @return The move offsets for a Knight as a 2D array of integers.
     * Each row of the array represents a move offset,
     * and each column of a row represents the row and column offsets respectively.
     */
    private int[][] getMoveOffsets() {
        return new int[][]{
                {-2, -1}, {-2, 1},
                {-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {2, -1}, {2, 1}
        };
    }
}
