package com.metalgeararchive.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(length = 500)
    private String role; // "Protagonist", "Antagonist", etc.
    
    @Column(length = 500)
    private String nationality;
    
    @Column(length = 20)
    private String age;
    
    @Column(length = 10)
    private String gender;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(length = 500)
    private String imageUrl;
    
    @ManyToMany
    @JoinTable(
        name = "character_games",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Set<Game> appearances = new HashSet<>();
}
