package com.metalgeararchive.backend.service;

import com.metalgeararchive.backend.entity.Game;
import com.metalgeararchive.backend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    public List<Game> obtenerTodos() {
        return gameRepository.findAll();
    }
    
    public Optional<Game> obtenerPorId(Long id) {
        return gameRepository.findById(id);
    }
    
    public Game crear(Game game) {
        return gameRepository.save(game);
    }
    
    public Game actualizar(Long id, Game gameDetails) {
        return gameRepository.findById(id).map(game -> {
            game.setName(gameDetails.getName());
            game.setReleaseYear(gameDetails.getReleaseYear());
            game.setPlatforms(gameDetails.getPlatforms());
            game.setSynopsis(gameDetails.getSynopsis());
            game.setImageUrl(gameDetails.getImageUrl());
            game.setVideoUrl(gameDetails.getVideoUrl());
            return gameRepository.save(game);
        }).orElseThrow(() -> new RuntimeException("Juego no encontrado"));
    }
    
    public void eliminar(Long id) {
        gameRepository.deleteById(id);
    }
}
