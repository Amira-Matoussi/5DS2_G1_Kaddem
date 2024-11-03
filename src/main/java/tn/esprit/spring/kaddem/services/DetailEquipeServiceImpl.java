package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;

import java.util.List;
import java.util.Optional;

@Service

public class DetailEquipeServiceImpl implements IDetailEquipeService{
    @Autowired
    private DetailEquipeRepository detailEquipeRepository;

    @Override
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe) {
        return detailEquipeRepository.save(detailEquipe);
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe) {
        return detailEquipeRepository.save(detailEquipe);
    }

    @Override
    public void deleteDetailEquipe(Integer id) {
        detailEquipeRepository.deleteById(id);
    }

    @Override
    public DetailEquipe getDetailEquipeById(Integer id) {
        Optional<DetailEquipe> optionalDetailEquipe = detailEquipeRepository.findById(id);
        return optionalDetailEquipe.orElse(null);
    }

    @Override
    public List<DetailEquipe> getAllDetailEquipes() {
        return (List<DetailEquipe>)  detailEquipeRepository.findAll();
    }
}
