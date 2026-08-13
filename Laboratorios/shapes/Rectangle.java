import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */
public class Rectangle extends StraightSided {

    public static int EDGES = 4;
    
    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        super(70, 15, "magenta");
        height = 30;
        width = 40;
    }
    
    /*
     * Draw rectangle
     */
    @Override
    protected void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }
    
}

