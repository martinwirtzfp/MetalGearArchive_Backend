package com.metalgeararchive.backend.controller;

import com.metalgeararchive.backend.entity.Organization;
import com.metalgeararchive.backend.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin(origins = "http://localhost:5173")
public class OrganizationController {
    
    @Autowired
    private OrganizationService organizationService;
    
    @GetMapping
    public List<Organization> obtenerTodos() {
        return organizationService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Organization> obtenerPorId(@PathVariable Long id) {
        return organizationService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Organization crear(@RequestBody Organization organization) {
        return organizationService.crear(organization);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Organization> actualizar(@PathVariable Long id, @RequestBody Organization organizationDetails) {
        try {
            Organization updated = organizationService.actualizar(id, organizationDetails);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        organizationService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
