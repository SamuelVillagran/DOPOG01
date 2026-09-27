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
    private boolean old;
    private int height;
    private int broad;
    private int moment = 0;
    
    /**builder
     * Initializes the bush with its color, energy, and position.
     */
    public Bush() {
        
    }
    
    /**
     * It states where the plant will be propagated.
     */
    public void reproduction(){
        
    }
    
    /**
     * decide what he or she is going to do
     */
    @Override
    public void tic() {
        
    }
    
    /**
     * does what tic decided
     */
    @Override
    public void tac() {
        
    }
    
    /**
     * @return returns the color based on age
     */
    @Override
    public Color getColor() {
        return null;
    }
    
    @Override
    public EcoSafari getHabitat() {
        return null;
    }
}