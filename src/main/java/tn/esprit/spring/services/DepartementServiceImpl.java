package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repositories.DepartementRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j

@Service
public class DepartementServiceImpl implements IDepartementService{
	private final DepartementRepository departementRepository;

	// Constructor injection
	@Autowired
	public DepartementServiceImpl(DepartementRepository departementRepository) {
		this.departementRepository = departementRepository;
	}
	public List<Departement> retrieveAllDepartements(){
		return (List<Departement>) departementRepository.findAll();
	}

	public Departement addDepartement (Departement d){
		return departementRepository.save(d);
	}

	public   Departement updateDepartement (Departement d){
		return departementRepository.save(d);
	}

	public Departement retrieveDepartement(Integer idDepart) {
		Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);

		if (optionalDepartement.isPresent()) {
			return optionalDepartement.get();
		} else {
			// Handle the case when the Departement is not found
			throw new EntityNotFoundException("Departement not found with id: " + idDepart);
		}
	}
	public  void deleteDepartement(Integer idDepartement){
		Departement d=retrieveDepartement(idDepartement);
		departementRepository.delete(d);
	}



}