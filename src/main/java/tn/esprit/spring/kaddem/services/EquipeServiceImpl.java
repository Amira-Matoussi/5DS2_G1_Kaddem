// EquipeServiceImpl.java
package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService {

    private final EquipeRepository equipeRepository;

    @Override
    public List<Equipe> retrieveAllEquipes() {
        return (List<Equipe>) equipeRepository.findAll();
    }

    @Override
    public Equipe addEquipe(Equipe equipe) {
        log.info("Adding new team: {}", equipe.getNomEquipe());
        return equipeRepository.save(equipe);
    }

    @Override
    public void deleteEquipe(Integer idEquipe) {
        Equipe equipe = retrieveEquipe(idEquipe);
        log.info("Deleting team with ID: {}", idEquipe);
        equipeRepository.delete(equipe);
    }

    @Override
    public Equipe retrieveEquipe(Integer equipeId) {
        return equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + equipeId));
    }

    @Override
    public Equipe updateEquipe(Equipe equipe) {
        log.info("Updating team with ID: {}", equipe.getIdEquipe());
        // Verify existence
        retrieveEquipe(equipe.getIdEquipe());
        return equipeRepository.save(equipe);
    }

    @Override
    public void evoluerEquipes() {
        log.info("Starting team evolution process");
        List<Equipe> eligibleTeams = retrieveAllEquipes();

        eligibleTeams.stream()
                .filter(this::isTeamEligibleForEvolution)
                .forEach(this::evolveTeamIfQualified);

        log.info("Team evolution process completed");
    }

    private boolean isTeamEligibleForEvolution(Equipe equipe) {
        return equipe.getNiveau() == Niveau.JUNIOR || equipe.getNiveau() == Niveau.SENIOR;
    }

    private void evolveTeamIfQualified(Equipe equipe) {
        if (countStudentsWithActiveContracts(equipe) >= 3) {
            evolveTeamLevel(equipe);
        }
    }

    private int countStudentsWithActiveContracts(Equipe equipe) {
        return (int) equipe.getEtudiants().stream()
                .filter(this::hasActiveYearLongContract)
                .count();
    }

    private boolean hasActiveYearLongContract(Etudiant etudiant) {
        if (etudiant.getContrats() == null) {
            return false;
        }
        return etudiant.getContrats().stream()
                .anyMatch(this::isContractActiveAndOlderThanYear);
    }

    private boolean isContractActiveAndOlderThanYear(Contrat contrat) {
        LocalDate contractEndDate = contrat.getDateFinContrat() == null
                ? null
                : contrat.getDateFinContrat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return !contrat.getArchive() &&
                contractEndDate != null &&
                LocalDate.now().minusYears(1).isAfter(contractEndDate);
    }

    private void evolveTeamLevel(Equipe equipe) {
        Niveau currentLevel = equipe.getNiveau();
        Niveau newLevel;

        if (currentLevel == Niveau.JUNIOR) {
            newLevel = Niveau.SENIOR;
        } else if (currentLevel == Niveau.SENIOR) {
            newLevel = Niveau.EXPERT;
        } else {
            return; // No evolution needed for other levels
        }

        updateTeamLevel(equipe, newLevel);
    }

    private void updateTeamLevel(Equipe equipe, Niveau newLevel) {
        log.info("Evolving team {} from {} to {}",
                equipe.getNomEquipe(), equipe.getNiveau(), newLevel);
        equipe.setNiveau(newLevel);
        equipeRepository.save(equipe);
    }
}