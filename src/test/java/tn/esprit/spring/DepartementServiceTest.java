package tn.esprit.spring;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class DepartementServiceTest {

    @Mock
    DepartementRepository departementRepository;

    @InjectMocks
    DepartementServiceImpl departementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllDepartements() {
        // Mock data
        List<Departement> mockDepartements = new ArrayList<>();
        mockDepartements.add(new Departement("Departement 1"));
        mockDepartements.add(new Departement("Departement 2"));

        // Define behavior of mock repository
        when(departementRepository.findAll()).thenReturn(mockDepartements);

        // Call service method
        List<Departement> departements = departementService.retrieveAllDepartements();

        // Log result
        log.info("Retrieved {} departements: {}", departements.size(), departements);

        // Assertions
        assertNotNull(departements);
        assertEquals(2, departements.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void addDepartement() {
        // Mock data
        Departement d = new Departement("Mock Departement");

        // Define behavior of mock repository
        when(departementRepository.save(any(Departement.class))).thenReturn(d);

        // Call service method
        Departement addedDepartement = departementService.addDepartement(d);

        // Log result
        log.info("Added departement: {}", addedDepartement);

        // Assertions
        assertNotNull(addedDepartement);
        assertEquals("Mock Departement", addedDepartement.getNomDepart());
        verify(departementRepository, times(1)).save(any(Departement.class));
    }

    @Test
    void updateDepartement() {
        // Mock data
        Departement d = new Departement("Old Name");
        d.setIdDepart(1);
        Departement updated = new Departement("Updated Name");
        updated.setIdDepart(1);

        // Define behavior of mock repository
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(d));
        when(departementRepository.save(any(Departement.class))).thenReturn(updated);

        // Update Departement name
        d.setNomDepart("Updated Name");
        Departement updatedDepartement = departementService.updateDepartement(d);

        // Log result
        log.info("Updated departement: {}", updatedDepartement);

        // Assertions
        assertNotNull(updatedDepartement);
        assertEquals("Updated Name", updatedDepartement.getNomDepart());
        verify(departementRepository, times(1)).save(any(Departement.class));
    }

    @Test
    void deleteDepartement() {
        // Mock data
        Departement d = new Departement();
        d.setIdDepart(1);

        // Define behavior of mock repository
        when(departementRepository.findById(anyInt())).thenReturn(Optional.of(d));

        // Call delete method
        departementService.deleteDepartement(1);

        // Log result
        log.info("Deleted departement with ID: {}", d.getIdDepart());

        // Verify deletion
        verify(departementRepository, times(1)).delete(any(Departement.class));
    }
}

