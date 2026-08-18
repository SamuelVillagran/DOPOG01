

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RoomTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RoomTest
{
    @Test
    public void create() {
        Room room = new Room(0,0);
        assertEquals(100, room.height());
        assertEquals(100, room.width());
    }
    @Test
    public void position() {
        Room room = new Room(0,0);
        assertEquals(0,room.getYPosition());
        assertEquals(0,room.getXPosition());
    }
}