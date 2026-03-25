package com.metalgeararchive.backend.data;

import com.metalgeararchive.backend.entity.*;
import com.metalgeararchive.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private CharacterRepository characterRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear Juegos
        Game mg1 = new Game();
        mg1.setName("Metal Gear Solid");
        mg1.setReleaseYear(1998);
        mg1.setPlatforms("[\"PlayStation\", \"PC\"]");
        mg1.setSynopsis("Un soldado especial se infiltra en una isla para evitar que terroristas usen una instalación militar nuclear.");
        mg1.setImageUrl("https://via.placeholder.com/300x400?text=MGS1");
        mg1.setVideoUrl("https://www.youtube.com/embed/l4VXn3irW10");
        
        Game mg2 = new Game();
        mg2.setName("Metal Gear Solid 2: Sons of Liberty");
        mg2.setReleaseYear(2001);
        mg2.setPlatforms("[\"PlayStation 2\", \"PC\"]");
        mg2.setSynopsis("Raiden es enviado a tomar control de un petrolero para impedir la distribución de un arma nuclear.");
        mg2.setImageUrl("https://via.placeholder.com/300x400?text=MGS2");
        mg2.setVideoUrl("https://www.youtube.com/embed/aHRqCvM3LN4");
        
        Game mg3 = new Game();
        mg3.setName("Metal Gear Solid 3: Snake Eater");
        mg3.setReleaseYear(2004);
        mg3.setPlatforms("[\"PlayStation 2\", \"Xbox 360\"]");
        mg3.setSynopsis("Naked Snake penetra una fortaleza soviética para asesinar a la leyenda de la Guerra Fría, The Boss.");
        mg3.setImageUrl("https://via.placeholder.com/300x400?text=MGS3");
        mg3.setVideoUrl("https://www.youtube.com/embed/lqGEFZp3qm8");
        
        gameRepository.save(mg1);
        gameRepository.save(mg2);
        gameRepository.save(mg3);
        
        // Crear Localizaciones
        Location shadowMoses = new Location();
        shadowMoses.setName("Shadow Moses Island");
        shadowMoses.setRegion("Alaska, USA");
        shadowMoses.setDescription("Instalación militar subterránea donde se almacenan armas nucleares y se realizan experimentos genéticos.");
        shadowMoses.setImageUrl("https://via.placeholder.com/300x400?text=ShadowMoses");
        shadowMoses.setCoordinates("60.0N,140.0W");
        
        Location tanyaStation = new Location();
        tanyaStation.setName("Tanker Deck");
        tanyaStation.setRegion("Atlantic Ocean");
        tanyaStation.setDescription("Un petrolero modificado utilizado para el transporte de armas nucleares avanzadas.");
        tanyaStation.setImageUrl("https://via.placeholder.com/300x400?text=TanyaTanker");
        tanyaStation.setCoordinates("50.5N,5.0W");
        
        Location groznygrad = new Location();
        groznygrad.setName("Groznyj Grad");
        groznygrad.setRegion("Soviet Union");
        groznygrad.setDescription("Fortaleza militar soviética donde se desarrollan armas revolucionarias.");
        groznygrad.setImageUrl("https://via.placeholder.com/300x400?text=Groznygrad");
        groznygrad.setCoordinates("55.5N,37.5E");
        
        locationRepository.save(shadowMoses);
        locationRepository.save(tanyaStation);
        locationRepository.save(groznygrad);
        
        // Crear Personajes
        com.metalgeararchive.backend.entity.Character snake = new com.metalgeararchive.backend.entity.Character();
        snake.setName("Solid Snake");
        snake.setRole("Protagonist");
        snake.setNationality("American");
        snake.setAge("Advanced");
        snake.setGender("Male");
        snake.setDescription("Soldado especializado equipado con habilidades de infiltración y combate.");
        snake.setImageUrl("https://via.placeholder.com/300x200?text=SolidSnake");
        snake.setAppearances(new HashSet<>(Set.of(mg1, mg2)));
        
        com.metalgeararchive.backend.entity.Character raiden = new com.metalgeararchive.backend.entity.Character();
        raiden.setName("Raiden");
        raiden.setRole("Protagonist");
        raiden.setNationality("Unknown");
        raiden.setAge("Young");
        raiden.setGender("Male");
        raiden.setDescription("Soldado de nueva generación entrenado con tecnología de realidad virtual.");
        raiden.setImageUrl("https://via.placeholder.com/300x200?text=Raiden");
        raiden.setAppearances(new HashSet<>(Set.of(mg2)));
        
        com.metalgeararchive.backend.entity.Character nakedSnake = new com.metalgeararchive.backend.entity.Character();
        nakedSnake.setName("Naked Snake");
        nakedSnake.setRole("Protagonist");
        nakedSnake.setNationality("American");
        nakedSnake.setAge("Middle-aged");
        nakedSnake.setGender("Male");
        nakedSnake.setDescription("Legendario soldado especial de la Guerra Fría.");
        nakedSnake.setImageUrl("https://via.placeholder.com/300x200?text=NakedSnake");
        nakedSnake.setAppearances(new HashSet<>(Set.of(mg3)));
        
        com.metalgeararchive.backend.entity.Character theGeneral = new com.metalgeararchive.backend.entity.Character();
        theGeneral.setName("The Boss");
        theGeneral.setRole("Antagonist");
        theGeneral.setNationality("Soviet Union");
        theGeneral.setAge("Mature");
        theGeneral.setGender("Female");
        theGeneral.setDescription("Leyenda de la Guerra Fría y maestra de artes marciales.");
        theGeneral.setImageUrl("https://via.placeholder.com/300x200?text=TheBoss");
        theGeneral.setAppearances(new HashSet<>(Set.of(mg3)));
        
        characterRepository.save(snake);
        characterRepository.save(raiden);
        characterRepository.save(nakedSnake);
        characterRepository.save(theGeneral);
        
        // Crear Organizaciones
        Organization foxhound = new Organization();
        foxhound.setName("FOXHOUND");
        foxhound.setType("Military Special Forces");
        foxhound.setDescription("Unidad de fuerzas especiales legendaria.");
        foxhound.setImageUrl("https://via.placeholder.com/300x200?text=FOXHOUND");
        foxhound.setHeadquarters("Shadow Moses Island");
        
        Organization specOps = new Organization();
        specOps.setName("SOCOM");
        specOps.setType("Military Command");
        specOps.setDescription("Comando de operaciones especiales del mundo.");
        specOps.setImageUrl("https://via.placeholder.com/300x200?text=SOCOM");
        specOps.setHeadquarters("Pentagon");
        
        Organization darpa = new Organization();
        darpa.setName("DARPA");
        darpa.setType("Government Research");
        darpa.setDescription("Agencia de Proyectos de Investigación Avanzada de Defensa.");
        darpa.setImageUrl("https://via.placeholder.com/300x200?text=DARPA");
        darpa.setHeadquarters("Arlington, Virginia");
        
        organizationRepository.save(foxhound);
        organizationRepository.save(specOps);
        organizationRepository.save(darpa);
        
        // Crear Eventos
        Event operation_intrude = new Event();
        operation_intrude.setName("Operation Intrude N313");
        operation_intrude.setCodeName("Infiltration");
        operation_intrude.setEventYear(1995);
        operation_intrude.setDescription("Infiltración para interrumpir tráfico de armas nucleares.");
        operation_intrude.setImageUrl("https://via.placeholder.com/300x200?text=OpIntrude");
        operation_intrude.setLocation(shadowMoses);
        operation_intrude.setOutcome("Success");
        
        Event tanker_chapter = new Event();
        tanker_chapter.setName("Tanker Chapter");
        tanker_chapter.setCodeName("R.A.G.E");
        tanker_chapter.setEventYear(2007);
        tanker_chapter.setDescription("Operación para investigar un petrolero con tecnología nuclear.");
        tanker_chapter.setImageUrl("https://via.placeholder.com/300x200?text=TankerChapter");
        tanker_chapter.setLocation(tanyaStation);
        tanker_chapter.setOutcome("Compromise");
        
        Event operation_snake_eater = new Event();
        operation_snake_eater.setName("Operation Snake Eater");
        operation_snake_eater.setCodeName("SNAKE EATER");
        operation_snake_eater.setEventYear(1964);
        operation_snake_eater.setDescription("Infiltración en Groznyj Grad para asesinar a The Boss.");
        operation_snake_eater.setImageUrl("https://via.placeholder.com/300x200?text=SnakeEater");
        operation_snake_eater.setLocation(groznygrad);
        operation_snake_eater.setOutcome("Success");
        
        eventRepository.save(operation_intrude);
        eventRepository.save(tanker_chapter);
        eventRepository.save(operation_snake_eater);
        
        System.out.println("=== Base de datos inicializada con seed data de Metal Gear ===");
    }
}
