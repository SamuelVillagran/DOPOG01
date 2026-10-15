

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RobotTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RobotTest {
    @Test
    public void shouldCreateANewRobot(){
        Robot simon = new Robot(50, 20);
        assertEquals(50 , simon.getXPosition());
        assertEquals(20 , simon.getYPosition());
        
        //assert
    }
    
}