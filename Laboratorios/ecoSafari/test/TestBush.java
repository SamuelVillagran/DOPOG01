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
        bush = new Bush();
        assertEquals(Color.GREEN , bush.getColor());
        bush.tic();
        bush.tic();
        bush.tic();
        bush.tic();
        assertEquals(Color.YELLOW , bush.getColor());
    }
    
    @Test
    public void shouldReproduction(){
        EcoSafari ecoSafari = new EcoSafari();
        bush = new Bush();
        ecoSafari.set(bush,5,5);
        bush.tic();
        bush.tic();
        assertTrue(ecoSafari.get(4,5) instanceof Bush);  
    }
    
    @Test
    public void shouldDead() {
    EcoSafari ecoSafari = new EcoSafari();
    bush = new Bush();
    ecoSafari.set(bush,5,5);    
    Elephant elephant = new Elephant(ecoSafari,4,5);
    bush.tic();
    elephant.tic();
    assertTrue( !(ecoSafari.get(4,5) instanceof Bush));  
    }
    
}