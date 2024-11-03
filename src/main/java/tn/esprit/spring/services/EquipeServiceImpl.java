package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Equipe;
import tn.esprit.spring.entities.Etudiant;
import tn.esprit.spring.entities.Niveau;
import tn.esprit.spring.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

	public Equipe retrieveEquipe(Integer equipeId){
		return equipeRepository.findById(equipeId).get();
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes(){
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
		for (Equipe equipe : equipes) {
			if ((equipe.getNiveau().equals(Niveau.JUNIOR)) || (equipe.getNiveau().equals(Niveau.SENIOR))) {
				List<Etudiant> etudiants = (List<Etudiant>) equipe.getEtudiants();
				Integer nbEtudiantsAvecContratsActifs=0;
				for (Etudiant etudiant : etudiants) {
					Set<Contrat> contrats = etudiant.getContrats();
					for (Contrat contrat : contrats) {
						Date dateSysteme = new Date();
						long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
						long differenceInYears = (differenceInTime / (1000l * 60 * 60 * 24 * 365));
						if ((!contrat.getArchive()) && (differenceInYears > 1)) {
							nbEtudiantsAvecContratsActifs++;
							break;
						}
						if (nbEtudiantsAvecContratsActifs >= 3) break;
					}
				}
					if (nbEtudiantsAvecContratsActifs >= 3){
						if (equipe.getNiveau().equals(Niveau.JUNIOR)){
							equipe.setNiveau(Niveau.SENIOR);
							equipeRepository.save(equipe);
							break;
						}
						if (equipe.getNiveau().equals(Niveau.SENIOR)){
							equipe.setNiveau(Niveau.EXPERT);
							equipeRepository.save(equipe);
							break;
						}
				}
			}

		}

	}
}