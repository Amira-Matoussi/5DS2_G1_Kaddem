package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Equipe;

@Repository
public interface EquipeRepository extends CrudRepository<Equipe,Integer> {



}
