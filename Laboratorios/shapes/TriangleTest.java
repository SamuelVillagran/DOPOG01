

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

    @Test
    public void shouldGiveArea12() {
        Triangle trle = new Triangle();
        trle.changeSize(2, 12);
        assertEquals(12, trle.area());
        trle.changeSize(12, 2);
        assertEquals(12, trle.area());
    }
    
    @Test
    public void shouldGiveArea32() {
        Triangle trle = new Triangle();
        trle.changeSize(8, 8);
        assertEquals(32, trle.area());
    }
    
    @Test
    public void shouldCantCalculateArea() {
        Triangle trle = new Triangle();
        trle.changeSize(-10, 2);
        assertEquals(0, trle.area());
    }
    
    
    @Test
    public void shouldConvertIntoEquilateralTriangle() {
        Triangle trle = new Triangle(); 
        trle.changeSize(40, 30);
        
    }
}
