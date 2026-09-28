package domain;
import java.awt.Color;


/**
 * Write a description of class African here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class African extends Elephant
{
    private int moment = 0;
    public African(EcoSafari safari, int row, int column) {
        super(safari, row, column); 
    }
    
    /**
     * Determines the color of the elephant based on its current energy level.
     *
     * @return color, LIGHT_GRAY < 80 OR DARK_GRAY >= 80
     */
    
    @Override
    public void tic(){
        nextAction = "NONE";
        height = habitat.find(this)[0];
        broad = habitat.find(this)[1];
        if ((! hasActed) && habitat.get(height -1,broad) instanceof Bush) {
            nextAction = "EAT";
            height--;
            
        } 
        else if ((! hasActed) && habitat.get(height+1,broad) instanceof Bush) {
            nextAction = "EAT";
            height++;
            
        } 
        else if ((! hasActed) && habitat.get(height,broad+1) instanceof Bush) {
            nextAction = "EAT";
            broad++;
            
        } 
        else if ((! hasActed) && habitat.get(height,broad-1) instanceof Bush) {
            nextAction = "EAT";
            broad--;
            
        } 
        else if ((! hasActed) && (move(1, 1))) {
            nextAction = "MOVE";
        }
        hasActed=true;
    }
    
    @Override
    public void tac(){
        moment++;
        if (moment >= 2) {
            moment = 0;
            if (nextAction.equals("EAT")) {
                changeEnergy(20);
                habitat.set(null,height,broad);
            }
            if (nextAction.equals("MOVE")) {
                changeEnergy(-10);
                if (getEnergy()==0){
                    disappear();
                }
            }
        }   
        hasActed=false;
    }
}