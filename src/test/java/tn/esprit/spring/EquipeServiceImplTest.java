package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.*;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;
import java.util.*;
import javax.persistence.EntityNotFoundException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    private Equipe equipe1;
    private Etudiant etudiant1;
    private Contrat contrat1;

    @BeforeEach
    void setUp() {
        // Initialize first equipe
        equipe1 = new Equipe();
        equipe1.setIdEquipe(1);
        equipe1.setNomEquipe("Team A");
        equipe1.setNiveau(Niveau.JUNIOR);
        equipe1.setEtudiants(new HashSet<>());

        // Initialize student
        etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiant1.setPrenomE("John");
        etudiant1.setNomE("Doe");
        etudiant1.setOp(Option.GAMIX);

        // Initialize contract with proper dates
        contrat1 = new Contrat();
        contrat1.setIdContrat(1);
        contrat1.setMontantContrat(1200);
        contrat1.setArchive(false);

        // Set contract dates for more than a year ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -2); // 2 years ago
        contrat1.setDateFinContrat(calendar.getTime());

        // Setup relationships
        Set<Contrat> contrats = new HashSet<>();
        contrats.add(contrat1);
        etudiant1.setContrats(contrats);
        contrat1.setEtudiant(etudiant1);

        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant1);
        equipe1.setEtudiants(etudiants);
    }

    @Test
    void testAddEquipe() {
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe1);

        Equipe savedEquipe = equipeService.addEquipe(equipe1);

        assertNotNull(savedEquipe);
        assertEquals("Team A", savedEquipe.getNomEquipe());
        verify(equipeRepository).save(any(Equipe.class));
    }

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> equipes = Collections.singletonList(equipe1);
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(equipeRepository).findAll();
    }

    @Test
    void testDeleteEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe1));
        doNothing().when(equipeRepository).delete(any(Equipe.class));

        equipeService.deleteEquipe(1);

        verify(equipeRepository).findById(1);
        verify(equipeRepository).delete(any(Equipe.class));
    }

    @Test
    void testDeleteEquipe_ThrowsException() {
        when(equipeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                equipeService.deleteEquipe(1)
        );

        verify(equipeRepository).findById(1);
        verify(equipeRepository, never()).delete(any(Equipe.class));
    }

    @Test
    void testRetrieveEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe1));

        Equipe foundEquipe = equipeService.retrieveEquipe(1);

        assertNotNull(foundEquipe);
        assertEquals(1, foundEquipe.getIdEquipe());
        assertEquals("Team A", foundEquipe.getNomEquipe());
        verify(equipeRepository).findById(1);
    }
}