package tn.esprit.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
@CrossOrigin(origins = "*")
public class EtudiantRestController {
	private final IEtudiantService etudiantService;

	@Autowired
	public EtudiantRestController(IEtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}

	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantService.retrieveAllEtudiants();
	}

	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}

	@PostMapping("/add-etudiant")
	public Etudiant addEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant etudiant = convertToEntity(etudiantDTO);
		return etudiantService.addEtudiant(etudiant);
	}

	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	@PutMapping("/update-etudiant")
	public Etudiant updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
		Etudiant etudiant = convertToEntity(etudiantDTO);
		return etudiantService.updateEtudiant(etudiant);
	}

	@PutMapping("/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(
			@PathVariable("etudiantId") Integer etudiantId,
			@PathVariable("departementId") Integer departementId) {
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
	}

	@PostMapping("/add-etudiant/{idContrat}/{idEquipe}")
	public Etudiant addEtudiantWithEquipeAndContract(
			@RequestBody EtudiantDTO etudiantDTO,
			@PathVariable("idContrat") Integer idContrat,
			@PathVariable("idEquipe") Integer idEquipe) {
		Etudiant etudiant = convertToEntity(etudiantDTO);
		return etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);
	}

	@GetMapping("/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {
		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

	// Helper method to convert DTO to Entity
	private Etudiant convertToEntity(EtudiantDTO etudiantDTO) {
		Etudiant etudiant = new Etudiant();
		if (etudiantDTO.getIdEtudiant() != null) {
			etudiant.setIdEtudiant(etudiantDTO.getIdEtudiant());
		}
		etudiant.setNomE(etudiantDTO.getNomE());
		etudiant.setPrenomE(etudiantDTO.getPrenomE());
		try {
			etudiant.setOp(Option.valueOf(etudiantDTO.getOp()));
		} catch (IllegalArgumentException e) {
			// Handle invalid Option value
			throw new IllegalArgumentException("Invalid Option value: " + etudiantDTO.getOp());
		}
		return etudiant;
	}
}