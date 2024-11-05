package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
class UniversiteServiceImplTest {

    @Mock
    UniversiteRepository universiteRepository;

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveAllUniversites() {
        // Mock data
        List<Universite> mockUniversites = new ArrayList<>();
        mockUniversites.add(new Universite("University 1"));
        mockUniversites.add(new Universite("University 2"));

        // Now let's Define behavior of mock repository
        when(universiteRepository.findAll()).thenReturn(mockUniversites);

        // Call service method
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Log result
        log.info("Retrieved {} universites: {}", universites.size(), universites);

        // Assertions
        assertNotNull(universites);
        assertEquals(2, universites.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void addUniversite() {
        // Mock data
        Universite u = new Universite("Mock University");

        // Define behavior of mock repository
        when(universiteRepository.save(any(Universite.class))).thenReturn(u);

        // Call service method
        Universite addedUniversite = universiteService.addUniversite(u);

        // Log result
        log.info("Added universite: {}", addedUniversite);

        // Assertions
        assertNotNull(addedUniversite);
        assertEquals("Mock University", addedUniversite.getNomUniv());
        verify(universiteRepository, times(1)).save(any(Universite.class));
    }

    @Test
    void updateUniversite() {
        // Mock data
        Universite u = new Universite("Old Name");
        u.setIdUniv(1);
        Universite updated = new Universite("Updated Name");
        updated.setIdUniv(1);

        // Define behavior of mock repository
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(u));
        when(universiteRepository.save(any(Universite.class))).thenReturn(updated);

        // Update Universite name
        u.setNomUniv("Updated Name");
        Universite updatedUniversite = universiteService.updateUniversite(u);

        // Log result
        log.info("Updated universite: {}", updatedUniversite);

        // Assertions
        assertNotNull(updatedUniversite);
        assertEquals("Updated Name", updatedUniversite.getNomUniv());
        verify(universiteRepository, times(1)).save(any(Universite.class));
    }

    @Test
    void deleteUniversite() {
        // Mock data
        Universite u = new Universite();
        u.setIdUniv(1);

        // Define behavior of mock repository
        when(universiteRepository.findById(anyInt())).thenReturn(Optional.of(u));

        // Call delete method
        universiteService.deleteUniversite(1);

        // Log result
        log.info("Deleted universite with ID: {}", u.getIdUniv());

        // Verify deletion
        verify(universiteRepository, times(1)).delete(any(Universite.class));
    }
}
