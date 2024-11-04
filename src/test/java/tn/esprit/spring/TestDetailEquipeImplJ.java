package tn.esprit.spring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.Equipe;

 class TestDetailEquipeImplJ {

    private DetailEquipe detailEquipe; // This will be a real instance of DetailEquipe

    @BeforeEach
    void setUp() {
        detailEquipe = new DetailEquipe(1, 101, "Team Strategy"); // Create a new instance for testing
    }

    @Test
     void testGetIdDetailEquipe() {
        assertEquals(1, detailEquipe.getIdDetailEquipe());
    }

    @Test
    void testGetSalle() {
        assertEquals(101, detailEquipe.getSalle());
    }

    @Test
     void testGetThematique() {
        assertEquals("Team Strategy", detailEquipe.getThematique());
    }

    @Test
    void testSetSalle() {
        detailEquipe.setSalle(102);
        assertEquals(102, detailEquipe.getSalle());
    }

    @Test
     void testSetThematique() {
        detailEquipe.setThematique("New Team Strategy");
        assertEquals("New Team Strategy", detailEquipe.getThematique());
    }

    @Test
     void testSetEquipe() {
        Equipe equipe = new Equipe(); // Create a new instance of Equipe
        detailEquipe.setEquipe(equipe);
        assertEquals(equipe, detailEquipe.getEquipe());
    }

    @Test
     void testDetailEquipeConstructor() {
        DetailEquipe newDetailEquipe = new DetailEquipe(2, 201, "Team Tactics");
        assertEquals(2, newDetailEquipe.getIdDetailEquipe());
        assertEquals(201, newDetailEquipe.getSalle());
        assertEquals("Team Tactics", newDetailEquipe.getThematique());
    }
}
