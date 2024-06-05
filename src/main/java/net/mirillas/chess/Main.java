package net.mirillas.chess;

import net.mirillas.chess.simulation.ThreatScenarioGenerator;

/**
 * The Main class represents the entry point of the program. It creates an instance of
 * ChessSimulator and runs the simulation by calling the {@code runSimulation()} method.
 */
public class Main {
    public static void main(String[] args) {
        ThreatScenarioGenerator threatScenarioGenerator = new ThreatScenarioGenerator();
        ChessSimulator chessSimulator = new ChessSimulator(threatScenarioGenerator);
        chessSimulator.runSimulation();
    }
}
