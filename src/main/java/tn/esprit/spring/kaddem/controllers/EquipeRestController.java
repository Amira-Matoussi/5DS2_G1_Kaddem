package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.kaddem.entities.Equipe;

import tn.esprit.spring.kaddem.entities.EquipeDTO;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.services.IEquipeService;


import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    public List<Equipe> getEquipes() {
        return equipeService.retrieveAllEquipes();
    }
    // http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/equipe/add-equipe
    @PostMapping("/add-equipe")
    public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipe.setNiveau(Niveau.valueOf(equipeDTO.getNiveau())); // Assuming Niveau is an enum

        // Add other fields as necessary

        return equipeService.addEquipe(equipe);
    }


    // http://localhost:8089/Kaddem/equipe/remove-equipe/1
    @DeleteMapping("/remove-equipe/{equipe-id}")
    public void removeEquipe(@PathVariable("equipe-id") Integer equipeId) {
        equipeService.deleteEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/equipe/update-equipe
    @PutMapping("/update-equipe")
    public Equipe updateEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(equipeDTO.getIdEquipe());
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipe.setNiveau(Niveau.valueOf(equipeDTO.getNiveau())); // Assuming Niveau is an enum

        // Add other fields as necessary

        return equipeService.updateEquipe(equipe);
    }

    @Scheduled(cron="0 0 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }
}