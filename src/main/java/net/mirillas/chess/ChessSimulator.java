package net.mirillas.chess;

import net.mirillas.chess.model.Message;
import net.mirillas.chess.model.ThreatScenario;
import net.mirillas.chess.pieces.ChessPiece;
import net.mirillas.chess.pieces.Knight;
import net.mirillas.chess.pieces.Rook;
import net.mirillas.chess.simulation.ThreatScenarioGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import static net.mirillas.chess.model.Message.SIM_BOARD_SIZE_ERROR_MESSAGE;
import static net.mirillas.chess.model.Message.SIM_BOARD_SIZE_INPUT_PROMPT;
import static net.mirillas.chess.model.Message.SIM_CHESS_PIECE_INPUT_PROMPT;
import static net.mirillas.chess.model.Message.SIM_CONTINUE;
import static net.mirillas.chess.model.Message.SIM_CONTINUE_ABR;
import static net.mirillas.chess.model.Message.SIM_EXIT;
import static net.mirillas.chess.model.Message.SIM_EXIT_ABR;
import static net.mirillas.chess.model.Message.SIM_GENERATION_MESSAGE;
import static net.mirillas.chess.model.Message.SIM_INVALID_PIECE;
import static net.mirillas.chess.model.Message.SIM_PIECE_SELECTED_MESSAGE;
import static net.mirillas.chess.model.Message.SIM_REGENERATE_SCENARIOS_PROMPT;
import static net.mirillas.chess.model.Message.SIM_SELECTED_PIECE;
import static net.mirillas.chess.model.Message.SIM_SELECTED_SIZE;
import static net.mirillas.chess.model.Message.SIM_SIMULATION_RESULTS;
import static net.mirillas.chess.model.Message.SIM_WELCOME_MESSAGE;
import static net.mirillas.chess.model.Message.SIM_YES_NO_INPUT_MESSAGE;

/**
 * The ChessSimulator class represents a chess simulator program that allows users to generate threat scenarios
 * for different chess pieces on a chessboard of a specified size.
 */
public class ChessSimulator {


    public static final String ROOK = "rook";
    public static final String KNIGHT = "knight";
    public static final String RESULTS_SEPARATOR = "--------------------- RESULTS ---------------------";
    public static final String HORIZONTAL_LINE = "---------------------------------------------------";
    private static final Logger LOGGER = LoggerFactory.getLogger(ChessSimulator.class);
    private static final int DEFAULT_BOARD_SIZE = 8;
    private static final String DEFAULT_CHESS_PIECE = "knight";
    private final ThreatScenarioGenerator threatScenarioGenerator;

    private ChessPiece selectedChessPiece;
    private int boardSize;


    public ChessSimulator(ThreatScenarioGenerator threatScenarioGenerator) {
        this.threatScenarioGenerator = threatScenarioGenerator;
    }

    /**
     * This method runs the simulation of the chess game. It prompts the user to select a chess piece and the board size,
     * generates threat scenarios based on the selected piece and board size, calculates the total number of threats,
     * and prompts the user to regenerate the scenarios or exit the simulation. The simulation continues until the user chooses to exit.
     * <p>
     * The simulation process is as follows:
     * 1. Prompt the user to select a chess piece and board size.
     * 2. Display the selected chess piece type and board size.
     * 3. Generate threat scenarios using the selected chess piece and board size.
     * 4. Calculate the total number of threats in the generated scenarios.
     * 5. Display the simulation results showing the total number of threats.
     * 6. Prompt the user to regenerate the scenarios or exit the simulation.
     * 7. Repeat steps 1-6 if the user chooses to start over.
     */
    public void runSimulation() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = false;
        LOGGER.info(SIM_WELCOME_MESSAGE.getMessage());
        do {
            threatScenarioGenerator.create();
            promptUser(scanner);
            LOGGER.info(SIM_PIECE_SELECTED_MESSAGE.getMessage(), selectedChessPiece.getPieceType(), boardSize);
            LOGGER.info(SIM_GENERATION_MESSAGE.getMessage());

            if (!confirmUserInput(scanner)) {
                continue;
            }

            Map<UUID, ThreatScenario> generatedScenarios = threatScenarioGenerator.generateThreatScenarios(selectedChessPiece, boardSize);
            long totalThreats = calculateTotalThreats(generatedScenarios);

            LOGGER.info(RESULTS_SEPARATOR);
            LOGGER.info(SIM_SIMULATION_RESULTS.getMessage(), totalThreats);
            LOGGER.info(HORIZONTAL_LINE);
            printWithBorder(SIM_REGENERATE_SCENARIOS_PROMPT.getMessage());

            continueLoop = confirmUserInput(scanner);
        } while (continueLoop);
        scanner.close();
    }

    private void promptUser(Scanner scanner) {
        this.selectedChessPiece = retrieveChessPieceFromUser(scanner);
        this.boardSize = retrieveBoardSizeFromUser(scanner);
    }

    private boolean confirmUserInput(Scanner scanner) {
        while (true) {
            String userInput = scanner.nextLine().trim().toLowerCase();
            if (userInput.equals(SIM_CONTINUE.getMessage()) || userInput.equals(SIM_CONTINUE_ABR.getMessage())) {
                return true;
            } else if (userInput.equals(SIM_EXIT.getMessage()) || userInput.equals(SIM_EXIT_ABR.getMessage())) {
                return false;
            } else {
                LOGGER.error(SIM_YES_NO_INPUT_MESSAGE.getMessage());
            }
        }
    }

    private long calculateTotalThreats(Map<UUID, ThreatScenario> generatedScenarios) {
        return generatedScenarios.values().stream().mapToLong(ThreatScenario::threats).sum();
    }

    private ChessPiece retrieveChessPieceFromUser(Scanner scanner) {
        while (true) {
            LOGGER.info(SIM_CHESS_PIECE_INPUT_PROMPT.getMessage(), DEFAULT_CHESS_PIECE);
            String chessPieceInput = getChessPieceOrDefault(scanner);

            try {
                LOGGER.info(SIM_SELECTED_PIECE.getMessage(), chessPieceInput);
                return instantiateChessPiece(chessPieceInput);
            } catch (InvalidParameterException e) {
                LOGGER.error(Message.SIM_INVALID_PIECE.getMessage(), chessPieceInput);
            }
        }
    }

    private int retrieveBoardSizeFromUser(Scanner scanner) {
        while (true) {
            LOGGER.info(SIM_BOARD_SIZE_INPUT_PROMPT.getMessage(), DEFAULT_BOARD_SIZE);
            try {
                int boardSize = getBoardSizeOrDefault(scanner);
                LOGGER.info(SIM_SELECTED_SIZE.getMessage(), boardSize);
                return boardSize;
            } catch (NumberFormatException e) {
                LOGGER.error(SIM_BOARD_SIZE_ERROR_MESSAGE.getMessage());
            }
        }
    }

    private int getBoardSizeOrDefault(Scanner scanner) {
        String boardSizeInput = scanner.nextLine().trim();
        if (boardSizeInput.isEmpty()) {
            return DEFAULT_BOARD_SIZE;
        }

        int boardSize = Integer.parseInt(boardSizeInput);

        if (boardSize <= 0) {
            throw new NumberFormatException();
        }
        if (boardSize > 300) {
            LOGGER.error(Message.SIM_PLEASE_STAY_REASONABLE.getMessage());
            throw new NumberFormatException();
        }


        return boardSize;
    }

    private String getChessPieceOrDefault(Scanner scanner) {
        String chessPieceInput = scanner.nextLine().trim();
        return chessPieceInput.isEmpty() ? DEFAULT_CHESS_PIECE : chessPieceInput.toLowerCase();
    }

    private ChessPiece instantiateChessPiece(String chessPieceType) throws InvalidParameterException {
        return switch (chessPieceType) {
            case ROOK -> new Rook();
            case KNIGHT -> new Knight();
            default -> throw new InvalidParameterException(SIM_INVALID_PIECE.getMessage() + chessPieceType);
        };
    }

    void printWithBorder(String message) {
        String border = "*".repeat(message.length() + 4);  // adjusts border length based on message length
        LOGGER.info(border);
        LOGGER.info("* {} *", message);
        LOGGER.info(border);
    }

}
