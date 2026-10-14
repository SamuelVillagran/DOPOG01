package domain;
import java.awt.Color;
import java.util.Random;

public class Grass extends Bush implements Entity {

    public Grass(EcoSafari habitat, int row, int column) {
        super(habitat, row, column);
        habitat.set(this, row, column);
    }

    @Override
    public Color getColor() {
        return Color.GREEN;
    }

    @Override
    public EcoSafari getHabitat() {
        return habitat;
    }

    @Override
    public void tic() {
    }

    @Override
    public void tac() { 
    }
    
}