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

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipeServiceImplTest {


    @Mock
    private EquipeRepository equipeRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    private Equipe testEquipe;
    private Etudiant testEtudiant;
    private Contrat testContrat;

    @BeforeEach
    void setUp() {
        testEquipe = new Equipe();
        testEquipe.setIdEquipe(1);
        testEquipe.setNomEquipe("Test Team");
        testEquipe.setNiveau(Niveau.JUNIOR);

        testEtudiant = new Etudiant();
        testEtudiant.setIdEtudiant(1);
        testEtudiant.setNomE("Test");
        testEtudiant.setPrenomE("Student");

        testContrat = new Contrat();
        testContrat.setIdContrat(1);
        testContrat.setArchive(false);
        Date endDate = Date.from(LocalDate.now().minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        testContrat.setDateFinContrat(endDate);

        Set<Contrat> contrats = new HashSet<>();
        contrats.add(testContrat);
        testEtudiant.setContrats(contrats);

        List<Etudiant> etudiants = new ArrayList<>();
        etudiants.add(testEtudiant);
        testEquipe.setEtudiants(etudiants);
    }

    // Mockito-based tests
    @Test
    void testAddEquipe() {
        when(equipeRepository.save(any(Equipe.class))).thenReturn(testEquipe);

        Equipe result = equipeService.addEquipe(testEquipe);

        assertNotNull(result);
        assertEquals("Test Team", result.getNomEquipe());
        verify(equipeRepository).save(any(Equipe.class));
    }

    @Test
    void testRetrieveEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(testEquipe));

        Equipe result = equipeService.retrieveEquipe(1);

        assertNotNull(result);
        assertEquals(1, result.getIdEquipe());
        verify(equipeRepository).findById(1);
    }

    @Test
    void testRetrieveEquipe_NotFound() {
        when(equipeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () ->
                equipeService.retrieveEquipe(1)
        );
    }

    @Test
    void testUpdateEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(testEquipe));
        when(equipeRepository.save(any(Equipe.class))).thenReturn(testEquipe);

        testEquipe.setNomEquipe("Updated Team");
        Equipe result = equipeService.updateEquipe(testEquipe);

        assertEquals("Updated Team", result.getNomEquipe());
        verify(equipeRepository).save(any(Equipe.class));
    }

    @Test
    void testDeleteEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(testEquipe));
        doNothing().when(equipeRepository).delete(any(Equipe.class));

        equipeService.deleteEquipe(1);

        verify(equipeRepository).delete(any(Equipe.class));
    }

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> equipes = Arrays.asList(testEquipe);
        when(equipeRepository.findAll()).thenReturn(equipes);

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(equipeRepository).findAll();
    }

    @Test
    void testEvoluerEquipes_JuniorToSenior() {
        // Setup three students with valid contracts
        List<Etudiant> etudiants = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Etudiant e = new Etudiant();
            Contrat c = new Contrat();
            c.setArchive(false);
            c.setDateFinContrat(Date.from(LocalDate.now().minusYears(2)
                    .atStartOfDay(ZoneId.systemDefault()).toInstant()));
            Set<Contrat> studentContracts = new HashSet<>();
            studentContracts.add(c);
            e.setContrats(studentContracts);
            etudiants.add(e);
        }

        testEquipe.setEtudiants(etudiants);
        testEquipe.setNiveau(Niveau.JUNIOR);

        when(equipeRepository.findAll()).thenReturn(Collections.singletonList(testEquipe));
        when(equipeRepository.save(any(Equipe.class))).thenReturn(testEquipe);

        equipeService.evoluerEquipes();

        assertEquals(Niveau.SENIOR, testEquipe.getNiveau());
        verify(equipeRepository).save(any(Equipe.class));
    }

    // Pure JUnit tests (no mocks)
    @Test
    void testEquipeConstructor() {
        String teamName = "Test Team";
        Niveau niveau = Niveau.JUNIOR;

        Equipe equipe = new Equipe(teamName, niveau);

        assertNotNull(equipe);
        assertEquals(teamName, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
    }

    @Test
    void testEquipeSettersAndGetters() {
        Equipe equipe = new Equipe();
        Integer id = 1;
        String name = "Test Team";
        Niveau niveau = Niveau.SENIOR;
        List<Etudiant> etudiants = new ArrayList<>();

        equipe.setIdEquipe(id);
        equipe.setNomEquipe(name);
        equipe.setNiveau(niveau);
        equipe.setEtudiants(etudiants);

        assertEquals(id, equipe.getIdEquipe());
        assertEquals(name, equipe.getNomEquipe());
        assertEquals(niveau, equipe.getNiveau());
        assertEquals(etudiants, equipe.getEtudiants());
    }

    @Test
    void testEquipeDTO() {
        Integer id = 1;
        String name = "Test Team";
        String niveau = "SENIOR";

        EquipeDTO dto = new EquipeDTO(id, name, niveau);

        assertEquals(id, dto.getIdEquipe());
        assertEquals(name, dto.getNomEquipe());
        assertEquals(niveau, dto.getNiveau());
    }

    @Test
    void testEquipeDTOSettersAndGetters() {
        EquipeDTO dto = new EquipeDTO();
        Integer id = 1;
        String name = "Test Team";
        String niveau = "EXPERT";

        dto.setIdEquipe(id);
        dto.setNomEquipe(name);
        dto.setNiveau(niveau);

        assertEquals(id, dto.getIdEquipe());
        assertEquals(name, dto.getNomEquipe());
        assertEquals(niveau, dto.getNiveau());
    }
}  
