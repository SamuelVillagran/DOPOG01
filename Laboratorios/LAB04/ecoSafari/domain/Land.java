package domain;
import java.awt.Color;
import java.util.Random;
 
/**
* Write a description of class Land here.
*
* @author (your name)
* @version (a version number or a date)
*/
/**
* Represents a land cell in the ecosystem.
*/
public class Land implements Entity {
    private EcoSafari habitat;
    private int row, column;
    private boolean generateGrass;
    private Random rand;
 
    /**
     * Creates a new land cell and places it in the habitat.
     */
    public Land(EcoSafari habitat, int row, int column) {
        this.habitat = habitat;
        this.row = row;
        this.column = column;
        this.rand = new Random();
        habitat.set(this, row, column);
    }
 
    /**
     * Decides what to do. It has a 10% chance to generate grass.
     */
    @Override
    public void tic() {
        generateGrass = (rand.nextInt(100) < 10);
    }
 
    /**
     * Executes the action. It creates new grass if decided in tic().
     */
    @Override
    public void tac() {
        if (generateGrass) {
            new Grass(habitat, row, column);
        }
    }
 
    /**
     * Returns a brown color for the land.
     */
    @Override
    public Color getColor() {
        return new Color(139, 69, 19);
    }
 
    /**
     * Returns the current habitat.
     */
    @Override
    public EcoSafari getHabitat() { 
        return habitat; 
    }
}