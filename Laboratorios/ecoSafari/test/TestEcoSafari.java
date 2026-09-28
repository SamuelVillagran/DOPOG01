package test;
import domain.EcoSafari;
import domain.Elephant;

import domain.*;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
/**
 * The test class EcoSafari.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */



public class TestEcoSafari {
    
    private EcoSafari game;
    private Elephant dumbo; 
    private Elephant babar; 
    private Storm sharkNado;
    private Elephant white;
    
    @BeforeEach
    public void setUp() {
        game = new EcoSafari();
        dumbo = (Elephant) game.get(5, 5);
        babar = (Elephant) game.get(10, 10);
        sharkNado = (Storm) game.get(4, 0);
    }
    @Test
    public void shouldTicTac() {
        int[] initialPosDumbo = game.find(dumbo);
        int[] initialPosBabar = game.find(babar);
        game.ticTac();
        int[] nextPosDumbo = game.find(dumbo);
        int[] nextPosBabar = game.find(babar);
        assertFalse(Arrays.equals(initialPosDumbo, nextPosDumbo)); // No deberian estar en la misma posicion
        
        assertFalse(Arrays.equals(initialPosBabar, nextPosBabar));

        assertFalse(Arrays.equals(initialPosBabar, nextPosBabar)); 

    }
    
    @Test
    public void shouldMoveStorm() {
        game.ticTac();
        Storm proofStorm = (Storm) game.get(3, 1);
        assertEquals(proofStorm, sharkNado);
        game.ticTac();
        proofStorm = (Storm) game.get(2, 2); 
        assertEquals(proofStorm, sharkNado);
        game.ticTac();
        proofStorm = (Storm) game.get(1, 3); // Hace recorrido
        assertEquals(proofStorm, sharkNado);
        game.ticTac();
        proofStorm = (Storm) game.get(0, 4); 
        assertEquals(proofStorm, sharkNado);
        game.ticTac();
        proofStorm = (Storm) game.get(4, 0); // Aqui finaliza en la última y se reinicia
        assertEquals(proofStorm, sharkNado);
    }
    
    @Test
    public void shouldStormDestroyElephant() {
        white =new Elephant(game, 3, 1);
        game.ticTac();
        assertEquals(sharkNado, game.get(3, 1));   // Storm ocupa la celda
        assertNull(game.find(white));               // el elefante ya no está en el tablero
    }
}