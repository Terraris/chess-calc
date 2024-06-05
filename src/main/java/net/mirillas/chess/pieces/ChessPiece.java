package net.mirillas.chess.pieces;

import net.mirillas.chess.model.FieldState;
import net.mirillas.chess.model.Location;
import net.mirillas.chess.simulation.ChessBoard;

/**
 * The ChessPiece interface represents a generic chess piece in a chess game.
 * <p>
 * Note: In a complete chess game implementation, we need to provide implementations for all types of chess pieces.
 * The listing below shows the classical chess pieces:
 * <p>
 * - Pawn: The Pawn is unique in that it moves and captures in different ways. It moves forward but captures diagonally.
 * It also has a special move called En Passant.
 * <p>
 * - Bishop: The Bishop moves and captures diagonally any number of squares.
 * <p>
 * - Queen: The Queen moves and captures horizontally, vertically, and diagonally any number of squares. It could be
 * implemented as a combination of the movements of the rook and the bishop.
 * <p>
 * - King: The King can move and capture in any direction (horizontally, vertically, and diagonally) but only one square at a time.
 * It also has a special move called Castling. (movement: see queen's movement and rook's processing)
 * <p>
 * Each chess piece mentioned above would need its own class with rules to govern its movement and capturing rules.
 */
public interface ChessPiece {
    /**
     * Returns the type of the chess piece.
     *
     * @return The type of the chess piece represented by the FieldState enumeration.
     */
    FieldState getPieceType();

    /**
     * Calculates the threads of a chess piece on a chessboard.
     *
     * <p>Given the current state of the chessboard and the location of the chess piece, this method updates the
     * board by marking the threatened fields with the THREAD state.</p>
     *
     * @param board    The current state of the chessboard.
     * @param location The location of the chess piece on the chessboard.
     */
    void calculateThreads(ChessBoard board, Location location);
}
