package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class UniversiteServiceImplTest {

    @Autowired
    IUniversiteService universiteService;

    @Test
    public void testAddUniversite() {
        // Create a new Universite object with sample data
        Universite u = new Universite();
        u.setNomUniv("Sample University");

        // Add the Universite using the service
        Universite universite = universiteService.addUniversite(u);

        // Log the created Universite
        log.info("Created universite: {}", universite);

        // Assertions to verify the Universite was created correctly
        assertNotNull(universite.getIdUniv());
        assertNotNull(universite.getNomUniv());
        assertTrue(universite.getNomUniv().length() > 0);

        // Clean up by deleting the test Universite
        universiteService.deleteUniversite(universite.getIdUniv());
    }

    @Test
    public void testRetrieveAllUniversites() {
        // Add a new Universite for the purpose of this test
        Universite universite = new Universite();
        universite.setNomUniv("Test University for Retrieve");
        universiteService.addUniversite(universite);

        // Retrieve all Universites
        List<Universite> universites = universiteService.retrieveAllUniversites();

        // Log the count of retrieved Universites
        log.info("Number of universites retrieved: {}", universites.size());

        // Assertions to ensure the list contains at least the one we added
        assertNotNull(universites);
        assertTrue(universites.size() > 0);

        // Clean up by deleting the test Universite
        universiteService.deleteUniversite(universite.getIdUniv());
    }

    @Test
    public void testUpdateUniversite() {
        // Create a new Universite and add it
        Universite u = new Universite();
        u.setNomUniv("Old University Name");
        Universite savedUniversite = universiteService.addUniversite(u);

        // Update the Universite's name
        savedUniversite.setNomUniv("Updated University Name");
        Universite updatedUniversite = universiteService.updateUniversite(savedUniversite);

        // Log the updated Universite
        log.info("Updated universite: {}", updatedUniversite);

        // Assertions to verify the update was successful
        assertEquals("Updated University Name", updatedUniversite.getNomUniv());

        // Clean up
        universiteService.deleteUniversite(updatedUniversite.getIdUniv());
    }

    @Test
    public void testDeleteUniversite() {
        // Create and save a new Universite
        Universite u = new Universite();
        u.setNomUniv("University to Delete");
        Universite savedUniversite = universiteService.addUniversite(u);

        // Delete the Universite
        universiteService.deleteUniversite(savedUniversite.getIdUniv());

        // Try to retrieve the deleted Universite and expect an exception
        try {
            universiteService.retrieveUniversite(savedUniversite.getIdUniv());
            fail("Expected NoSuchElementException to be thrown");
        } catch (NoSuchElementException e) {
            // Log the successful catch of the exception
            log.info("Successfully caught NoSuchElementException after deletion, as expected.");
        }
    }

    @Test
    public void testAssignUniversiteToDepartement() {

    }

    @Test
    public void testRetrieveDepartementsByUniversite() {

    }

}
