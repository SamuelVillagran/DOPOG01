import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RoomTest.
 *
 * @author  Gualdron - Villagran
 * @version (a version number or a date)
 */
public class RoomTest {
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room(0, 0); // room 100x100 en (0,0)
        Canvas.getCanvas().setVisible(false); // oculta la ventana, no la elimina
    }

    @Test
    public void shouldNotCollideWhenNoWallIsBuilt() {
        assertFalse(room.checkPlayerCollision(0, 50, 5, 5));    // zona oeste
        assertFalse(room.checkPlayerCollision(85, 50, 5, 5));   // zona este
        assertFalse(room.checkPlayerCollision(40, 0, 10, 10));  // zona norte
        assertFalse(room.checkPlayerCollision(40, 85, 10, 10)); // zona sur
    }

    @Test
    public void shouldCollideOnlyWithTheBuiltWall() {
        room.buildWall('n'); // solo se construye la pared norte

        assertTrue(room.checkPlayerCollision(40, 0, 10, 10));    // norte sí existe
        assertFalse(room.checkPlayerCollision(40, 85, 10, 10));  // sur no existe
        assertFalse(room.checkPlayerCollision(0, 50, 5, 5));     // oeste no existe
        assertFalse(room.checkPlayerCollision(85, 50, 5, 5));    // este no existe
    }

    @Test
    public void shouldCollideOnEveryWallWhenAllAreBuilt() {
        room.buildWall('n');
        room.buildWall('s');
        room.buildWall('e');
        room.buildWall('w');

        assertTrue(room.checkPlayerCollision(40, 0, 10, 10));   // norte
        assertTrue(room.checkPlayerCollision(40, 85, 10, 10));  // sur
        assertTrue(room.checkPlayerCollision(85, 50, 5, 5));    // este
        assertTrue(room.checkPlayerCollision(0, 50, 5, 5));     // oeste
    }

    @Test
    public void shouldNotCollideInTheMiddleOfTheRoom() {
        room.buildWall('n');
        room.buildWall('s');
        room.buildWall('e');
        room.buildWall('w');

        assertFalse(room.checkPlayerCollision(40, 40, 10, 10)); // centro, lejos de paredes
    }
}