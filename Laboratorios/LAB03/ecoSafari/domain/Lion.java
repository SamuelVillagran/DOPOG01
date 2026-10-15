package domain;


/**
 * Write a description of class Lion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lion extends Organism implements Entity {
    
    private final EcoSafari habitat;
    
    public Lion(EcoSafari habitat, int row, int column) {
        this.habitat = habitat;
        this.energy = 100;
        habitat.set(this, row, column);
    }
    
    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public EcoSafari getHabitat() {
        return habitat;
    }
}