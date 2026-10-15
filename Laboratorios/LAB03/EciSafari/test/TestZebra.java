package test;

import domain.Land;
import domain.Grass;
import domain.EcoSafari;
import domain.Zebra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test class TestZebra.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestZebra
{
    private EcoSafari habitat;

    @BeforeEach
    public void setUp() {
        habitat = new EcoSafari(); 
        
        for (int r = 0; r < 25; r++) {
            for (int c = 0; c < 25; c++) {
                new Land(habitat, r, c);
            }
        }
    }

    @Test
    public void testZebraCreationAndColor() {
        Zebra zebra = new Zebra(habitat, 2, 2);
        
        assertEquals(Color.WHITE, zebra.getColor());
        assertTrue(habitat.get(2, 2) instanceof Zebra);
    }

    @Test
    public void testZebraMovesToAvailableLand() {
        Zebra zebra = new Zebra(habitat, 2, 2);
        
        zebra.tic();
        zebra.tac();
        
        assertNotEquals(zebra, habitat.get(2, 2));
        
        int[] pos = habitat.find(zebra);
        assertNotNull(pos);
    }

    @Test
    public void testZebraEatsGrass() {
        Zebra zebra = new Zebra(habitat, 2, 2);
        Grass grass = new Grass(habitat, 2, 3);
        
        zebra.tic();
        zebra.tac();
        
        assertTrue(habitat.get(2, 3) instanceof Land);
    }

    @Test
    public void testZebraDiesWithoutEnergy() {
        Zebra zebra = new Zebra(habitat, 2, 2);
        
        for (int i = 0; i < 10; i++) {
            zebra.tic();
            zebra.tac();
        }
        
        zebra.tic();
        zebra.tac();
        
        int[] lastPos = habitat.find(zebra);
        assertNull(lastPos); 
    }

    @Test
    public void testZebraReproduction() {
        Zebra zebra1 = new Zebra(habitat, 2, 1);
        Zebra zebra2 = new Zebra(habitat, 2, 3);
        
        zebra1.tic();
        zebra2.tic();
        zebra1.tac();
        zebra2.tac();
        
        int zebraCount = 0;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (habitat.get(r, c) instanceof Zebra) {
                    zebraCount++;
                }
            }
        }
        
        assertTrue(zebraCount >= 3);
    }
}