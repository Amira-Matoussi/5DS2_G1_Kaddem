package tn.esprit.spring.services;

import tn.esprit.spring.entities.Departement;

import java.util.List;

public interface IDepartementService {
    public List<Departement> retrieveAllDepartements();

    public Departement addDepartement (Departement d);

    public   Departement updateDepartement (Departement d);

    public  Departement retrieveDepartement (Integer idDepart);

    public  void deleteDepartement(Integer idDepartement);

}
