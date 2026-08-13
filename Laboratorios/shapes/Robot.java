import java.util.concurrent.Future;
import java.util.ArrayList;

/**
 * Write a description of class Robot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot {
    
    protected int actualLocation;
    protected int initialStart;
    protected boolean visible;
    protected ArrayList<Figure> figureRobot;
    
    
    public Robot(int start, String color) {
    
        this.figureRobot = new ArrayList<>();
        this.actualLocation = start;
        this.initialStart = start;
        
        // Creation and addition of shapes:
        this.figureRobot.add(new Circle());
        this.figureRobot.add(new Circle());
        this.figureRobot.add(new Rectangle());
        this.figureRobot.add(new Triangle());
        this.figureRobot.add(new Rectangle());
        this.figureRobot.add(new Rectangle());
    }
}