package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Universite;
import tn.esprit.spring.repositories.DepartementRepository;
import tn.esprit.spring.repositories.UniversiteRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
    private final UniversiteRepository universiteRepository;
    private final DepartementRepository departementRepository;

    // Constructor injection for both repositories..
    @Autowired
    public UniversiteServiceImpl(UniversiteRepository universiteRepository, DepartementRepository departementRepository) {
        this.universiteRepository = universiteRepository;
        this.departementRepository = departementRepository;
    }
    public UniversiteServiceImpl() {
        // The default constructor is intentionally left empty.
        // It may be used for frameworks or libraries that require a no-argument constructor.
        // However, this service should not be instantiated without providing necessary dependencies.
        // Therefore, an UnsupportedOperationException is thrown to prevent misuse.
        throw new UnsupportedOperationException("This service should not be instantiated directly. Use dependency injection.");
    }
  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Departement d = departementRepository.findById(idDepartement).orElse(null);

        // Check if either Universite or Departement is null
        if (u == null) {
            throw new EntityNotFoundException("Universite not found with id: " + idUniversite);
        }

        if (d == null) {
            throw new EntityNotFoundException("Departement not found with id: " + idDepartement);
        }

        // Add the Departement to the Universite's list of Departements
        u.getDepartements().add(d);
        universiteRepository.save(u);
    }


    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Universite u = universiteRepository.findById(idUniversite).orElse(null);

        // Check if Universite is null
        if (u == null) {
            throw new EntityNotFoundException("Universite not found with id: " + idUniversite);
        }

        return u.getDepartements();
    }

}
