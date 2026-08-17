
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
    
    public Maze(int size) {
        
        roomEntry = new Room(0,40);
        roomExit = new Room(roomEntry.height()* (size+1),roomEntry.height() * (size));
        Canvas.getCanvas().setDimension((roomEntry.height() * (size+2)), (roomEntry.height() * (size+2)));
        matrix = new Room[size][size];
        
        karel = new Robot(20,55);
        karel.makeVisible();
        
        roomEntry.buildWall('n');
        roomEntry.buildWall('w');
        roomEntry.buildWall('s');
        
        roomExit.buildWall('n');
        roomExit.buildWall('e');
        roomExit.buildWall('s');
        
        for (int i = 0; i < size;i++){
            matrix[i][0] = new Room((roomEntry.width()*i)+40,40);
            matrix[i][0].buildWall('n');
        }
        for (int i = 1; i < size;i++){
            for (int u = 0; u < size;u++){
            matrix[u][i] = new Room((roomEntry.width()*u)+40,(roomEntry.height()*i)+40);
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
    
    public void buid(int x, int y, char direction) {
    matrix[x][y].buildWall(direction);
    }   
}

