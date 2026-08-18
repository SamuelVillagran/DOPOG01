import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.Point;

/**
 * This is the robot of maze.
 * @author Gualdron - Villagran
 * @version 1.0
 */
public class Robot {
    
    public int xPosition;
    public int yPosition;
    protected boolean isVisible;
    protected ArrayList<Figure> robotFigure;
    
    protected int live;
    protected boolean ok;  
    protected boolean canMove;
    protected char direction; 
    protected int nextNumStep;

    public static final int HEAD = 0;
    public static final int BODY = 1;
    public static final int MOUTH = 2;
    public static final int ANTENNA = 3;
    public static final int LEFT_EYE = 4;
    public static final int RIGHT_EYE = 5;
    public static final int NEEDLE = 6;
    public static final int NUM_FIGURES = 6;
    
//ciclo 1  
    /**
     * Constructor to able create a robot
     * with specific coordenades of canva.
     * @param x x is the x robot's position on the canvas.
     * @param y y is the y robot's position on the canvas.
     */
    public Robot(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
        
        this.robotFigure = new ArrayList<>();        
        this.isVisible = false;
        this.direction = 'e';
        this.ok = true;
        this.canMove =  true;
        this.live = 10;
        
        makeRobotShapes();
        modifyRobotShapes(); /* Add instructions at methods 
            to make more simplified this method of class*/
    }

     
    /**
     * Move the position of the Robot.
     * @param x x is the new xPosition that robot has.
     * @param y y is the new yPosition that robot has.
     */
    public void setPosition(int x,int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    
    /** Give the x and y coordenades of robot.
     * @return return robot''s coordinates (x,y)
     */
    public int[] coordinates() {
        int[] coordinatesXY;
        coordinatesXY = new int[2];
        coordinatesXY[0] = xPosition;
        coordinatesXY[1] = yPosition;
        return coordinatesXY;
    }
    
    /**
     * Give the direction of robot.
     * @return return attribute direction of robot.
     */
    public char direction() {
          return this.direction; 
    }
    
//ciclo 2
    
    /**
     * Move the figure according to the direction
     * @param step step are the times that robot moves.
     */
    public void move(int step) {
        int dx = 0, dy = 0;
        nextNumStep = step;

        switch (this.direction) {
            case 'w':
                // izquierda
                if (this.canMove) {
                    dx = -1;
                }
                break;
            case 's':
                // abajo
                if (this.canMove) {
                    dy = 1;
                }
                break;
            case 'e':
                //derecha 
                if (this.canMove) {
                    dx = 1;
                }
                break;
            case 'n':
                //arriba 
                if (this.canMove) {
                    dy = -1;
                }
                break;
        }
        moveSlow(dx, dy, step);
    }

    
    /**
     * change direction
     * @param direction direction can be 'n', 'w', 'e' o 's'.
     */
    public void turn(char direction){
        ((Triangle)robotFigure.get(NEEDLE)).erase();
        if (direction == 'n') {
            ((Triangle)robotFigure.get(NEEDLE)).rotate(0);
        } else if (direction == 'w') {
            ((Triangle)robotFigure.get(NEEDLE)).rotate(-90);
        } else if (direction == 'e') {
            ((Triangle)robotFigure.get(NEEDLE)).rotate(90);
        } else if (direction == 's') {
            ((Triangle)robotFigure.get(NEEDLE)).rotate(180);
        }
        
        ((Triangle)robotFigure.get(NEEDLE)).draw();
        this.direction = direction;
    }
    
    /**
     * Indicats if the robot can move (if this have live).
     */
    public boolean isOK(){
        //pendiente de laberinto
        this.ok = this.live > 0;
        if (this.ok) {
            this.canMove = true;
        } else {
            canMove = false;
        }
        return  this.ok;
    }
 
 
//ciclo 3
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
    
    
    public void reset() {
        for (Figure f : robotFigure) {
            f.makeInvisible();    
            }
        this.ok = true;
        this.live=10;
        
    }
//---------------------------------------------------------------------------------------------
    
    public int getXPosition() {
        return xPosition;
    }

    
    public int getYPosition() {
        return this.yPosition;
    }
    
    /**Robot take damage when collision with a wall
     */
    public void takeDamage() {
        this.live--;
    }
    
    /**
     * Check the collision of robot with the matrix maze
     * @param matrixMaze matrixMaze are the rooms of Maze
     */
    public void checkCollision(Room[][] matrixMaze) {
        Room[] roomAroundRobot = getNearRooms(matrixMaze);
        boolean isCollision = false;
        
        for (Room room : roomAroundRobot) {
            isCollision = room.checkPlayerCollision(xPosition, yPosition, 
                width(), height());
            if (isCollision) {
                takeDamage();
                backLastPosition();
                this.canMove = false; // Detiene el robot
                if (this.live <= 0) {
                    die();
                }
                break;
            }
        }
    }
    
    /**
     * Check the collision of robot with the room entry and finally
     * @param matrixMaze matrixMaze are the rooms of Maze
     */
    public void checkCollision(Room room) {
        boolean isCollision = room.checkPlayerCollision(xPosition, yPosition, width(), height());
        if (isCollision) {
            takeDamage();
            backLastPosition();
            this.canMove = false; // Detiene el robot
            if (this.live <= 0) {
                die();
            }
        }
    }

    public int height() {
        int height = 0;
        for (int i = 0; i < 4 ; i++) {
            height += ((StraightSided)robotFigure.get(i)).getHeight();
        }
        return height;
    }
    
    public int width() {
        return  ((StraightSided)robotFigure.get(HEAD)).getWidth();
    }
    
    public boolean canMove() {
        return canMove;
    }
    
    private void moveRobotFigure(int xPos, int yPos) {
        for (Figure f : robotFigure) {
            f.setPosition(f.getXPosition() + xPos, f.getYPosition() + yPos);
            if (isVisible) {
                f.draw();    
            }
        }
    }
    
    private void die() {
        for (int i = 0; i < robotFigure.size(); i++) {
            if (i == LEFT_EYE || i == RIGHT_EYE) {
                 robotFigure.get(i).changeColor("black");
            } else {
                 robotFigure.get(i).changeColor("gray");
            }
        }   
        this.ok = false;
    }
    
    /**
     * Back the robot at the last position when collision with a wall
     */
    private void backLastPosition() {
        int dx = 0, dy = 0;
        
        switch (this.direction) {
            case ('s'): dy = -10; break;
            case ('n'): dy = 10; break;
            case ('e'): dx = -10; break;
            case ('w'): dx = 10; break;
        }   
        moveRobotFigure(dx, dy);
        setPosition(xPosition + dx, yPosition + dy);
        
    }
    
    private Room[] getNearRooms(Room[][] matrixMaze) {
        int xPosRoom, yPosRoom,
            dxRobotRoom = 0, dyRobotRoom = 0;
        int[] distances = { Integer.MAX_VALUE, Integer.MAX_VALUE,
                         Integer.MAX_VALUE, Integer.MAX_VALUE };
        double distanceRobotRoom;
        Room[] roomsAround = new Room[4]; 
        for (Room[] fileRoom : matrixMaze) {
            if (fileRoom == null) continue; //
            for (Room room : fileRoom) {
                if (room == null) continue; // Omite celdas vacias de la matriz
                xPosRoom = room.getXPosition(); yPosRoom = room.getYPosition();
                dxRobotRoom = Math.abs(xPosition - xPosRoom); dyRobotRoom = Math.abs(yPosition - yPosRoom);
                distanceRobotRoom = Math.sqrt(Math.pow(dxRobotRoom, 2) + Math.pow(dyRobotRoom, 2));// Formula de distancia entre dos puntos
                for (int i = 0; i < 4; i++) { // Esta parte fue hecho con Claude Sonnet 5 IA
                    if (distanceRobotRoom < distances[i]) {
                        // corre los siguientes un puesto hacia atrás
                        for (int j = 3; j > i; j--) {
                            distances[j] = distances[j - 1];
                            roomsAround[j] = roomsAround[j - 1];
                        }
                        distances[i] = (int) distanceRobotRoom;
                        roomsAround[i] = room;
                        break; // ya insertado, siguiente room
                    } // Hasta aquí
                }
            }
        }
        
        //Esta parte fue ayudada a revisar con Gemini IA 3.
        // Cuenta cuántas habitaciones reales se guardaron para omitir los nulos
        int validRoomsCount = 0;
        for (int i = 0; i < 4; i++) {
            if (roomsAround[i] != null) {
                validRoomsCount++;
            }
        }
        
        // Devuelve un arreglo del tamaño exacto sin nulos
        Room[] finalRooms = new Room[validRoomsCount];
        for (int i = 0; i < validRoomsCount; i++) {
            finalRooms[i] = roomsAround[i];
        }
        
        return finalRooms;
    }
    
    /* Make move slowy the robot
     * @param dx dx is the delta at x position of robot that this moves it.
     * @param dy dy is the delta at y position of robot that this moves it.
     * @param step are the times that rob,t moves.
     */
    private void moveSlow(int dx, int dy, int step) {
        int figurePosX=Integer.MAX_VALUE, figurePosY = Integer.MAX_VALUE;
        for (int i = 0; i < step; i++) {
             if (!this.canMove) break;
             for (Figure figure : robotFigure) {
                  figurePosX = figure.getXPosition();
                  figurePosY = figure.getYPosition();
                  figure.setPosition(figurePosX+dx, figurePosY+dy);
                  if (i%5==0) {
                      figure.draw();
                  }
             }
        }
        xPosition = figurePosX; yPosition = figurePosY;
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
        this.robotFigure.add(new Triangle());// 6: NEEDLE
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
        ((Triangle)robotFigure.get(BODY)).changeColor("blue");
        ((Triangle)robotFigure.get(BODY)).changeSize(10, 15);
        ((Triangle)robotFigure.get(BODY)).rotate(180);
        robotFigure.get(BODY).setPosition(xPosition+8, yPosition+25);
        
        //Modifying mouth
        robotFigure.get(MOUTH).changeColor("white");
        ((Rectangle)robotFigure.get(MOUTH)).changeSize(3, 10);
        robotFigure.get(MOUTH).setPosition(xPosition+3, yPosition+10);
        
        //Modifying antenna
        robotFigure.get(ANTENNA).changeColor("yellow");
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
        
        //Modifying pointer
        robotFigure.get(NEEDLE).changeColor("red");
        ((Triangle)robotFigure.get(NEEDLE)).changeSize(11, 8);
        ((Triangle)robotFigure.get(NEEDLE)).rotate(90);
        robotFigure.get(NEEDLE).setPosition(xPosition+13, yPosition-20);
    }
}