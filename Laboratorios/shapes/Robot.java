import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

/**
 * This is class Robot. This is the robot of maze.
 * 
 * @author Gualdron - Villagran
 * @version 1.0
 */
public class Robot {
    
    protected int xPosition;
    protected int yPosition;
    protected boolean isVisible;
    protected ArrayList<Figure> robotFigure;
    
    public static final int HEAD = 0;
    public static final int BODY = 1;
    public static final int MOUTH = 2;
    public static final int ANTENNA = 3;
    public static final int LEFT_EYE = 4;
    public static final int RIGHT_EYE = 5;
    public static final int NUM_FIGURES = 6;

    
    public Robot(int x, int y) {
        xPosition = x;
        yPosition = y;
        
        this.robotFigure = new ArrayList<>();        
        this.isVisible = true;
        
        makeRobotShapes();
        modifyRobotShapes(); /* Add instructions at methods 
            to make more simplified this method of class*/
    }
    
    /**
     * Hide the robot of canvas
     */
    public void makeInvisible() {
        for (Figure f : robotFigure) {
            f.makeInvisible();
        }
        this.isVisible = false;
    }
    
    /**
     * Make visible robot on canvas
     */
    public void makeVisible(){
        for (Figure f : robotFigure) {
            f.makeVisible();
        }
        this.isVisible = true;
    }
     
    /**
     * Set the position of the Robot.
     * @param x x is the new xPosition that robot has.
     * @param y y is the new yPosition that robot has.
     */
    public void setPosition(int x, int y) {
        xPosition = x;
        yPosition = y;
        ArrayList<Point> movesPlus = new ArrayList<>(
            List.of(new Point(0,0), new Point(8, 25), new Point(3, 10),
            new Point(5, -7), new Point(4, 4), new Point(10, 4)));
        int plusXPos, plusYPos;
        Figure currentFigure;
        for (int i = 0; i < NUM_FIGURES; i++) {
            currentFigure = robotFigure.get(i);
            plusXPos = xPosition + movesPlus.get(i).x;
            plusYPos = yPosition + movesPlus.get(i).y;
            currentFigure.setPosition(xPosition+plusXPos, xPosition+plusYPos);
        }

        //Make all parts visible
        if (this.isVisible){
            makeVisible();
        }
    }
    
    /* 
     * Creates the shapes that compose the robot.
     */
    private void makeRobotShapes() {
        // Creation and addition of shapes:
        this.robotFigure.add(new Rectangle());// 0: HEAD
        this.robotFigure.add(new Triangle()); // 1: BODY
        this.robotFigure.add(new Rectangle());// 2: MOUTH
        this.robotFigure.add(new Rectangle());// 3: ANTENNA
        this.robotFigure.add(new Circle());   // 4: LEFT_EYE
        this.robotFigure.add(new Circle());   // 5: RIGHT_EYE
    }
    
    /*
     * Modify each robot's shape to generate the robot figure. 
     */
    private void modifyRobotShapes() {
        //Modifying head
        robotFigure.get(HEAD).changeColor("blue");
        ((Rectangle)robotFigure.get(HEAD)).changeSize(15, 15);
        robotFigure.get(HEAD).setPosition(xPosition, yPosition);
        
        //Modifyng body
        ((Triangle)robotFigure.get(BODY)).changeSize(10, 15);
        ((Triangle)robotFigure.get(BODY)).rotate(180);
        robotFigure.get(BODY).setPosition(xPosition+8, yPosition+25);
        
        //Modifying mouth
        robotFigure.get(MOUTH).changeColor("white");
        ((Rectangle)robotFigure.get(MOUTH)).changeSize(3, 10);
        robotFigure.get(MOUTH).setPosition(xPosition+3, yPosition+10);
        
        //Modifying antenna
        robotFigure.get(ANTENNA).changeColor("red");
        ((Rectangle)robotFigure.get(ANTENNA)).changeSize(7, 5);
        robotFigure.get(ANTENNA).setPosition(xPosition+5, yPosition-7);
        
        //Modifyng leftEye
        robotFigure.get(LEFT_EYE).changeColor("white");
        ((Circle)robotFigure.get(LEFT_EYE)).changeSize(3);
        robotFigure.get(LEFT_EYE).setPosition(xPosition+4, yPosition+4);
        
        //Modifying rightEye
        robotFigure.get(RIGHT_EYE).changeColor("white");
        ((Circle)robotFigure.get(RIGHT_EYE)).changeSize(3);
        robotFigure.get(RIGHT_EYE).setPosition(xPosition+10, yPosition+4);
    }
}