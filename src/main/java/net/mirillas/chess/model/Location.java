package net.mirillas.chess.model;

/**
 * Represents a location (indices) on a chessboard.
 *
 * <p>The {@code Location} class provides the following information:
 * <ul>
 *   <li>The row of the location</li>
 *   <li>The column of the location</li>
 * </ul>
 */
public record Location(int row, int column) {
}
