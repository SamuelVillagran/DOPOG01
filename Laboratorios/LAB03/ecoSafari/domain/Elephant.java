package domain;
import java.awt.Color;


//Include the documentation
public class Elephant extends Organism implements Entity{
    protected final EcoSafari habitat;
    protected boolean hasActed;
    protected String nextAction = "NONE";
    protected int broad;
    protected int height;
    public Elephant(EcoSafari habitat,int row, int column){
        this.habitat=habitat;
        habitat.set((Entity)this, row, column);  
        hasActed=false;
    }

    public EcoSafari getHabitat(){
        return habitat;
    }
    
    /**
     * Determines the color of the elephant based on its current energy level.
     *
     * @return color, LIGHT_GRAY < 80 OR DARK_GRAY >= 80
     */
    public final Color getColor(){
        return(getEnergy()>=80? Color.DARK_GRAY: Color.LIGHT_GRAY);
    }

    public final int shape(){
        return Entity.ROUND;
    }
    
    
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
    
    public void tac(){
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
        hasActed=false;
    }    
}
