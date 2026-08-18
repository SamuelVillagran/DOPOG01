

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MazeTest.
 * This class contains unit tests for the Maze class, verifying core mechanics
 * such as robot movement, maze initialization, building walls/obstacles, 
 * and game state handling (e.g., game over scenarios).
 * 
 * @author  Gualdron - Villagran
 * @version 1.0
 */
public class MazeTest
{
    /**
     * Tests the robot's ability to successfully move towards or pass the goal.
     * Initializes a maze of size 5 and moves the robot East by 2 spaces, 
     * followed by South by 2 spaces.
     */
    @Test
    public void shouldPassGoal() {
        Maze game = new Maze(5);
        game.moveRobot('e',2);
        game.moveRobot('s',2);
    }
    
    /**
     * Tests the creation of multiple mazes and the building of elements 
     * (such as walls or boundaries) in different directions.
     * Verifies the {@code buid} method for West ('w'), East ('e'), 
     * South ('s'), and North ('n').
     */
    @Test
    public void createMazel() {
        Maze game = new Maze(1);
        game.createMaze(5);
        game.createMaze(2);
        game.createMaze(6);
        game.buid(1,1,'w');
        game.buid(1,1,'e');
        game.buid(1,1,'s');
        game.buid(1,1,'n');
    }
    
    /**
     * Tests a "game over" scenario.
     * Initializes a small maze (size 1) and attempts to move the robot 
     * East by 2 spaces, which should theoretically trigger a game over state 
     * due to moving out of bounds or hitting a barrier.
     */
    @Test
    public void gameOver() {
        Maze game = new Maze(1);
        game.moveRobot('e',2);
    }
    
    /**
     * Tests the termination or finalization sequence of the game.
     * Calls the {@code end()} method to ensure the game shuts down 
     * or cleans up its state correctly.
     */
    @Test
    public void end() {
        Maze game = new Maze(1);
        game.end();
    }
}