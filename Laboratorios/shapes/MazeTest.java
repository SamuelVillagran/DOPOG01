

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MazeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MazeTest
{
    @Test
    public void shouldPassGoal() {
        Maze game = new Maze(1);
        game.moveRobot('e',2);
        
    }
}