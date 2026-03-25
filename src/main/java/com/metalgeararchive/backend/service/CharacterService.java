package com.metalgeararchive.backend.service;

import com.metalgeararchive.backend.entity.Character;
import com.metalgeararchive.backend.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    
    @Autowired
    private CharacterRepository characterRepository;
    
    public List<Character> obtenerTodos() {
        return characterRepository.findAll();
    }
    
    public Optional<Character> obtenerPorId(Long id) {
        return characterRepository.findById(id);
    }
    
    public Character crear(Character character) {
        return characterRepository.save(character);
    }
    
    public Character actualizar(Long id, Character characterDetails) {
        return characterRepository.findById(id).map(character -> {
            character.setName(characterDetails.getName());
            character.setRole(characterDetails.getRole());
            character.setNationality(characterDetails.getNationality());
            character.setAge(characterDetails.getAge());
            character.setGender(characterDetails.getGender());
            character.setDescription(characterDetails.getDescription());
            character.setImageUrl(characterDetails.getImageUrl());
            character.setAppearances(characterDetails.getAppearances());
            return characterRepository.save(character);
        }).orElseThrow(() -> new RuntimeException("Personaje no encontrado"));
    }
    
    public void eliminar(Long id) {
        characterRepository.deleteById(id);
    }
}
