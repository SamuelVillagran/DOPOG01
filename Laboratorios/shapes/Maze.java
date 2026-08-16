
/**
 * Write a description of class maze here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Maze {
    private Room[][] matrix;
    private Room room1;
    public Maze(int size) {
        room1 = new Room(0,0);
        Canvas.getCanvas().setDimension((room1.height() * size)+2, (room1.height() * size)+2);
        matrix = new Room[size][size];
        matrix[0][0] = room1;
        matrix[0][0].buildWall('n');
        for (int i = 1; i < size;i++){
            matrix[i][0] = new Room(room1.width()*i,0);
            matrix[i][0].buildWall('n');
        }
        for (int i = 1; i < size;i++){
            for (int u = 0; u < size;u++){
            matrix[u][i] = new Room(room1.width()*u,room1.height()*i);
            }
        }
        for (int i = 0; i < size;i++){
              matrix[0][i].buildWall('w');
        }
        for (int i = 0; i < size;i++){
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

