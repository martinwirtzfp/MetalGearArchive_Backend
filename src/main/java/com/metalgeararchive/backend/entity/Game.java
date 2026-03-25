package com.metalgeararchive.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private Integer releaseYear;
    
    @Column(length = 500)
    private String platforms; // JSON: ["PlayStation", "Xbox", "PC"]
    
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    
    @Column(length = 500)
    private String imageUrl;
    
    @Column(length = 500)
    private String videoUrl; // YouTube link
}
