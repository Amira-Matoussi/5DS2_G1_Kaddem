package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;

import java.util.List;

public interface IDetailEquipeService {
    DetailEquipe addDetailEquipe(DetailEquipe detailEquipe);
    DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe);
    void deleteDetailEquipe(Integer id);
    DetailEquipe getDetailEquipeById(Integer id);
    List<DetailEquipe> getAllDetailEquipes();
}
