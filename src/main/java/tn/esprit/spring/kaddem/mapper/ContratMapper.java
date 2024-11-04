package tn.esprit.spring.kaddem.mapper;

import tn.esprit.spring.kaddem.dto.ContratDTO;
import tn.esprit.spring.kaddem.entities.Contrat;

public class ContratMapper {
    // Private constructor to prevent instantiation
    private ContratMapper() {
        throw new UnsupportedOperationException("Utility class");
    }
    public static ContratDTO toDTO(Contrat contrat) {
        return new ContratDTO(
                contrat.getIdContrat(),
                contrat.getDateDebutContrat(),
                contrat.getDateFinContrat(),
                contrat.getSpecialite(),
                contrat.getArchive(),
                contrat.getMontantContrat(),
                contrat.getEtudiant() != null ? contrat.getEtudiant().getIdEtudiant() : null
        );
    }

    public static Contrat toEntity(ContratDTO contratDTO) {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(contratDTO.getIdContrat());
        contrat.setDateDebutContrat(contratDTO.getDateDebutContrat());
        contrat.setDateFinContrat(contratDTO.getDateFinContrat());
        contrat.setSpecialite(contratDTO.getSpecialite());
        contrat.setArchive(contratDTO.getArchive());
        contrat.setMontantContrat(contratDTO.getMontantContrat());
        // Note: Handle Etudiant association as needed
        return contrat;
    }

}
