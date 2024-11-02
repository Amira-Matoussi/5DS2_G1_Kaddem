package tn.esprit.spring.kaddem;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.services.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ContratServiceImplTest {

    @Autowired
    IContratService contratService;

    @Test
    public void testAddContrat() throws ParseException {
        // Create SimpleDateFormat for parsing dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse start and end dates for the contract
        Date dateDebut = dateFormat.parse("01/09/2023");
        Date dateFin = dateFormat.parse("01/09/2024");

        // Create new Contrat object
        Contrat c = new Contrat(
                dateDebut,
                dateFin,
                Specialite.IA,  // Assuming IA is one of your Specialite enum values
                false,          // not archived
                5000           // montant contrat
        );

        // Add the contract using the service
        Contrat contrat = contratService.addContrat(c);

        // Print the created contract
        System.out.print("contrat " + contrat);

        // Assertions to verify the contract was created correctly
        assertNotNull(contrat.getIdContrat());
        assertNotNull(contrat.getSpecialite());
        assertNotNull(contrat.getDateDebutContrat());
        assertNotNull(contrat.getDateFinContrat());
        assertFalse(contrat.getArchive());
        assertTrue(contrat.getMontantContrat() > 0);

        // Clean up by deleting the test contract
        contratService.removeContrat(contrat.getIdContrat());
    }

    @Test
    public void testDeleteContrat() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebut = dateFormat.parse("01/09/2023");
        Date dateFin = dateFormat.parse("01/09/2024");

        Contrat c = new Contrat(dateDebut, dateFin, Specialite.IA, false, 5000);

        Contrat contrat = contratService.addContrat(c);
        contratService.removeContrat(contrat.getIdContrat());
        assertNull(contratService.retrieveContrat(contrat.getIdContrat()));
    }

    @Test
    public void testRetrieveAllContrats() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebut = dateFormat.parse("01/09/2023");
        Date dateFin = dateFormat.parse("01/09/2024");

        List<Contrat> contrats = contratService.retrieveAllContrats();
        int expected = contrats.size();

        Contrat c = new Contrat(dateDebut, dateFin, Specialite.IA, false, 5000);
        Contrat contrat = contratService.addContrat(c);

        assertEquals(expected + 1, contratService.retrieveAllContrats().size());

        // Clean up
        contratService.removeContrat(contrat.getIdContrat());
    }
    @Test
    public void testGetContratById() throws ParseException {
        // Create a new contract first to ensure we have something to retrieve
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDebut = dateFormat.parse("01/09/2023");
        Date dateFin = dateFormat.parse("01/09/2024");

        Contrat c = new Contrat(dateDebut, dateFin, Specialite.IA, false, 5000);

        // Save the contract
        Contrat savedContrat = contratService.addContrat(c);
        Integer contratId = savedContrat.getIdContrat();

        // Retrieve the contract by ID
        Contrat retrievedContrat = contratService.retrieveContrat(contratId);

        // Assertions to verify the retrieved contract
        assertNotNull(retrievedContrat);
        assertEquals(contratId, retrievedContrat.getIdContrat());
        assertEquals(Specialite.IA, retrievedContrat.getSpecialite());
        assertEquals(dateDebut, retrievedContrat.getDateDebutContrat());
        assertEquals(dateFin, retrievedContrat.getDateFinContrat());
        assertEquals(false, retrievedContrat.getArchive());
        assertEquals(Integer.valueOf(5000), retrievedContrat.getMontantContrat());

        // Clean up
        contratService.removeContrat(contratId);

    }
}

