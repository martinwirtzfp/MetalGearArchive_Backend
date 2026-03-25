package com.metalgeararchive.backend.controller;

import com.metalgeararchive.backend.entity.Character;
import com.metalgeararchive.backend.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "http://localhost:5173")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;
    
    @GetMapping
    public List<Character> obtenerTodos() {
        return characterService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Character> obtenerPorId(@PathVariable Long id) {
        return characterService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Character crear(@RequestBody Character character) {
        return characterService.crear(character);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Character> actualizar(@PathVariable Long id, @RequestBody Character characterDetails) {
        try {
            Character updated = characterService.actualizar(id, characterDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        characterService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
