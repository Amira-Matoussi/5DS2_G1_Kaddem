package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService {
	private final EtudiantRepository etudiantRepository;
	private final ContratRepository contratRepository;
	private final EquipeRepository equipeRepository;
	private final DepartementRepository departementRepository;

	// Constructor injection
	@Autowired
	public EtudiantServiceImpl(
			EtudiantRepository etudiantRepository,
			ContratRepository contratRepository,
			EquipeRepository equipeRepository,
			DepartementRepository departementRepository) {
		this.etudiantRepository = etudiantRepository;
		this.contratRepository = contratRepository;
		this.equipeRepository = equipeRepository;
		this.departementRepository = departementRepository;
	}
	public List<Etudiant> retrieveAllEtudiants(){
		return (List<Etudiant>) etudiantRepository.findAll();
	}

	public Etudiant addEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant updateEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant retrieveEtudiant(Integer idEtudiant) {
		Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);
		if (optionalEtudiant.isPresent()) {
			return optionalEtudiant.get();
		} else {
			// Handle the case where the Etudiant is not found
			// You can throw an exception, return null, or handle it as per your requirements
			throw new EntityNotFoundException("Etudiant not found with id: " + idEtudiant);
		}
	}

	public void removeEtudiant(Integer idEtudiant){
		Etudiant e=retrieveEtudiant(idEtudiant);
		etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
		Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
		Departement departement = departementRepository.findById(departementId).orElse(null);

		// Check if the Etudiant and Departement are found
		if (etudiant == null) {
			throw new EntityNotFoundException("Etudiant not found with id: " + etudiantId);
		}

		if (departement == null) {
			throw new EntityNotFoundException("Departement not found with id: " + departementId);
		}

		// Assign the Departement to the Etudiant
		etudiant.setDepartement(departement);
		etudiantRepository.save(etudiant);
	}

	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
		Contrat c = contratRepository.findById(idContrat).orElse(null);
		Equipe eq = equipeRepository.findById(idEquipe).orElse(null);

		// Check if the Contrat and Equipe are found
		if (c == null) {
			throw new EntityNotFoundException("Contrat not found with id: " + idContrat);
		}

		if (eq == null) {
			throw new EntityNotFoundException("Equipe not found with id: " + idEquipe);
		}

		// Assign the Etudiant to the Contrat
		c.setEtudiant(e);

		// Add the Etudiant to the Equipe's list of Etudiants
		eq.getEtudiants().add(e);

		// Optionally, save the Contrat and Equipe if necessary
		contratRepository.save(c);
		equipeRepository.save(eq);

		return e;
	}


	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
		return  etudiantRepository.findEtudiantsByDepartement_IdDepart((idDepartement));
	}}