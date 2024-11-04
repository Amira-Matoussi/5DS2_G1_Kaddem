package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.ContratDTO;
import tn.esprit.spring.entities.Specialite;
import tn.esprit.spring.repositories.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratServiceImpTest {
    @Mock
    private ContratRepository contratRepository;
    @InjectMocks
    private ContratServiceImpl contratService;
    private Contrat testContrat;
    @BeforeEach
    void setUp() {
        testContrat = new Contrat();
        testContrat.setIdContrat(1);
        testContrat.setMontantContrat(5000);
    }
    // Mockito test for addContrat method
    @Test
    void testAddContrat_Mockito() {
        when(contratRepository.save(any(Contrat.class))).thenReturn(testContrat);
        Contrat result = contratService.addContrat(testContrat);
        assertEquals(5000, result.getMontantContrat());
        verify(contratRepository, times(1)).save(testContrat);
    }
    // Mockito test for retrieveContrat method
    @Test
    void testRetrieveContrat_Mockito() {
        when(contratRepository.findById(1)).thenReturn(Optional.of(testContrat));
        Contrat result = contratService.retrieveContrat(1);
        assertNotNull(result);
        assertEquals(5000, result.getMontantContrat());
        verify(contratRepository, times(1)).findById(1);
    }

    // Mockito test for updateContrat method
    @Test
    void testUpdateContrat_Mockito() {
        when(contratRepository.save(any(Contrat.class))).thenReturn(testContrat);
        Contrat result = contratService.updateContrat(testContrat);
        assertNotNull(result);
        assertEquals(5000, result.getMontantContrat());
        verify(contratRepository, times(1)).save(testContrat);
    }
    // Mockito test for retrieveAllContrats method
    @Test
    void testRetrieveAllContrats_Mockito() {
        List<Contrat> mockContrats = Arrays.asList(testContrat);
        when(contratRepository.findAll()).thenReturn(mockContrats);
        List<Contrat> result = contratService.retrieveAllContrats();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testContrat.getIdContrat(), result.get(0).getIdContrat());
        verify(contratRepository, times(1)).findAll();
    }
    @Test
    void testContratConstructor() {
        Date endDate = new Date();
        Contrat contrat = new Contrat(endDate, endDate, Specialite.IA, false, 5000);

        assertNotNull(contrat);
        assertEquals(endDate, contrat.getDateFinContrat());
        assertEquals(Specialite.IA, contrat.getSpecialite());
        assertFalse(contrat.getArchive());
        assertEquals(5000, contrat.getMontantContrat());
    }

    @Test
    void testContratSettersAndGetters() {
        Integer id = 1;
        Date endDate = new Date();
        boolean archive = false;
        int montant = 5000;

        testContrat.setIdContrat(id);
        testContrat.setDateFinContrat(endDate);
        testContrat.setArchive(archive);
        testContrat.setMontantContrat(montant);

        assertEquals(id, testContrat.getIdContrat());
        assertEquals(endDate, testContrat.getDateFinContrat());
        assertEquals(archive, testContrat.getArchive());
        assertEquals(montant, testContrat.getMontantContrat());
    }

    // If you have a ContratDTO class, you can add DTO tests as well
    @Test
    void testContratDTO() {
        Integer id = 1;
        Date dateDebut = new Date(); // Set an appropriate start date
        Date dateFin = new Date(); // Set an appropriate end date
        String specialite = "Some Speciality"; // Set an appropriate speciality
        Boolean archive = false;
        Integer montant = 5000;

        ContratDTO dto = new ContratDTO(id, dateDebut, dateFin, specialite, archive, montant);

        assertEquals(id, dto.getIdContrat());
        assertEquals(archive, dto.getArchive());
        assertEquals(montant, dto.getMontantContrat());
        assertEquals(dateDebut, dto.getDateDebutContrat());
        assertEquals(dateFin, dto.getDateFinContrat());
        assertEquals(specialite, dto.getSpecialite());
    }

    @Test
    void testContratDTOSettersAndGetters() {
        ContratDTO dto = new ContratDTO();
        Integer id = 1;
        boolean archive = false;
        int montant = 5000;

        dto.setIdContrat(id);
        dto.setArchive(archive);
        dto.setMontantContrat(montant);

        assertEquals(id, dto.getIdContrat());
        assertEquals(archive, dto.getArchive());
        assertEquals(montant, dto.getMontantContrat());
    }
}