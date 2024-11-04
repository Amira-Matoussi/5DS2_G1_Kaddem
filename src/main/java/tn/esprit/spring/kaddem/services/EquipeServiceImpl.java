package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.*;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
		return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId) {
		Optional<Equipe> optionalEquipe = equipeRepository.findById(equipeId);

		// Check if the value is present
		if (optionalEquipe.isPresent()) {
			return optionalEquipe.get(); // Safely access the value
		} else {
			// Handle the absence of the value as needed
			// For example, you can throw an exception
			throw new NoSuchElementException("Equipe with ID " + equipeId + " not found");
			// Alternatively, you could return null or handle it differently
		}
	}

	public Equipe updateEquipe(Equipe e){
		return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();

		for (Equipe equipe : equipes) {
			// Skip if not JUNIOR or SENIOR
			if (!equipe.getNiveau().equals(Niveau.JUNIOR) && !equipe.getNiveau().equals(Niveau.SENIOR)) {
				continue;
			}

			List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
			Integer nbEtudiantsAvecContratsActifs = 0;

			// Count students with active contracts
			for (Etudiant etudiant : etudiants) {
				if (hasActiveContract(etudiant)) {
					nbEtudiantsAvecContratsActifs++;
				}
				// Early exit if we already have enough active contracts
				if (nbEtudiantsAvecContratsActifs >= 3) {
					break;
				}
			}

			// Update team level if needed
			if (nbEtudiantsAvecContratsActifs >= 3) {
				updateEquipeNiveau(equipe);
			}
		}
	}

	private boolean hasActiveContract(Etudiant etudiant) {
		Date dateSysteme = new Date();

		for (Contrat contrat : etudiant.getContrats()) {
			// Check if contrat is null before proceeding
			if (contrat == null || contrat.getArchive()) {
				continue;
			}

			// Ensure that getDateFinContrat() does not return null
			Date dateFinContrat = contrat.getDateFinContrat();
			if (dateFinContrat != null) {
				long differenceInTime = dateSysteme.getTime() - dateFinContrat.getTime();
				long differenceInYears = differenceInTime / (1000L * 60 * 60 * 24 * 365);

				// Check if the contract is active (not finished over a year ago)
				if (differenceInYears <= 1) {
					return true; // Active contract found
				}
			}
		}
		return false; // No active contracts found

	}

	private void updateEquipeNiveau(Equipe equipe) {
		if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
			equipe.setNiveau(Niveau.SENIOR);
			equipeRepository.save(equipe);
		} else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
			equipe.setNiveau(Niveau.EXPERT);
			equipeRepository.save(equipe);
		}
	}
}