package com.metalgeararchive.backend.controller;

import com.metalgeararchive.backend.entity.Game;
import com.metalgeararchive.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:5173")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @GetMapping
    public List<Game> obtenerTodos() {
        return gameService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Game> obtenerPorId(@PathVariable Long id) {
        return gameService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Game crear(@RequestBody Game game) {
        return gameService.crear(game);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Game> actualizar(@PathVariable Long id, @RequestBody Game gameDetails) {
        try {
            Game updated = gameService.actualizar(id, gameDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        gameService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
