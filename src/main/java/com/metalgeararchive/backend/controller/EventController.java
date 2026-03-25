package com.metalgeararchive.backend.controller;

import com.metalgeararchive.backend.entity.Event;
import com.metalgeararchive.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {
    
    @Autowired
    private EventService eventService;
    
    @GetMapping
    public List<Event> obtenerTodos() {
        return eventService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Event> obtenerPorId(@PathVariable Long id) {
        return eventService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Event crear(@RequestBody Event event) {
        return eventService.crear(event);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Event> actualizar(@PathVariable Long id, @RequestBody Event eventDetails) {
        try {
            Event updated = eventService.actualizar(id, eventDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        eventService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
