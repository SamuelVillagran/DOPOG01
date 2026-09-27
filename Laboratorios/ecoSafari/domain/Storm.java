package domain;
import java.awt.Color;


/**
 * Write a description of class Storm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Storm extends NonLiving implements Entity {
    private final EcoSafari habitat;
    
    public Storm(EcoSafari habitat,int row, int column){
        this.habitat=habitat;
        habitat.set((Entity)this, row, column);  
    }
    
    public EcoSafari getHabitat() {
        return habitat;
    }
    
    public Color getColor() {
        return Color.BLACK;
    }
    
    public void tic() {
        int SIZE = habitat.getSize();
        int [] positionStorm = habitat.find(this);
        if (positionStorm == null) {
            return; // La tormenta ya no está en el tablero o fue destruida
        }
        int posRow = positionStorm[0], posCol = positionStorm[1];
        int invariant = posRow + posCol;
        habitat.set(null, posRow, posCol); //borra la posicion anterior

        if (posRow == 0) {
            habitat.set(this, invariant, 0); //reaparece abajo, en la misma diagonal
        } else {
            habitat.set(this, posRow-1, posCol+1); //avanza en diagonal arriba-derecha
        }
    }
    
    public void tac() {
        tic();
    }
    
    public final int shape(){
        return Entity.ROUND;
    }
    
    @Override
    public void collideWith(Entity other) {
        other.disappear();
    }
}