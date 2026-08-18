import javax.swing.JOptionPane;
import java.awt.Color;
/**
 * Write a description of class maze here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Maze {
    
    private Room[][] matrix;
    private Room roomEntry;
    private Room roomExit;

    private Robot karel;
    private Canvas maze;
    public static final int HEIGHT_ENTRY_ROOM = 100;
    
    public Maze(int size) {
        createMaze(size);
        karel = new Robot(roomEntry.height()/2,roomEntry.height()+(roomEntry.height()/2));
        karel.makeVisible();

    }
    
    public void buid(int x, int y, char direction) {
    matrix[x][y].buildWall(direction);
    }   
    
    public void createMaze(int size){
    
        roomEntry = new Room(0,HEIGHT_ENTRY_ROOM);
        roomExit = new Room(roomEntry.height()* (size+1),roomEntry.height() * (size));
        maze = Canvas.getCanvas();
        maze.setDimension((roomEntry.height() * (size+2)), (roomEntry.height() * (size+2)));
        
        matrix = new Room[size][size];

        
        roomEntry.buildWall('n');
        roomEntry.buildWall('w');
        roomEntry.buildWall('s');
        
        roomExit.buildWall('n');
        roomExit.buildWall('e');
        roomExit.buildWall('s');
        
        for (int i = 0; i < size;i++){
            matrix[i][0] = new Room((roomEntry.width()*i)+roomEntry.height(),roomEntry.height());
            matrix[i][0].buildWall('n');
        }
        for (int i = 1; i < size;i++){
            for (int u = 0; u < size;u++){
            matrix[u][i] = new Room((roomEntry.width()*u)+roomEntry.height(),(roomEntry.height()*i)+roomEntry.height());
            }
        }
        for (int i = 1; i < size;i++){
              matrix[0][i].buildWall('w');
        }
        for (int i = 0; i < (size-1);i++){
              matrix[size -1 ][i].buildWall('e');
        }
        for (int i = 0; i < size;i++){
              matrix[i][size -1].buildWall('s');
        }
    }
    public boolean gameOver(int xRobot,int yRobot) {
        if (xRobot > roomExit.getXPosition() && yRobot > roomExit.getYPosition()) {
                JOptionPane.showMessageDialog(
                        null,
                        "WIN",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE
                    ); //con ayuda de ia
                return true;  
        }
        return false;
    }
    
    public void end() {
        int size = Integer.parseInt( JOptionPane.showInputDialog("tamaño del laberinto: ") );
        maze.close();
        matrix = new Room[size][size];
        maze = null;
        createMaze(size);
        karel = null;
        karel = new Robot(roomEntry.height()/2,roomEntry.height()+(roomEntry.height()/2));
        karel.makeVisible();
    }
    
    public void moveRobot(char direction, int step) {
        karel.turn(direction);
        Room[] roomsAroundKarel = new Room[4];
        int xPosKarel = karel.getXPosition(), yPosKarel = karel.getYPosition();
        //karel.checkCollision();
        karel.move(step);
        if (this.gameOver(karel.getXPosition(), karel.getYPosition())) {
            end();
        }
    }

}

