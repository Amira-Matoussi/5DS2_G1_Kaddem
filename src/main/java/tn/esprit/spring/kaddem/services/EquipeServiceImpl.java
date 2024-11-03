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
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

/**
 * Service Implementation for managing Equipe operations
 */
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

    /**
     * Checks if a team is eligible for evolution (JUNIOR or SENIOR level)
     */
    private boolean isTeamEligibleForEvolution(Equipe equipe) {
        return equipe.getNiveau() == Niveau.JUNIOR ||
                equipe.getNiveau() == Niveau.SENIOR;
    }

    /**
     * Evolves team level if qualified based on active contracts
     */
    private void evolveTeamIfQualified(Equipe equipe) {
        if (countStudentsWithActiveContracts(equipe) >= 3) {
            evolveTeamLevel(equipe);
        }
    }

    /**
     * Counts students with active contracts in the team
     * */

    private int countStudentsWithActiveContracts(Equipe equipe) {
        return (int) equipe.getEtudiants().stream()
                .filter(this::hasActiveYearLongContract)
                .count();
    }

    /**
     * Checks if a student has an active contract longer than a year
     */

    private boolean hasActiveYearLongContract(Etudiant etudiant) {
        return etudiant.getContrats().stream()
                .anyMatch(this::isContractActiveAndOlderThanYear);
    }


    /**
     * Checks if a contract is active and older than a year
     * */

    private boolean isContractActiveAndOlderThanYear(Contrat contrat) {
        return !contrat.getArchive() && ChronoUnit.YEARS.between(
                contrat.getDateFinContrat().toInstant(),
                LocalDate.now().atStartOfDay()
        ) > 1;
    }


    /**
     * Evolves the team's level based on current level
     */
    private void evolveTeamLevel(Equipe equipe) {
        Niveau currentLevel = equipe.getNiveau();
        if (currentLevel == Niveau.JUNIOR) {
            updateTeamLevel(equipe, Niveau.SENIOR);
        } else if (currentLevel == Niveau.SENIOR) {
            updateTeamLevel(equipe, Niveau.EXPERT);
        }
    }

    /**
     * Updates team's level and saves to repository
     */
    private void updateTeamLevel(Equipe equipe, Niveau newLevel) {
        log.info("Evolving team {} from {} to {}",
                equipe.getNomEquipe(), equipe.getNiveau(), newLevel);
        equipe.setNiveau(newLevel);
        equipeRepository.save(equipe);
    }
}