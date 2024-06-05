package net.mirillas.chess.view;

import net.mirillas.chess.model.Location;
import net.mirillas.chess.pieces.ChessPiece;
import net.mirillas.chess.simulation.ChessBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.mirillas.chess.model.FieldState.EMPTY;
import static net.mirillas.chess.model.FieldState.THREAD;
import static net.mirillas.chess.model.Message.DRAW_PIECE_INFO_TEMPLATE;

/**
 * The ChessBoardView class is responsible for drawing the chessboard and displaying the current state of the game.
 */
public class ChessBoardView {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChessBoardView.class);
    private static final String ROW_NUMBER_PADDING = "%2d ";
    private static final String PADDING = "   ";
    private static final String SMALL_PADDING = "  ";
    private static final char FIRST_RANK = 'A';
    private static final String LINE_BREAK = "\n";
    private static final String BOARD_SEPARATOR = "----------------------------";

    /**
     * Draw the chessboard with the specified ChessBoard and ChessPiece.
     *
     * @param board The ChessBoard object representing the chessboard.
     * @param piece The ChessPiece object representing the chess piece to be rendered.
     */
    public void drawBoard(ChessBoard board, ChessPiece piece) {
        int rowSize = board.getSize();
        String boardOutput = LINE_BREAK +
                generateColumnDescriptors(rowSize) +
                generateBoardContent(board, piece, rowSize) +
                generateColumnDescriptors(rowSize) +
                BOARD_SEPARATOR;
        LOGGER.debug(boardOutput);
    }

    /**
     * Creates a string representation of a chess piece info.
     *
     * @param piece    The ChessPiece object representing the chess piece.
     * @param location The Location object representing the location of the chess piece.
     * @param threats  The number of threats on the chess piece.
     * @return A string representation of the chess piece info.
     */
    public String createPieceInfo(ChessPiece piece, Location location, long threats) {
        int fileNumber = location.row() + 1;
        char rankChar = (char) (FIRST_RANK + location.column());
        return String.format(DRAW_PIECE_INFO_TEMPLATE.getMessage(), piece.getPieceType(), rankChar, fileNumber, threats);
    }

    private String generateBoardContent(ChessBoard chessBoard, ChessPiece piece, int rowSize) {
        StringBuilder boardContent = new StringBuilder();
        for (int row = rowSize - 1; row >= 0; row--) {
            int rowNumber = row + 1;
            String rowPadding = String.format(ROW_NUMBER_PADDING, rowNumber);
            boardContent.append(rowPadding);

            for (int column = 0; column < rowSize; column++) {
                Location location = new Location(row, column);

                char cellMarker;
                if (chessBoard.isLocationOccupied(location)) {
                    cellMarker = piece.getPieceType().getFieldState();
                } else if (chessBoard.isLocationUnderThreat(location)) {
                    cellMarker = THREAD.getFieldState();
                } else {
                    cellMarker = EMPTY.getFieldState();
                }
                boardContent.append(cellMarker).append(SMALL_PADDING);
            }

            boardContent.append(rowNumber).append(LINE_BREAK);
        }
        return boardContent.toString();
    }

    private String generateColumnDescriptors(int rowSize) {
        StringBuilder fileDescriptors = new StringBuilder();
        fileDescriptors.append(PADDING);
        for (int row = 0; row < rowSize; row++) {
            char rankChar = (char) (FIRST_RANK + row);
            fileDescriptors.append(rankChar).append(SMALL_PADDING);
        }
        fileDescriptors.append(LINE_BREAK);

        return fileDescriptors.toString();
    }
}
