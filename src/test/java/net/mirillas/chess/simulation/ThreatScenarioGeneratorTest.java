package net.mirillas.chess.simulation;

import net.mirillas.chess.model.FieldState;
import net.mirillas.chess.model.ThreatScenario;
import net.mirillas.chess.pieces.Knight;
import net.mirillas.chess.pieces.Rook;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ThreatScenarioGeneratorTest {


    @Test
    public void testGenerateThreatScenariosNoThreads() {
        ThreatScenarioGenerator generator = new ThreatScenarioGenerator();

        int boardSize = 3;
        FieldState emptyField = mock(FieldState.class);
        Rook mockPiece = mock(Rook.class);
        when(mockPiece.getPieceType()).thenReturn(emptyField);

        Map<UUID, ThreatScenario> result = generator.generateThreatScenarios(mockPiece, boardSize);

        assertEquals(boardSize * boardSize, result.size());
        result.values().forEach(threatScenario -> {
            assertThat(threatScenario.piece().getPieceType()).isEqualTo(emptyField);
            assertThat(threatScenario.location().row()).isBetween(0, boardSize - 1);
            assertThat(threatScenario.location().column()).isBetween(0, boardSize - 1);
            assertThat(threatScenario.threats()).isEqualTo(0L);
            assertThat(threatScenario.board().getCellsUnderThreat().size()).isEqualTo(0);
        });
    }

    @Test
    public void testGenerateThreatScenariosRook() {
        ThreatScenarioGenerator generator = new ThreatScenarioGenerator();

        int boardSize = 3;
        FieldState emptyField = mock(FieldState.class);
        Rook mockPiece = mock(Rook.class);
        when(mockPiece.getPieceType()).thenReturn(emptyField);

        Map<UUID, ThreatScenario> result = generator.generateThreatScenarios(mockPiece, boardSize);

        assertEquals(boardSize * boardSize, result.size());
        result.values().forEach(threatScenario -> {
            assertThat(threatScenario.piece().getPieceType()).isEqualTo(emptyField);
            assertThat(threatScenario.location().row()).isBetween(0, boardSize - 1);
            assertThat(threatScenario.location().column()).isBetween(0, boardSize - 1);
            assertThat(threatScenario.threats()).isEqualTo(0L);
            assertThat(threatScenario.board().getCellsUnderThreat().size()).isEqualTo(0);
        });
    }

    @Test
    public void testGenerateThreatScenariosKnight() {
        ThreatScenarioGenerator generator = new ThreatScenarioGenerator();

        int boardSize = 3;
        FieldState emptyField = mock(FieldState.class);
        Knight mockPiece = mock(Knight.class);
        when(mockPiece.getPieceType()).thenReturn(emptyField);

        Map<UUID, ThreatScenario> result = generator.generateThreatScenarios(mockPiece, boardSize);

        assertEquals(boardSize * boardSize, result.size());
        result.values().forEach(threatScenario -> {
            assertThat(threatScenario.piece().getPieceType()).isEqualTo(emptyField);
            assertThat(threatScenario.location().row()).isBetween(0, boardSize - 1);
            assertThat(threatScenario.location().column()).isBetween(0, boardSize - 1);
            assertThat(threatScenario.threats()).isEqualTo(0L);
            assertThat(threatScenario.board().getCellsUnderThreat().size()).isEqualTo(0);
        });
    }

    @Test
    public void testGenerateThreatScenariosWithThreats() {
        ThreatScenarioGenerator generator = new ThreatScenarioGenerator();
        Rook rook = new Rook();

        int boardSize = 3;
        Map<UUID, ThreatScenario> result = generator.generateThreatScenarios(rook, boardSize);

        result.values().forEach(threatScenario -> {
            assertThat(threatScenario.threats()).isEqualTo(4L);
            assertEquals(4, threatScenario.board().getCellsUnderThreat().size());
        });
    }

}
