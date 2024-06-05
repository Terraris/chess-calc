package net.mirillas.chess.model;

import net.mirillas.chess.pieces.ChessPiece;
import net.mirillas.chess.simulation.ChessBoard;

/**
 * Represents information about the state of a chessboard. It includes the number of threats on the board,
 * information on the chess piece, piece location information, and the chessboard itself.
 */
public record ThreatScenario(long threats, ChessPiece piece, Location location, ChessBoard board) {
}
