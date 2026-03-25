package com.metalgeararchive.backend.service;

import com.metalgeararchive.backend.entity.Location;
import com.metalgeararchive.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    
    @Autowired
    private LocationRepository locationRepository;
    
    public List<Location> obtenerTodos() {
        return locationRepository.findAll();
    }
    
    public Optional<Location> obtenerPorId(Long id) {
        return locationRepository.findById(id);
    }
    
    public Location crear(Location location) {
        return locationRepository.save(location);
    }
    
    public Location actualizar(Long id, Location locationDetails) {
        return locationRepository.findById(id).map(location -> {
            location.setName(locationDetails.getName());
            location.setRegion(locationDetails.getRegion());
            location.setDescription(locationDetails.getDescription());
            location.setImageUrl(locationDetails.getImageUrl());
            location.setCoordinates(locationDetails.getCoordinates());
            return locationRepository.save(location);
        }).orElseThrow(() -> new RuntimeException("Localización no encontrada"));
    }
    
    public void eliminar(Long id) {
        locationRepository.deleteById(id);
    }
}
