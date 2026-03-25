package com.metalgeararchive.backend.controller;

import com.metalgeararchive.backend.entity.Location;
import com.metalgeararchive.backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {
    
    @Autowired
    private LocationService locationService;
    
    @GetMapping
    public List<Location> obtenerTodos() {
        return locationService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Location> obtenerPorId(@PathVariable Long id) {
        return locationService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Location crear(@RequestBody Location location) {
        return locationService.crear(location);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Location> actualizar(@PathVariable Long id, @RequestBody Location locationDetails) {
        try {
            Location updated = locationService.actualizar(id, locationDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        locationService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
