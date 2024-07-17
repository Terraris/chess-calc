package net.mirillas.chess.simulation;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.mirillas.chess.model.Location;
import net.mirillas.chess.model.ThreatScenario;
import net.mirillas.chess.pieces.ChessPiece;
import net.mirillas.chess.view.ChessBoardView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static net.mirillas.chess.model.Message.GEN_ELAPSED_TIME_MESSAGE;
import static net.mirillas.chess.model.Message.GEN_THREAT_SCENARIO_INFO_TEMPLATE;
import static net.mirillas.chess.simulation.BoardManager.countThreatPositions;

/**
 * The ThreatScenarioGenerator class is responsible for generating threat scenarios for a given chess piece on a chessboard
 * of a specified size.
 */
@Getter
@Slf4j
@Component
public class ThreatScenarioGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreatScenarioGenerator.class);
    private final ChessBoardView boardDrawer = new ChessBoardView();
    private ConcurrentMap<UUID, ThreatScenario> threatScenarios = new ConcurrentHashMap<>();

    /**
     * Generates threat scenarios for a given chess piece on a chessboard of a specified size.
     *
     * @param piece     The chess piece for which to generate threat scenarios.
     * @param boardSize The size of the chessboard.
     * @return A map of threat scenarios, where the key is a unique identifier and the value is a ThreatScenario object
     * containing information about the state of the chessboard, including the number of threats, the chess piece,
     * the piece location, and the chessboard itself.
     */
    public Map<UUID, ThreatScenario> generateThreatScenarios(ChessPiece piece, int boardSize) {
        long startTime = System.currentTimeMillis();
        for (int rank = 0; rank < boardSize; rank++) {
            for (int file = 0; file < boardSize; file++) {
                generateThreatScenario(piece, boardSize, rank, file);
            }
        }
        long endTime = System.currentTimeMillis();

        LOGGER.info(GEN_ELAPSED_TIME_MESSAGE.getMessage(), endTime - startTime);

        return threatScenarios;
    }

    private void generateThreatScenario(ChessPiece piece, int boardSize, int rank, int file) {
        Location currentPieceLocation = new Location(rank, file);
        ChessBoard board = new ChessBoard(boardSize);
        board.placePiece(currentPieceLocation);

        createThreatScenario(piece, currentPieceLocation, board);
    }

    private void createThreatScenario(ChessPiece piece, Location location, ChessBoard board) {
        piece.calculateThreads(board, location);
        long threatCount = countThreatPositions(board);
        ThreatScenario threatScenario = new ThreatScenario(threatCount, piece, location, board);
        UUID boardId = UUID.randomUUID();
        threatScenarios.put(boardId, threatScenario);

        if (log.isDebugEnabled()) {
            drawThreatScenario(board, piece, location, threatCount);
        }
    }

    private void drawThreatScenario(ChessBoard board, ChessPiece piece, Location location, long threatCount) {
        String pieceInfo = boardDrawer.createPieceInfo(piece, location, threatCount);
        log.debug(GEN_THREAT_SCENARIO_INFO_TEMPLATE.getMessage(), UUID.randomUUID(), pieceInfo);
        boardDrawer.drawBoard(board, piece);
    }

    public void create() {
        this.threatScenarios = new ConcurrentHashMap<>();
    }
}
