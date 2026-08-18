import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RoomTest.
 * This class contains unit tests for the Room class, specifically focusing
 * on the collision detection logic between a player/object and the room's walls.
 *
 * @author Gualdron - Villagran
 * @version 1.0
 */
public class RoomTest {
    private Room room;

    /**
     * Sets up the test environment before each test method runs.
     * Initializes a new room at coordinates (0,0) and hides the canvas 
     * to prevent visual rendering during the automated tests.
     */
    @BeforeEach
    public void setUp() {
        room = new Room(0, 0); 
        Canvas.getCanvas().setVisible(false); 
    }

    /**
     * Tests that no collisions are detected in any direction (West, East, North, South) 
     * when the room has no walls built.
     */
    @Test
    public void shouldNotCollideWhenNoWallIsBuilt() {
        assertFalse(room.checkPlayerCollision(0, 50, 5, 5));
        assertFalse(room.checkPlayerCollision(85, 50, 5, 5));
        assertFalse(room.checkPlayerCollision(40, 0, 10, 10));
        assertFalse(room.checkPlayerCollision(40, 85, 10, 10));
    }

    /**
     * Tests that a collision is only detected on a specific wall that has been built.
     * In this case, only the North wall is built, so collisions should only
     * occur on the North boundary, leaving the South, West, and East boundaries open.
     */
    @Test
    public void shouldCollideOnlyWithTheBuiltWall() {
        room.buildWall('n'); 

        assertTrue(room.checkPlayerCollision(40, 0, 10, 10));
        assertFalse(room.checkPlayerCollision(40, 85, 10, 10));
        assertFalse(room.checkPlayerCollision(0, 50, 5, 5));
        assertFalse(room.checkPlayerCollision(85, 50, 5, 5));
    }

    /**
     * Tests that collisions are successfully detected on all boundaries 
     * when all four walls (North, South, East, West) are built.
     */
    @Test
    public void shouldCollideOnEveryWallWhenAllAreBuilt() {
        room.buildWall('n');
        room.buildWall('s');
        room.buildWall('e');
        room.buildWall('w');

        assertTrue(room.checkPlayerCollision(40, 0, 10, 10));
        assertTrue(room.checkPlayerCollision(40, 85, 10, 10));
        assertTrue(room.checkPlayerCollision(85, 50, 5, 5));
        assertTrue(room.checkPlayerCollision(0, 50, 5, 5));
    }

    /**
     * Tests that even with all walls built, a player located safely in the middle 
     * of the room (away from all edges) does not trigger any collisions.
     */
    @Test
    public void shouldNotCollideInTheMiddleOfTheRoom() {
        room.buildWall('n');
        room.buildWall('s');
        room.buildWall('e');
        room.buildWall('w');

        assertFalse(room.checkPlayerCollision(40, 40, 10, 10));
    }
}