package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.*;

public class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllContrats() {
        // Given
        List<Contrat> contrats = Arrays.asList(new Contrat(), new Contrat());
        when(contratRepository.findAll()).thenReturn(contrats);

        // When
        List<Contrat> result = contratService.retrieveAllContrats();

        // Then
        assertEquals(2, result.size(), "Should return all contracts");
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    public void testAddContrat() {
        // Given
        Contrat contrat = new Contrat();
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // When
        Contrat result = contratService.addContrat(contrat);

        // Then
        assertNotNull(result, "The saved contract should not be null");
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    public void testAffectContratToEtudiant() {
        // Given
        String nomE = "John";
        String prenomE = "Doe";
        Integer idContrat = 1;

        Etudiant etudiant = new Etudiant();
        etudiant.setNomE(nomE);
        etudiant.setPrenomE(prenomE);
        etudiant.setContrats(new HashSet<>());

        Contrat contrat = new Contrat();
        contrat.setIdContrat(idContrat);
        contrat.setArchive(false);

        when(etudiantRepository.findByNomEAndPrenomE(nomE, prenomE)).thenReturn(etudiant);
        when(contratRepository.findByIdContrat(idContrat)).thenReturn(contrat);
        when(contratRepository.save(contrat)).thenReturn(contrat);

        // When
        Contrat result = contratService.affectContratToEtudiant(idContrat, nomE, prenomE);

        // Then
        assertNotNull(result.getEtudiant(), "The contract should be assigned to a student");
        assertEquals(etudiant, result.getEtudiant(), "The contract should be assigned to the correct student");
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    public void testGetChiffreAffaireEntreDeuxDates() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 30L * 24 * 60 * 60 * 1000); // 1 month later

        Contrat contrat1 = new Contrat();
        contrat1.setSpecialite(Specialite.IA);
        Contrat contrat2 = new Contrat();
        contrat2.setSpecialite(Specialite.CLOUD);

        when(contratRepository.findAll()).thenReturn(Arrays.asList(contrat1, contrat2));

        // When
        float result = contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);

        // Then
        float expected = 300 + 400; // Based on your pricing for IA and CLOUD
        assertEquals(expected, result, 0.1, "The calculated revenue should match the expected value");
        verify(contratRepository, times(1)).findAll();
    }
}
