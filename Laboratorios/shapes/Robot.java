import java.util.ArrayList;

/**
 * Write a description of class Robot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot {
    
    protected int xPosition;
    protected int yPosition;
    protected boolean visible;
    protected ArrayList<Figure> figureRobot;
    
    
    public Robot(int x, int y) {
        xPosition = x;
        yPosition = y;
        
        this.figureRobot = new ArrayList<>();        
        this.visible = true;
        
        makeRobotShapes();
        modifyRobotShapes(); /* Add instructions at methods 
            to make more simplified this method*/
    }
    
    private void makeRobotShapes() {
        // Creation and addition of shapes:
        this.figureRobot.add(new Circle());
        this.figureRobot.add(new Circle());
        this.figureRobot.add(new Rectangle());
        this.figureRobot.add(new Triangle());
        this.figureRobot.add(new Rectangle());
        this.figureRobot.add(new Rectangle());
    }
    
    private void modifyRobotShapes() {
        //Modifying head
        figureRobot.get(2).changeColor("blue");
        ((Rectangle)figureRobot.get(2)).changeSize(15, 15);
        figureRobot.get(2).setPosition(xPosition, yPosition);
        
        //Modifyng leftEye
        figureRobot.get(0).changeColor("white");
        ((Circle)figureRobot.get(0)).changeSize(3);
        figureRobot.get(0).setPosition(xPosition+4, yPosition+4);
        
        //Modifying rightEye
        figureRobot.get(1).changeColor("white");
        ((Circle)figureRobot.get(1)).changeSize(3);
        figureRobot.get(1).setPosition(xPosition+10, yPosition+4);
        
        //Modifyng body
        ((Triangle)figureRobot.get(3)).changeSize(10, 15);
        ((Triangle)figureRobot.get(3)).rotate(180);
        figureRobot.get(3).setPosition(xPosition+8, yPosition+25);
        
        //Modifying mouth
        figureRobot.get(4).changeColor("white");
        ((Rectangle)figureRobot.get(4)).changeSize(3, 10);
        figureRobot.get(4).setPosition(xPosition+3, yPosition+10);
        
        //Modifying antenna
        figureRobot.get(5).changeColor("red");
        ((Rectangle)figureRobot.get(5)).changeSize(7, 5);
        figureRobot.get(5).setPosition(xPosition+5, yPosition-7);        
    }
    
    /**
     * Hide the robot of canvas
     */
    public void makeInvisible() {
        for (Figure f : figureRobot) {
            f.makeInvisible();
        }
        this.visible = false;
    }
    
    /**
     * Make visible robot on canvas
     */
    public void makeVisible(){
        /* Se hace en orden para que aparezcan los ojos*/
        figureRobot.get(3).makeVisible(); //body
        figureRobot.get(2).makeVisible(); //head
        figureRobot.get(5).makeVisible(); //antenna
        figureRobot.get(4).makeVisible(); //mouth
        figureRobot.get(0).makeVisible(); //leftEye
        figureRobot.get(1).makeVisible(); //rightEye
        this.visible = true;
    }
    
    /**
     * Set the position of the Robot in Pixels
     */
    public void setPosition(int x, int y) {
        //2: head
        figureRobot.get(2).setXPosition(50 + y * 40);
        figureRobot.get(2).setYPosition(50 + x * 40);
        //4: mouth
        figureRobot.get(4).setXPosition(53 + y * 40);
        figureRobot.get(4).setYPosition(60 + x * 40);
        //5: antenna
        figureRobot.get(5).setXPosition(55 + y * 40);
        figureRobot.get(5).setYPosition(43 + x * 40);
        //3: body
        figureRobot.get(3).setXPosition(57 + y * 40);
        figureRobot.get(3).setYPosition(76 + x * 40);
        //0: leftEye
        figureRobot.get(0).setXPosition(55 + y * 40);
        figureRobot.get(0).setYPosition(55 + x * 40);
        //1: rightEye
        figureRobot.get(1).setXPosition(60 + y * 40);
        figureRobot.get(1).setYPosition(55 + x * 40);
        
        //Make all parts visible
        if (this.visible){
            makeVisible();
        }
    }
    
    
}