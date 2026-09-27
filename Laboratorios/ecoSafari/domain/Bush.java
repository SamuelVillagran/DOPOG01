package domain;
import java.awt.Color;


/**
 * Write a description of class bush here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bush extends Organism implements Entity
{
    private final EcoSafari habitat;
    private boolean hasActed;
    private boolean old;
    private int moment = 0;
    
    /**builder
     * Initializes the bush with its color, energy, and position.
     */
    public Bush(EcoSafari habitat,int row, int column) {
        this.habitat=habitat;
        habitat.set((Entity)this, row, column);  
        hasActed=false;
    }
    
    /**
     * It states where the plant will be propagated.
     */
    public void reproduction(){
        int height = habitat.find(this)[0];
        int broad = habitat.find(this)[1];
        if (habitat.get(height -1,broad) == null) {
             new Bush(habitat,height -1,broad);
        } 
        else if (habitat.get(height+1,broad) == null) {
             new Bush(habitat,height+1,broad);
        } 
        else if (habitat.get(height,broad+1) == null) {
             new Bush(habitat,height,broad+1);
        } 
        else if (habitat.get(height,broad-1) == null) {
             new Bush(habitat,height,broad-1);
        } 
    }
    
    /**
     * decide what he or she is going to do
     */
    @Override
    public void tic() {
        moment++;
        if (moment == 4) {
            old = true;
        }
    }
    
    /**
     * does what tic decided
     */
    @Override
    public void tac() {
        if (old == false) {
            if (moment == 2) {
                reproduction();
            }
        }
    }
    
    /**
     * @return returns the color based on age
     */
    @Override
    public Color getColor() {
        return(moment>=4? Color.YELLOW: (Color.GREEN));
    }
    
    @Override
    public EcoSafari getHabitat() {
        return null;
    }
}