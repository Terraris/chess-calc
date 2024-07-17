package net.mirillas.chess.rest;

import lombok.extern.slf4j.Slf4j;
import net.mirillas.chess.model.ThreatScenario;
import net.mirillas.chess.pieces.ChessPiece;
import net.mirillas.chess.pieces.ChessPieceFactory;
import net.mirillas.chess.simulation.ThreatScenarioGenerator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/chess")
@CrossOrigin(origins = "*") // DEBUG
@Slf4j
public class ChessRestController {

    private final ThreatScenarioGenerator threatScenarioGenerator;

    private final ChessPieceFactory chessPieceFactory;

    public ChessRestController(ThreatScenarioGenerator threatScenarioGenerator,
                               ChessPieceFactory chessPieceFactory) {
        this.threatScenarioGenerator = threatScenarioGenerator;
        this.chessPieceFactory = chessPieceFactory;
    }

    @PostMapping("/simulate")
    public Map<UUID, ThreatScenario> simulate(@RequestParam("piece") String pieceName, @RequestParam("size") Integer size) {
        ChessPiece piece = chessPieceFactory.create(pieceName);
        return threatScenarioGenerator.generateThreatScenarios(piece, size);
    }
}