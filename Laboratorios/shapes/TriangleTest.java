

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TriangleTest.
 *
 * @author  Gualdron - Villagran
 * @version 1.0
 */
public class TriangleTest {

    private Triangle trle;

    @BeforeEach
    public void setUp() {
        trle = new Triangle();
    }

    @AfterEach
    public void tearDown() {
        trle = null;
    }
    
    @Test
    public void shouldGiveArea12() {
        
        trle.changeSize(2, 12);
        assertEquals(12, trle.area());
        trle.changeSize(12, 2);
        assertEquals(12, trle.area());
    }
    
    @Test
    public void shouldGiveArea32() {

        trle.changeSize(8, 8);
        assertEquals(32, trle.area());
    }
    
    @Test
    public void shouldCantCalculateArea() {
        
        trle.changeSize(-10, 2);
        assertEquals(0, trle.area());
    }
    
    
    @Test
    public void shouldConvertIntoAnIdealEquilateralTriangle() {
        
        trle.changeSize(140, 230);
        trle.equilateral(); /* Tiene que cumplir que sean areas aproximadamente cercanas al area ideal de un triangulo equilatero.
             Esto por el truncamiento*/
        int areaTriangle = trle.area(), proofAreaEquilateral = (int) ((Math.sqrt(3)/4)*Math.pow(trle.getWidth(), 2)); 
        double delta = proofAreaEquilateral*0.01 /* <- tolerancia*/;
        assertEquals(areaTriangle, proofAreaEquilateral, delta); /* delta es el error que puede 
            cometer el calculo de proofAreaEquilateral */
    }
    
    @Test
    public void shouldConvertIntoEquilateralTriangle() {
        
        trle.changeSize(9401, 2307); // Si se cambia esto llega hasta un límite, después falla
        int areaBefore = trle.area();
        trle.equilateral(); /* Tiene que cumplir que sean areas aproximadamente 
            cercanas al area antes de convertirlo a equilatero*/
        int areaTriangleEq = trle.area(); 
        double delta = areaTriangleEq*0.02 /* <- tolerancia*/;
        assertEquals(areaTriangleEq, areaBefore, delta); // delta es el error que puede cometer el calculo del area
    }
    
    @Test
    public void shouldWalkPositive() {
        int xPosBefore = trle.getXPosition();
        trle.walk(5);
        int movement = 5*10;
        int xPosNow = trle.getXPosition();
        assertEquals(xPosNow, xPosBefore+movement);
    }
    
    @Test
    public void shouldWalkNegative() {
        int xPosBefore = trle.getXPosition();
        trle.walk(-7);
        int movement = (-7)*10;
        int xPosNow = trle.getXPosition();
        assertEquals(xPosNow, xPosBefore+movement);
    }
    
    @Test
    public void shouldWalkNoMove() {
        int xPosBefore = trle.getXPosition();
        trle.walk(0);
        int movement = 0;
        int xPosNow = trle.getXPosition();
        assertEquals(xPosNow, xPosBefore+movement);
    }
}
