package tn.esprit.spring.kaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ContratServiceImplTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindContratById() {
        // Given
        Contrat contrat = new Contrat();
        contrat.setId(1);
        when(contratRepository.findById(1)).thenReturn(java.util.Optional.of(contrat));

        // When
        Contrat found = contratService.findContratById(1);

        // Then
        assertNotNull(found, "Contract should be found by ID");
    }
}
