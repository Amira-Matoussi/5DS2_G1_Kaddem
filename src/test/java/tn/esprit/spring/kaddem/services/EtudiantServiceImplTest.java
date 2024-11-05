package tn.esprit.spring.kaddem.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class) //enables Mockito's behavior for the test class.
class EtudiantServiceImplTest {

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    private Etudiant etudiant;
    private Contrat contrat;
    private Equipe equipe;
    private Departement departement;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        contrat = new Contrat();
        contrat.setIdContrat(1);
        equipe = new Equipe();
        equipe.setIdEquipe(1);
        departement = new Departement();
        departement.setIdDepart(1);
    }

    @Test
    void testRetrieveAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);

        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(etudiant, result.get(0));
        //Checks that the result is not null, has the expected size, and contains the expected etudiant
    }
    //returns a list of all students.

    @Test
    void testAddEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant result = etudiantService.addEtudiant(etudiant);

        assertNotNull(result);
        assertEquals(etudiant, result);
    }

    @Test
    void testUpdateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        Etudiant result = etudiantService.updateEtudiant(etudiant);

        assertNotNull(result);
        assertEquals(etudiant, result); //Verifies the saved Etudiant is the expected one
    }

    @Test
    void testRetrieveEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1);

        assertNotNull(result);
        assertEquals(etudiant, result);
    }

    @Test
    void testRemoveEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        etudiantService.removeEtudiant(1);

        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    void testAssignEtudiantToDepartement() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);

        etudiantService.assignEtudiantToDepartement(1, 1);

        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

//    @Test
//    void testAddAndAssignEtudiantToEquipeAndContract() {
//        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));
//        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));
//        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
//
//        Etudiant result = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);
//
//        assertNotNull(result);
//        assertEquals(etudiant, result);
//        assertEquals(etudiant, contrat.getEtudiant());
//        assertTrue(equipe.getEtudiants().contains(etudiant));
//    }

    @Test
    void testGetEtudiantsByDepartement() {
        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(etudiant);

        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1)).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(etudiant, result.get(0));
    }
}

//k