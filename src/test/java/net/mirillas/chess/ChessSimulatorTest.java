package net.mirillas.chess;

import net.mirillas.chess.model.ThreatScenario;
import net.mirillas.chess.pieces.Knight;
import net.mirillas.chess.pieces.Rook;
import net.mirillas.chess.simulation.ThreatScenarioGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.UUID;

import static net.mirillas.chess.model.Message.SIM_BOARD_SIZE_INPUT_PROMPT;
import static net.mirillas.chess.model.Message.SIM_CHESS_PIECE_INPUT_PROMPT;
import static net.mirillas.chess.model.Message.SIM_GENERATION_MESSAGE;
import static net.mirillas.chess.model.Message.SIM_PIECE_SELECTED_MESSAGE;
import static net.mirillas.chess.model.Message.SIM_REGENERATE_SCENARIOS_PROMPT;
import static net.mirillas.chess.model.Message.SIM_SELECTED_PIECE;
import static net.mirillas.chess.model.Message.SIM_SELECTED_SIZE;
import static net.mirillas.chess.model.Message.SIM_SIMULATION_RESULTS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChessSimulatorTest {

    private ChessSimulator chessSimulator;
    private ThreatScenarioGenerator generator;

    @BeforeEach
    public void setup() {
        generator = mock(ThreatScenarioGenerator.class);
        chessSimulator = new ChessSimulator(generator);
    }

    @Test
    public void testRunSimulation() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("knight\n5\ny\nn".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        ThreatScenario threatScenario = new ThreatScenario(14L, new Knight(), null, null);
        when(generator.generateThreatScenarios(Mockito.any(), Mockito.anyInt())).thenReturn(Collections.singletonMap(UUID.randomUUID(), threatScenario));

        chessSimulator.runSimulation();

        String[] expectedMessages = new String[]{
                String.format(SIM_CHESS_PIECE_INPUT_PROMPT.getMessage().replace("{}", "%s"), "knight"),
                String.format(SIM_SELECTED_PIECE.getMessage().replace("{}", "%s"), "knight"),
                String.format(SIM_BOARD_SIZE_INPUT_PROMPT.getMessage().replace("{}", "%s"), 8),
                String.format(SIM_SELECTED_SIZE.getMessage().replace("{}", "%s"), "5"),
                String.format(SIM_PIECE_SELECTED_MESSAGE.getMessage().replace("{}", "%s"), "KNIGHT", 5, 5),
                SIM_GENERATION_MESSAGE.getMessage(),
                String.format(SIM_SIMULATION_RESULTS.getMessage().replace("{}", "%s"), 14L),
                SIM_REGENERATE_SCENARIOS_PROMPT.getMessage()
        };

        String consoleOutputAsString = consoleOutput.toString();
        for (String expectedMessage : expectedMessages) {
            assertThat(consoleOutputAsString).contains(expectedMessage);
        }
    }

    @Test
    public void testRunSimulationWithDifferentPieces() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("rook\n7\ny\nn".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        ThreatScenario threatScenario = new ThreatScenario(7L, new Rook(), null, null);
        when(generator.generateThreatScenarios(Mockito.any(), Mockito.anyInt())).thenReturn(Collections.singletonMap(UUID.randomUUID(), threatScenario));

        chessSimulator.runSimulation();

        String[] expectedMessages = new String[]{
                String.format(SIM_CHESS_PIECE_INPUT_PROMPT.getMessage().replace("{}", "%s"), "knight"),
                String.format(SIM_SELECTED_PIECE.getMessage().replace("{}", "%s"), "rook"),
                String.format(SIM_BOARD_SIZE_INPUT_PROMPT.getMessage().replace("{}", "%s"), 8),
                String.format(SIM_SELECTED_SIZE.getMessage().replace("{}", "%s"), "7"),
                String.format(SIM_PIECE_SELECTED_MESSAGE.getMessage().replace("{}", "%s"), "ROOK", 7, 7),
                SIM_GENERATION_MESSAGE.getMessage(),
                String.format(SIM_SIMULATION_RESULTS.getMessage().replace("{}", "%s"), 7L),
                SIM_REGENERATE_SCENARIOS_PROMPT.getMessage()
        };

        String consoleOutputAsString = consoleOutput.toString();
        for (String expectedMessage : expectedMessages) {
            assertThat(consoleOutputAsString).contains(expectedMessage);
        }
    }

    @Test
    public void testRunSimulationWithDifferentDimensions() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("rook\n6\ny\nn".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleOutput));
        ThreatScenario threatScenario = new ThreatScenario(15L, new Rook(), null, null);
        when(generator.generateThreatScenarios(Mockito.any(), Mockito.anyInt())).thenReturn(Collections.singletonMap(UUID.randomUUID(), threatScenario));

        chessSimulator.runSimulation();

        String[] expectedMessages = new String[]{
                String.format(SIM_CHESS_PIECE_INPUT_PROMPT.getMessage().replace("{}", "%s"), "knight"),
                String.format(SIM_SELECTED_PIECE.getMessage().replace("{}", "%s"), "rook"),
                String.format(SIM_BOARD_SIZE_INPUT_PROMPT.getMessage().replace("{}", "%s"), 8),
                String.format(SIM_SELECTED_SIZE.getMessage().replace("{}", "%s"), "6"),
                String.format(SIM_PIECE_SELECTED_MESSAGE.getMessage().replace("{}", "%s"), "ROOK", 6, 6),
                SIM_GENERATION_MESSAGE.getMessage(),
                String.format(SIM_SIMULATION_RESULTS.getMessage().replace("{}", "%s"), 15L),
                SIM_REGENERATE_SCENARIOS_PROMPT.getMessage()
        };

        String consoleOutputAsString = consoleOutput.toString();
        for (String expectedMessage : expectedMessages) {
            assertThat(consoleOutputAsString).contains(expectedMessage);
        }
    }

}
