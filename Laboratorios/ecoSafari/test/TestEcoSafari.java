package test;

import domain.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    
    @BeforeEach
    public void setUp() {
        game = new EcoSafari();
        dumbo = (Elephant) game.get(5, 5);
        babar = (Elephant) game.get(10, 10);
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
    }
}