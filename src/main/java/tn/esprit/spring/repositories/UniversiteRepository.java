package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Universite;

@Repository
public interface UniversiteRepository extends CrudRepository<Universite,Integer> {


}
