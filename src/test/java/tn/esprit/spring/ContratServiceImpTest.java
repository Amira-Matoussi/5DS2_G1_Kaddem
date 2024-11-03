package tn.esprit.spring;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Specialite;
import tn.esprit.spring.services.IContratService;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ContratServiceImpTest {
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
}
