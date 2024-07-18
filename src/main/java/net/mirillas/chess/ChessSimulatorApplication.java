package net.mirillas.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Main class represents the entry point of the program. It creates an instance of
 * ChessSimulator and runs the simulation by calling the {@code runSimulation()} method.
 */
@SpringBootApplication
public class ChessSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChessSimulatorApplication.class);
    }

}
