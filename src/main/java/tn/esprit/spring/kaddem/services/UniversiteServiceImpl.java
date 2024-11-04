package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Optional;

@Service
@Slf4j
public class UniversiteServiceImpl implements IUniversiteService {

    @Autowired
    UniversiteRepository universiteRepository;
    DepartementRepository departementRepository;

    public UniversiteServiceImpl() {
        log.debug("UniversiteServiceImpl instantiated.");
    }

    public List<Universite> retrieveAllUniversites() {
        log.info("Retrieving all universities..");
        List<Universite> universites = (List<Universite>) universiteRepository.findAll();
        log.debug("Retrieved {} universities", universites.size());
        return universites;
    }

    public Universite addUniversite(Universite u) {
        log.info("Adding new university : {}", u.getNomUniv());
        Universite savedUniversite = universiteRepository.save(u);
        log.info("University added with ID: {}", savedUniversite.getIdUniv());
        return savedUniversite;
    }

    public Universite updateUniversite(Universite u) {
        log.info("Updating university with ID: {}", u.getIdUniv());
        Universite updatedUniversite = universiteRepository.save(u);
        log.info("University updated: {}", updatedUniversite);
        return updatedUniversite;
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        log.info("Retrieving university with ID: {}", idUniversite);
        Optional<Universite> universiteOpt = universiteRepository.findById(idUniversite);
        if (universiteOpt.isPresent()) {
            log.debug("University found: {}", universiteOpt.get());
            return universiteOpt.get();
        } else {
            log.error("University with ID {} not found.", idUniversite);
            throw new NoSuchElementException("University not found with ID: " + idUniversite);
        }
    }

    public void deleteUniversite(Integer idUniversite) {
        log.info("Deleting university with ID: {}", idUniversite);
        Universite universite = retrieveUniversite(idUniversite);
        universiteRepository.delete(universite);
        log.info("University with ID {} deleted successfully.", idUniversite);
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        log.info("Assigning department with ID {} to university with ID {}", idDepartement, idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Departement d = departementRepository.findById(idDepartement).orElse(null);

        if (u == null) {
            log.error("University with ID {} not found.", idUniversite);
            return;
        }

        if (d == null) {
            log.error("Department with ID {} not found.", idDepartement);
            return;
        }

        u.getDepartements().add(d);
        universiteRepository.save(u);
        log.info("Department with ID {} assigned to university with ID {}", idDepartement, idUniversite);
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        log.info("Retrieving departments for university with ID {}", idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);

        if (u == null) {
            log.error("University with ID {} not found.", idUniversite);
            return null;
        }

        Set<Departement> departements = u.getDepartements();
        log.debug("Retrieved {} departments for university with ID {}", departements.size(), idUniversite);
        return departements;
    }
}
