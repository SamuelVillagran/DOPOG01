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
    
    
    
    @BeforeEach
    public void setUp() {
        
    }
    
<<<<<<< HEAD
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
    
    @Test
    public void shouldBearMakeDamage() {
        EcoSafari game = new EcoSafari();
        Bear bear = new Bear(game, 5, 4);
        Elephant elephant = new Elephant(game, 5, 5); 
        
        for (int i = 0; i < 20; i++) {
            bear.attack();
        }
        
        // 20 ataques * 3 de daño = 60 de daño. 100 - 60 = 40.
        assertEquals(40, elephant.getEnergy());
    }
    
    @Test
    public void shouldBearMakeDamageBush() {
        EcoSafari game = new EcoSafari();
        Bear bear = new Bear(game, 15, 16);
        Bush bush = new Bush(game, 15, 15); 
        
        for (int i = 0; i < 20; i++) {
            bear.attack();
        }
        
        assertEquals(40, bush.getEnergy());
    }
    
    @Test
    public void shouldBearMoveDifferentPlace() {
        EcoSafari game = new EcoSafari();
        Bear bear = new Bear(game, 15, 16);
        
        bear.move();
        
        int[] currentPosition = game.find(bear);
        assertNotNull(currentPosition, "El oso no debería haber desaparecido");
        
        // Verificamos que haya cambiado de fila o de columna
        boolean movedRow = currentPosition[0] != 15;
        boolean movedCol = currentPosition[1] != 16;
        
        assertTrue(movedRow || movedCol, "El oso debió cambiar de posición");
    }
=======
>>>>>>> 4192d4ff8be721095eeb64279a261d772db6d79c
}