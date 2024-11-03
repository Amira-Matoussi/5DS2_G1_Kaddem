package tn.esprit.spring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;

@ExtendWith(MockitoExtension.class)
public class TestDetailEquipeImpl {

    @InjectMocks
    private DetailEquipe detailEquipe; // This will be instantiated with mocked dependencies

    @Mock
    private Equipe equipe; // Mocked Equipe object

    @BeforeEach
    public void setUp() {
        detailEquipe = new DetailEquipe(1, 101, "Team Strategy"); // Create a new instance for testing
    }

    @Test
    public void testGetIdDetailEquipe() {
        assertEquals(1, detailEquipe.getIdDetailEquipe());
    }

    @Test
    public void testGetSalle() {
        assertEquals(101, detailEquipe.getSalle());
    }

    @Test
    public void testGetThematique() {
        assertEquals("Team Strategy", detailEquipe.getThematique());
    }

    @Test
    public void testSetSalle() {
        detailEquipe.setSalle(102);
        assertEquals(102, detailEquipe.getSalle());
    }

    @Test
    public void testSetThematique() {
        detailEquipe.setThematique("New Team Strategy");
        assertEquals("New Team Strategy", detailEquipe.getThematique());
    }

    @Test
    public void testSetEquipe() {
        // Using the mocked equipe
        detailEquipe.setEquipe(equipe);
        assertEquals(equipe, detailEquipe.getEquipe());
    }

    @Test
    public void testDetailEquipeConstructor() {
        DetailEquipe newDetailEquipe = new DetailEquipe(2, 201, "Team Tactics");
        assertEquals(2, newDetailEquipe.getIdDetailEquipe());
        assertEquals(201, newDetailEquipe.getSalle());
        assertEquals("Team Tactics", newDetailEquipe.getThematique());
    }
}
