package tn.esprit.spring.kaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.EtudiantDTO;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
public class EtudiantRestController {
	private final IEtudiantService etudiantService;

	// Constructor injection
	@Autowired
	public EtudiantRestController(IEtudiantService etudiantService) {
		this.etudiantService = etudiantService;
	}
	// http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantService.retrieveAllEtudiants();
	}
	// http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		return etudiantService.retrieveEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/add-etudiant
	@PostMapping("/add-etudiant")
	public Etudiant addEtudiant(@RequestBody EtudiantDTO eDTO) {
		// Create a new Etudiant entity
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE(eDTO.getNomE());
		etudiant.setPrenomE(eDTO.getPrenomE());
		etudiant.setOp(Option.valueOf(eDTO.getOp())); // Assuming Option can be set this way

		return etudiantService.addEtudiant(etudiant);
	}

	// http://localhost:8089/Kaddem/etudiant/remove-etudiant/1
	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		etudiantService.removeEtudiant(etudiantId);
	}

	// http://localhost:8089/Kaddem/etudiant/update-etudiant
	@PutMapping("/update-etudiant")
	public Etudiant updateEtudiant(@RequestBody EtudiantDTO eDTO) {
		// Create a new Etudiant entity
		Etudiant etudiant = new Etudiant();
		etudiant.setIdEtudiant(eDTO.getIdEtudiant()); // Set the ID for updating
		etudiant.setNomE(eDTO.getNomE());
		etudiant.setPrenomE(eDTO.getPrenomE());
		etudiant.setOp(Option.valueOf(eDTO.getOp())); // Assuming Option can be set this way

		return etudiantService.updateEtudiant(etudiant);
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("departementId")Integer departementId){
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
	}
	//addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe)
	@PostMapping("/add-etudiant/{idContrat}/{idEquipe}")
	public Etudiant addEtudiantWithEquipeAndContract(@RequestBody EtudiantDTO eDTO,
													 @PathVariable("idContrat") Integer idContrat,
													 @PathVariable("idEquipe") Integer idEquipe) {
		// Create a new Etudiant entity from the DTO
		Etudiant etudiant = new Etudiant();
		etudiant.setNomE(eDTO.getNomE());
		etudiant.setPrenomE(eDTO.getPrenomE());
		etudiant.setOp(Option.valueOf(eDTO.getOp())); // Adjust according to your Option enum

		// Call the service method to add and assign the student to the contract and team
		return etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, idContrat, idEquipe);
	}

	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {

		return etudiantService.getEtudiantsByDepartement(idDepartement);
	}

}


