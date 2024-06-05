package net.mirillas.chess.simulation;

import lombok.Getter;
import net.mirillas.chess.model.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a chess board. The {@code ChessBoard} class provides operations for managing and manipulating a chess board. It keeps track of
 * the size of the board, the cells that are under threat, and the currently occupied location.</p>
 */
@Getter
public class ChessBoard {
    private final int size;
    private final Set<Location> cellsUnderThreat;
    private Location occupiedLocation;

    /**
     * Creates a new {@code ChessBoard} object with the specified size. The chess board keeps track of its size, the cells that are under threat, and the currently occupied location
     * .
     *
     * @param size The size of the chess board.
     */
    public ChessBoard(int size) {
        this.size = size;
        this.cellsUnderThreat = new HashSet<>();
        this.occupiedLocation = new Location(0, 0);
    }

    public void placePiece(Location location) {
        this.occupiedLocation = location;
    }

    /**
     * Adds a location under threat to the chessboard.
     *
     * <p>This method adds the specified location to the set of cells under threat on the chessboard.</p>
     *
     * @param location The location to be added under threat.
     */
    public void addLocationUnderThreat(Location location) {
        cellsUnderThreat.add(location);
    }

    /**
     * Determines whether a location on the chessboard is occupied by a piece.
     *
     * @param location The location on the chessboard to check.
     * @return {@code true} if the location is occupied, {@code false} otherwise.
     */
    public boolean isLocationOccupied(Location location) {
        return occupiedLocation.equals(location);
    }

    /**
     * Determines whether a specific location on the chessboard is under threat.
     *
     * @param location The location on the chessboard to check.
     * @return {@code true} if the location is under threat, {@code false} otherwise.
     */
    public boolean isLocationUnderThreat(Location location) {
        return cellsUnderThreat.contains(location);
    }
}
