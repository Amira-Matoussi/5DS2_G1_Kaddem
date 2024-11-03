package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Contrat;
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
    private Date dateDebut;
    private Date dateFin;

    @BeforeEach
    void setUp() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateDebut = dateFormat.parse("01/09/2023");
        dateFin = dateFormat.parse("01/09/2024");

        testContrat = new Contrat(
                dateDebut,
                dateFin,
                Specialite.IA,
                false,
                5000
        );
        testContrat.setIdContrat(1);
    }

    @Test
    void testAddContrat() {
        // Arrange
        when(contratRepository.save(any(Contrat.class))).thenReturn(testContrat);

        // Act
        Contrat result = contratService.addContrat(testContrat);

        // Assert
        assertNotNull(result);
        assertEquals(testContrat.getIdContrat(), result.getIdContrat());
        assertEquals(testContrat.getSpecialite(), result.getSpecialite());
        assertEquals(testContrat.getDateDebutContrat(), result.getDateDebutContrat());
        assertEquals(testContrat.getDateFinContrat(), result.getDateFinContrat());
        assertFalse(result.getArchive());
        assertEquals(5000, result.getMontantContrat());

        // Verify
        verify(contratRepository, times(1)).save(any(Contrat.class));
    }


    @Test
    void testRetrieveAllContrats() {
        // Arrange
        List<Contrat> mockContrats = Arrays.asList(testContrat);
        when(contratRepository.findAll()).thenReturn(mockContrats);

        // Act
        List<Contrat> result = contratService.retrieveAllContrats();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testContrat.getIdContrat(), result.get(0).getIdContrat());

        // Verify
        verify(contratRepository, times(1)).findAll();
    }

    @Test
    void testGetContratById() {
        // Arrange
        Integer contratId = 1;
        when(contratRepository.findById(contratId)).thenReturn(Optional.of(testContrat));

        // Act
        Contrat result = contratService.retrieveContrat(contratId);

        // Assert
        assertNotNull(result);
        assertEquals(contratId, result.getIdContrat());
        assertEquals(Specialite.IA, result.getSpecialite());
        assertEquals(dateDebut, result.getDateDebutContrat());
        assertEquals(dateFin, result.getDateFinContrat());
        assertFalse(result.getArchive());
        assertEquals(5000, result.getMontantContrat());

        // Verify
        verify(contratRepository, times(1)).findById(contratId);
    }

    @Test
    void testGetContratByIdNotFound() {
        // Arrange
        Integer contratId = 999;
        when(contratRepository.findById(contratId)).thenReturn(Optional.empty());

        // Act
        Contrat result = contratService.retrieveContrat(contratId);

        // Assert
        assertNull(result);

        // Verify
        verify(contratRepository, times(1)).findById(contratId);
    }
}
