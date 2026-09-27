package test;


import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Bush;
import domain.EcoSafari;
import domain.Elephant;
/**
 * The test class TestBush.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestBush
{
    private Bush bush;
    @Test
    public void shouldAge() {
        EcoSafari game = new EcoSafari();
        bush = new Bush(game, 4, 10);
        assertEquals(Color.GREEN , bush.getColor());
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        assertEquals(Color.YELLOW , bush.getColor());
    }
    
    @Test
    public void shouldReproduction(){
        EcoSafari game = new EcoSafari();
        bush = new Bush(game, 5, 5);
        game.ticTac();
        game.ticTac();
        game.ticTac();
        game.ticTac();
        assertTrue(game.get(4,5) instanceof Bush);  
    }
    
    @Test
    public void shouldDead() {
    EcoSafari game = new EcoSafari();
    bush = new Bush(game, 4, 5);  
    Elephant elephant = new Elephant(game,5,5);
    assertTrue(game.get(4,5) instanceof Bush); 
    game.ticTac();
    game.ticTac();
    assertTrue( !(game.get(4,5) instanceof Bush));  
    }
    
}