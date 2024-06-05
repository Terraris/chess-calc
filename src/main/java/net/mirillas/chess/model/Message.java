package net.mirillas.chess.model;

import lombok.Getter;

@Getter
public enum Message {

    SIM_WELCOME_MESSAGE(
            "Welcome to Chess Simulator! This program simulates threat scenarios for different pieces on a " +
                    "chessboard of customizable size. First, you'll select a chess piece (like Knight or Rook) and the " +
                    "size of the chessboard. Then, the simulation generates all possible threat scenarios (e.g. the " +
                    "number of valid board configurations) for the selected chess piece on the chessboard, counts the " +
                    "total number of threats and presents the results to you. You can continue to regenerate scenarios " +
                    "or exit at your discretion. Enjoy exploring the scenarios! \n >>> NOTE: you can see exactly what the " +
                    "program does on each simulated " +
                    "chess board, just set logback's log level to debug."),
    DRAW_PIECE_INFO_TEMPLATE("Piece (%s) at location (%c,%d) can make %d threats."),

    SIM_ROOK("rook"),
    SIM_KNIGHT("knight"),
    SIM_CONTINUE("yes"),
    SIM_CONTINUE_ABR("y"),
    SIM_EXIT("no"),
    SIM_EXIT_ABR("n"),
    SIM_CHESS_PIECE_INPUT_PROMPT("Choose a chess piece (rook, knight) to simulate possible threat scenarios " +
            "(hit ENTER to use the default ({})): "),
    SIM_INVALID_PIECE("Invalid chess piece selected! Was {}"),
    SIM_BOARD_SIZE_INPUT_PROMPT("Choose a size for the chess board (hit ENTER to use the default ({})): "),
    SIM_BOARD_SIZE_ERROR_MESSAGE("Board size must be a positive integer. Was {}"),
    SIM_REGENERATE_SCENARIOS_PROMPT("Do you want to generate more scenarios? (y/n): "),
    SIM_YES_NO_INPUT_MESSAGE("Please enter 'yes/y' or 'no/n'."),
    SIM_SELECTED_PIECE("Selected chess piece: {}"),
    SIM_SELECTED_SIZE("Selected board size: {}"),
    SIM_PIECE_SELECTED_MESSAGE("You selected '{}', and board size: {}"),
    SIM_SIMULATION_RESULTS("Number of possible threatening configurations: {}."),
    SIM_GENERATION_MESSAGE("Do you want to generate all possible threats for this scenario? (y/n). Depending on your " +
            "input and hardware might this take some time."),
    SIM_PLEASE_STAY_REASONABLE("Please stay in reasonable test sizes, boards of the size 300 and bigger are not supported (yet)."),

    GEN_THREAT_SCENARIO_INFO_TEMPLATE("Board with id: {} saved. {}"),
    GEN_ELAPSED_TIME_MESSAGE("Elapsed time for the generation of threat scenarios: {} ms");

    private final String message;

    Message(String message) {
        this.message = message;
    }

}
