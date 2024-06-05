package net.mirillas.chess;

import net.mirillas.chess.model.ThreatScenario;
import net.mirillas.chess.pieces.Knight;
import net.mirillas.chess.pieces.Rook;
import net.mirillas.chess.simulation.ThreatScenarioGenerator;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class ThreatScenarioGeneratorIntegrationTest {

    private static final int STANDARD_BOARD_SIZE = 8;
    private static final int SMALL_BOARD_SIZE = 3;
    private static final int BIG_BOARD_SIZE = 20;
    private static final int NUMBER_OF_POSSIBLE_CONFIGURATIONS_STANDARD = STANDARD_BOARD_SIZE * STANDARD_BOARD_SIZE;
    private static final int NUMBER_OF_POSSIBLE_CONFIGURATIONS_BIG = BIG_BOARD_SIZE * BIG_BOARD_SIZE;
    private static final int NUMBER_OF_POSSIBLE_CONFIGURATIONS_SMALL = SMALL_BOARD_SIZE * SMALL_BOARD_SIZE;

    @Test
    public void testKnightPositions_smallBoard() {
        ThreatScenarioGenerator threatScenarioGenerator = new ThreatScenarioGenerator();
        Knight knight = new Knight();
        Map<UUID, ThreatScenario> knightThreatMap = threatScenarioGenerator.generateThreatScenarios(knight, SMALL_BOARD_SIZE);

        long totalThreats = knightThreatMap.values()
                .stream()
                .mapToLong(ThreatScenario::threats)
                .sum();
        assertThat(knightThreatMap).hasSize(NUMBER_OF_POSSIBLE_CONFIGURATIONS_SMALL);
        assertThat(totalThreats).isEqualTo((NUMBER_OF_POSSIBLE_CONFIGURATIONS_SMALL - 1) * 2);
    }

    @Test
    public void testKnightPositions() {
        ThreatScenarioGenerator threatScenarioGenerator = new ThreatScenarioGenerator();
        Knight knight = new Knight();
        Map<UUID, ThreatScenario> knightThreatMap = threatScenarioGenerator.generateThreatScenarios(knight, STANDARD_BOARD_SIZE);

        long totalThreats = knightThreatMap.values()
                .stream()
                .mapToLong(ThreatScenario::threats)
                .sum();
        assertThat(knightThreatMap).hasSize(NUMBER_OF_POSSIBLE_CONFIGURATIONS_STANDARD);
        assertThat(totalThreats).isEqualTo(336);
    }

    @Test
    public void testRookPositions_smallBoard() {
        ThreatScenarioGenerator threatScenarioGenerator = new ThreatScenarioGenerator();
        Rook rook = new Rook();
        Map<UUID, ThreatScenario> rookThreatMap = threatScenarioGenerator.generateThreatScenarios(rook, SMALL_BOARD_SIZE);

        long totalThreats = rookThreatMap.values()
                .stream()
                .mapToLong(ThreatScenario::threats)
                .sum();
        assertThat(rookThreatMap).hasSize(NUMBER_OF_POSSIBLE_CONFIGURATIONS_SMALL);
        int maxRowCoverage = SMALL_BOARD_SIZE - 1;
        assertThat(totalThreats).isEqualTo(2 * maxRowCoverage * NUMBER_OF_POSSIBLE_CONFIGURATIONS_SMALL);
    }

    @Test
    public void testRookPositions() {
        ThreatScenarioGenerator threatScenarioGenerator = new ThreatScenarioGenerator();
        Rook rook = new Rook();
        Map<UUID, ThreatScenario> rookThreatMap = threatScenarioGenerator.generateThreatScenarios(rook, STANDARD_BOARD_SIZE);

        long totalThreats = rookThreatMap.values()
                .stream()
                .mapToLong(ThreatScenario::threats)
                .sum();
        assertThat(rookThreatMap).hasSize(NUMBER_OF_POSSIBLE_CONFIGURATIONS_STANDARD);
        int maxRowCoverage = STANDARD_BOARD_SIZE - 1;
        assertThat(totalThreats).isEqualTo(2 * maxRowCoverage * NUMBER_OF_POSSIBLE_CONFIGURATIONS_STANDARD);
    }

    @Test
    public void testRookPositions_bigBoard() {
        ThreatScenarioGenerator threatScenarioGenerator = new ThreatScenarioGenerator();
        Rook rook = new Rook();
        Map<UUID, ThreatScenario> rookThreatMap = threatScenarioGenerator.generateThreatScenarios(rook, BIG_BOARD_SIZE);

        long totalThreats = rookThreatMap.values()
                .stream()
                .mapToLong(ThreatScenario::threats)
                .sum();
        assertThat(rookThreatMap).hasSize(BIG_BOARD_SIZE * BIG_BOARD_SIZE);
        int maxRowCoverage = BIG_BOARD_SIZE - 1;
        assertThat(totalThreats).isEqualTo(2 * maxRowCoverage * NUMBER_OF_POSSIBLE_CONFIGURATIONS_BIG);
    }
}
