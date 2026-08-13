
/**
 * Write a description of class Polygon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class StraightSided extends Figure {
    
    protected int height;
    protected int width;
    
    protected StraightSided(int x, int y, String color) {
        super(x, y, color);
    }
    
    /**
     * Change size of a specified polygon
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    
}