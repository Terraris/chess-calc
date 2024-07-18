package net.mirillas.chess.pieces;

import net.mirillas.chess.pieces.ChessPiece;
import net.mirillas.chess.pieces.Knight;
import net.mirillas.chess.pieces.Rook;
import org.springframework.stereotype.Component;

@Component
public class ChessPieceFactory {
    public ChessPiece create(String pieceName) {
        return switch (pieceName.toLowerCase()) {
            case "knight" -> new Knight();
            case "rook" -> new Rook();
            default -> throw new IllegalArgumentException("Unsupported chess piece: " + pieceName);
        };
    }
}