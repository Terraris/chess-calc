package net.mirillas.chess.model;

import lombok.Getter;

/**
 * The {@code FieldState} enumeration represents the four possible states of a field on a chessboard in the context of the application.
 * Each state has a corresponding character representation used when drawing the chessboard.
 *
 * <p>
 * Possible states are:
 * <ul>
 *   <li>{@code EMPTY}: The field is unoccupied and is not threatened by any piece.</li>
 *   <li>{@code THREAD}: The field is threatened by a piece.</li>
 *   <li>{@code ROOK}: The field is currently occupied by a Rook.</li>
 *   <li>{@code KNIGHT}: The field is currently occupied by a Knight.</li>
 * </ul>
 */
@Getter
public enum FieldState {
    EMPTY('.'),
    THREAD('T'),
    ROOK('R'),
    KNIGHT('N');

    private final char fieldState;

    /**
     * Constructor for the {@code FieldState} enumeration.
     * Assigns the character representation for each possible state.
     *
     * @param fieldState Character representing the state of a field in the chessboard.
     */
    FieldState(char fieldState) {
        this.fieldState = fieldState;
    }
}
