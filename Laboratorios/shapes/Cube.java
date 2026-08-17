import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Cube{

    public Rectangle cube;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Cube(){
        cube = new Rectangle(70, 15, 100, 100, "green");
        cube.makeInvisible();
    }
    
    public int height(){
        return cube.getHeight();
    }
    
    public int width(){
        return cube.getWidth();
    }
    
    public void changeXY(int x, int y){
        cube.setPosition(x, y);
    }
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void buildWall(){
        cube.makeVisible();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void downWall(){
        cube.makeInvisible();
    }
    
    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        cube.moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        cube.moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        cube.moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        cube.moveHorizontal(distance);
    }

    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        cube.moveVertical(distance);
    }

    /**
     * Slowly move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        cube.slowMoveHorizontal(distance);
    }

    /**
     * Slowly move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        cube.slowMoveVertical(distance);
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        cube.changeSize(newHeight, newWidth);
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        cube.changeColor(newColor);
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        cube.draw();
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        cube.erase();
    }
}

