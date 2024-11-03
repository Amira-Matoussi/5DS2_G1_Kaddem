package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.services.IEquipeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {

    private final IEquipeService equipeService;

    // GET http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    public ResponseEntity<List<EquipeResponse>> getEquipes() {
        List<EquipeResponse> response = equipeService.retrieveAllEquipes()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // GET http://localhost:8089/Kaddem/equipe/retrieve-equipe/1
    @GetMapping("/retrieve-equipe/{equipe-id}")
    public ResponseEntity<EquipeResponse> retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        Equipe equipe = equipeService.retrieveEquipe(equipeId);
        return ResponseEntity.ok(convertToResponse(equipe));
    }
/**
    // POST http://localhost:8089/Kaddem/equipe/add-equipe
    // Sample JSON body:
    // {
    //     "nomEquipe": "Team A",
    //     "niveau": "JUNIOR",
    //     "detailEquipe": {
    //      "salle": "Salle A",
    //      "thematique": "Web Dev"
    //     }
    // }
 */
    @PostMapping("/add-equipe")
    public ResponseEntity<EquipeResponse> addEquipe(@Valid @RequestBody EquipeRequest equipeRequest) {
        Equipe equipe = convertToEntity(equipeRequest);
        Equipe savedEquipe = equipeService.addEquipe(equipe);
        return ResponseEntity.ok(convertToResponse(savedEquipe));
    }

    // DELETE http://localhost:8089/Kaddem/equipe/remove-equipe/1
    @DeleteMapping("/remove-equipe/{equipe-id}")
    public ResponseEntity<Void> removeEquipe(@PathVariable("equipe-id") Integer equipeId) {
        equipeService.deleteEquipe(equipeId);
        return ResponseEntity.noContent().build();
    }
/**
    // PUT http://localhost:8089/Kaddem/equipe/update-equipe
    // Sample JSON body:
    // {
    //     "idEquipe": 1,
    //     "nomEquipe": "Updated Team A",
    //     "niveau": "SENIOR",
    //     "detailEquipe": {
    //         "salle": "Updated Salle A",
    //         "thematique": "Updated Web Dev"
    //     }
    // }
 */
    @PutMapping("/update-equipe")
    public ResponseEntity<EquipeResponse> updateEquipe(@Valid @RequestBody EquipeRequest equipeRequest) {
        Equipe equipe = convertToEntity(equipeRequest);
        Equipe updatedEquipe = equipeService.updateEquipe(equipe);
        return ResponseEntity.ok(convertToResponse(updatedEquipe));
    }

    @Scheduled(cron = "0 0 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public ResponseEntity<Void> faireEvoluerEquipes() {
        equipeService.evoluerEquipes();
        return ResponseEntity.ok().build();
    }

    // DTOs optimized for Postman testing
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EquipeRequest {
        private Integer idEquipe;  // Optional for POST, required for PUT
        private String nomEquipe;
        private Niveau niveau;     // Should be one of: JUNIOR, SENIOR, EXPERT
        private DetailEquipe detailEquipe;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EquipeResponse {
        private Integer idEquipe;
        private String nomEquipe;
        private Niveau niveau;
        private DetailEquipe detailEquipe;
    }

    private Equipe convertToEntity(EquipeRequest request) {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(request.getIdEquipe());
        equipe.setNomEquipe(request.getNomEquipe());
        equipe.setNiveau(request.getNiveau());
        equipe.setDetailEquipe(request.getDetailEquipe());
        return equipe;
    }

    private EquipeResponse convertToResponse(Equipe equipe) {
        return new EquipeResponse(
                equipe.getIdEquipe(),
                equipe.getNomEquipe(),
                equipe.getNiveau(),
                equipe.getDetailEquipe()
        );
    }
}