
/**
 * This class, create salas for the labyrinth
 *
 * @author Gualdron - Villagran
 * @version 1.0
 */

/**
     * create Room
     * @return return none
     */
public class Room {
    private int xPosition;
    private int yPosition;
    
    private Cube baseCube;
    private Cube leftCube;
    private Cube rightCube;
    private Cube aboveCube;
    private Cube belowCube;
    
    
    public Room(int x, int y) {
        // geminis
        xPosition = x; 
        yPosition = y; 
        //

        baseCube = new Cube();
        baseCube.changeXY(x,y);
        baseCube.buildWall();
        
        leftCube = new Cube();
        leftCube.changeXY(x,y);
        leftCube.changeSize(leftCube.height(),baseCube.width()/6);
        //leftCube.slowMoveHorizontal(baseCube.width()/6);
        leftCube.changeColor("black");
        
        
        rightCube = new Cube();
        rightCube.changeXY(x,y);
        rightCube.changeSize(rightCube.height(),baseCube.width()/6);
        rightCube.slowMoveHorizontal(5*baseCube.width()/6);
        rightCube.changeColor("black");
        
        
        aboveCube = new Cube();
        aboveCube.changeXY(x,y);
        aboveCube.changeSize(baseCube.height()/6,aboveCube.width());
        //aboveCube.slowMoveVertical(5*baseCube.width()/6);
        aboveCube.changeColor("black");
        
        
        belowCube = new Cube();
        belowCube.changeXY(x,y);
        belowCube.changeSize(baseCube.height()/6,belowCube.width());
        belowCube.slowMoveVertical(5*baseCube.width()/6);
        belowCube.changeColor("black");
    }
    
    public int getXPosition() {
        return xPosition;
    }
    
    public int getYPosition() {
        return yPosition;
    }
    public int height(){
        return baseCube.height();
    }
    
    public int width(){
        return baseCube.width();
    }
    
    /**
     * make visible walls
     * @return return none
     */
    
    public void buildWall(char visible){
        switch (visible) {
            case 'w':
                leftCube.buildWall();
                break;
            case 'n':
                aboveCube.buildWall();
                break;
            case 'e':
                rightCube.buildWall();
                break;
            case 's':
                belowCube.buildWall();
                break;
        }
    }
    
    
    /**
     * changes color in collision the walls
     * @return return none
     */
    
    public void collision(char collision){
        switch (collision) {
            case 'w':
                leftCube.changeColor("red");
                break;
            case 'n':
                aboveCube.changeColor("red");
                break;
            case 'e':
                rightCube.changeColor("red");
                break;
            case 's':
                belowCube.changeColor("red");
                break;
        }
    }

    
    
    //Hecho con Gemini IA
    /**
     * Check for an object colliding with the walls of this room
     * @return return none
     */
    public boolean checkPlayerCollision(int pX, int pY, int pWidth, int pHeight) {
        int w = width();
        int h = height();
        int grosorParedX = w / 6;
        int grosorParedY = h / 6;

        // Comprobar colisión con la pared Izquierda (w)
        if (pX < xPosition + grosorParedX && pX + pWidth > xPosition && 
            pY < yPosition + h && pY + pHeight > yPosition) {
            collision('w'); // Llama a tu método original para pintar de rojo
            return true;
        }
        
        // Comprobar colisión con la pared Derecha (e)
        if (pX < xPosition + w && pX + pWidth > xPosition + (5 * grosorParedX) && 
            pY < yPosition + h && pY + pHeight > yPosition) {
            collision('e');
            return true;
        }
        
        // Comprobar colisión con la pared Superior (n)
        if (pX < xPosition + w && pX + pWidth > xPosition && 
            pY < yPosition + grosorParedY && pY + pHeight > yPosition) {
            collision('n');
            return true;
        }
        
        // Comprobar colisión con la pared Inferior (s)
        if (pX < xPosition + w && pX + pWidth > xPosition && 
            pY < yPosition + h && pY + pHeight > yPosition + (5 * grosorParedY)) {
            collision('s');
            return true;
        }

        return false;
    }     //

}