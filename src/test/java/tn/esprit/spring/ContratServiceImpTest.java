package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repositories.ContratRepository;
import tn.esprit.spring.services.ContratServiceImpl;
import java.util.Arrays;
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
    // JUnit test for addContrat method
    @Test
    void testAddContrat_JUnit() {
        // Since we're using Mockito, we need to define the behavior
        when(contratRepository.save(any(Contrat.class))).thenReturn(testContrat);

        Contrat result = contratService.addContrat(testContrat);

        assertNotNull(result);
        assertEquals(5000, result.getMontantContrat());
        verify(contratRepository).save(any(Contrat.class));
    }
    // Mockito test for addContrat method
    @Test
    void testAddContrat_Mockito() {
        when(contratRepository.save(any(Contrat.class))).thenReturn(testContrat);
        Contrat result = contratService.addContrat(testContrat);
        assertEquals(5000, result.getMontantContrat());
        verify(contratRepository, times(1)).save(testContrat);
    }
    // JUnit test for retrieveContrat method
    @Test
    void testRetrieveContrat_JUnit() {
        Contrat contrat = contratService.retrieveContrat(1);
        assertNull(contrat);
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
    // JUnit test for updateContrat method

    @Test
    void testUpdateContrat_JUnit() {
        // Since we're using Mockito, we need to define the behavior
        when(contratRepository.save(any(Contrat.class))).thenReturn(testContrat);

        Contrat updatedContrat = contratService.updateContrat(testContrat);

        assertNotNull(updatedContrat);
        assertEquals(5000, updatedContrat.getMontantContrat());
        verify(contratRepository).save(any(Contrat.class));
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
    // JUnit test for retrieveAllContrats method
    @Test
    void testRetrieveAllContrats_JUnit() {
        List<Contrat> contrats = contratService.retrieveAllContrats();
        assertNotNull(contrats);
        assertTrue(contrats.isEmpty());
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
}
