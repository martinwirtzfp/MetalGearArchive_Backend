package com.metalgeararchive.backend.service;

import com.metalgeararchive.backend.entity.Event;
import com.metalgeararchive.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    
    @Autowired
    private EventRepository eventRepository;
    
    public List<Event> obtenerTodos() {
        return eventRepository.findAll();
    }
    
    public Optional<Event> obtenerPorId(Long id) {
        return eventRepository.findById(id);
    }
    
    public Event crear(Event event) {
        return eventRepository.save(event);
    }
    
    public Event actualizar(Long id, Event eventDetails) {
        return eventRepository.findById(id).map(event -> {
            event.setName(eventDetails.getName());
            event.setCodeName(eventDetails.getCodeName());
            event.setEventYear(eventDetails.getEventYear());
            event.setDescription(eventDetails.getDescription());
            event.setImageUrl(eventDetails.getImageUrl());
            event.setLocation(eventDetails.getLocation());
            event.setOutcome(eventDetails.getOutcome());
            return eventRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }
    
    public void eliminar(Long id) {
        eventRepository.deleteById(id);
    }
}
