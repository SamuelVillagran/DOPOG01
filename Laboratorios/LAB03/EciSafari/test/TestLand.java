package test;


import domain.Land;
import domain.Grass;
import domain.EcoSafari;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*; 
/**
 * The test class TestLand.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestLand
{
    private EcoSafari habitat;
    
    @Before
    public void setUp() {
        habitat = new EcoSafari();
    }
    
    @Test
    public void shouldGrass() {
        for (int i = 0; i <= 1000; i++){
            habitat.ticTac();
        }
        for (int i = 0; i < 25; i++){
            for (int u = 0; u < 25; u++){
                assertTrue(habitat.get(i,u) instanceof Grass);
            }
        }
    }
}