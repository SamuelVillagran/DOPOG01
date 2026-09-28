package test;

import domain.EcoSafari;
import domain.Elephant;
import domain.African;
import domain.Bush;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
/**
 * The test class TestAfrican.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestAfrican
{
    private EcoSafari game;
    private African gualdronn;
    private African steveveenn;
    private Elephant dumbo;
    
    @Test
    public void shouldTicTac() {
        game = new EcoSafari();
        gualdronn = new African(game,8,8);
        steveveenn = new African(game,15,15);
        int[] initialPosDumbo = game.find(gualdronn);
        int[] initialPosBabar = game.find(steveveenn);
        game.ticTac();
        game.ticTac();
        int[] nextPosDumbo = game.find(gualdronn);
        int[] nextPosBabar = game.find(steveveenn);
        assertFalse(Arrays.equals(initialPosDumbo, nextPosDumbo)); // No deberian estar en la misma posicion
        assertFalse(Arrays.equals(initialPosBabar, nextPosBabar));
    }
    
    @Test
    public void shouldEat() {
        game = new EcoSafari();
        gualdronn = new African(game,1,1);
        steveveenn = new African(game,15,15);
        Bush bush = new Bush(game, 2, 1);
        Bush bush1 = new Bush(game, 14, 15);
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        assertTrue( !(game.get(2, 1) instanceof Bush));  
        assertTrue( !(game.get(14, 15) instanceof Bush));  
    }
    
    @Test
    public void shouldEatNormal() {
        game = new EcoSafari();
        gualdronn = new African(game,1,1);
        dumbo = new Elephant(game,3,1);
        Bush bush = new Bush(game, 2, 1);
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        assertTrue( !(game.get(2, 1) instanceof Bush));  
        assertTrue( (game.get(4, 2) instanceof Elephant));  
        assertTrue( (game.get(2, 2) instanceof African));
    }
}