package domain;

import java.awt.Color;
import java.util.Random;

/**
 * This is a grizzly bear hungry to eat Squirrels
 * and attack Trees
 * 
 * @author Villagran
 * @version 1.0
 */
public class Bear extends Organism implements Entity {
    private EcoSafari habitat;
    private int tictac;
    private int season; // Hunt 0, hibernation 1
    private int years;
    public static final int DAMAGE = 3;
    private String nextAction = "NONE";

    /**
     * Constructor for objects of class Bear
     */
    public Bear(EcoSafari habitat,int row, int column) {
        this.habitat=habitat;
        habitat.set((Entity)this, row, column);  
        this.season=0;
        this.tictac=0;
    }
    
    public EcoSafari getHabitat(){
        return habitat;
    }
    
    /**
     * Makes the logic of ticTac when 
     * tictac's button is pressed
     */
    public void tic() {
        tictac++;
        nextAction = "NONE";
        
        // Calculamos la estación aquí mismo, antes de tomar decisiones
        season = tictac % 4;
        
        if (tictac % 4 == 0) {
            getOld(); 
            boolean OK = step();
            if (!OK) {
                nextAction = "DIE";
                return; // Si muere, cortamos el turno aquí
            }
        } 
        
        // Si no está hibernando (estado 1), planeamos el movimiento
        if (season != 1) {
            nextAction = "MOVE";
        }
    }
    
    public void tac() {
        if ("DIE".equals(nextAction)) {
            die();
        } 
        else if ("MOVE".equals(nextAction)) {
            move(); // Aquí es donde realmente se mueve y ataca
        }
    }
    
    /**The LivingThing makes one step
     * 
     */
    final boolean step(){
        boolean ok = false;
        if (getEnergy() >= 1) { 
            changeEnergy(-1);
            ok = true;
        }
        return ok;
    }    
    
    /**
     * Get the bear's color
     * When bear is hunting its color is more vivid
     * When bear is hibernating its color is darker
     * This depends of season 
     * @return color Bear's color
     */
    public Color getColor() {
        season = tictac % 4; 
        
        if (season == 1) return Color.BLUE; // Marrón
        if (season == 2) return Color.RED;             // Rojo
        if (season == 3) return Color.YELLOW;          // Amarillo
        return Color.ORANGE;                           // Naranja (season == 0)
    }
    
    /**
     * Makes bear older
     */
    public void getOld() {
        years += 2;
    }

    
    /**
     * Makes move bear on a directon of 4x4 cells 
     */
    public void move() {
        Random rd = new Random();
        int delta;
        
        do {
            delta = rd.nextInt(4) - 2; 
        } while (delta == 0);
        
        int[] position = habitat.find(this);
        
        if (position == null) {
            return; 
        }
        
        int height = position[0];
        int broad = position[1];   
        int dr = height + delta, dc = broad + delta;
        
        boolean areThereEntity = habitat.isInside(dr, dc);
        
        if (areThereEntity && dr != height && dc != broad && season != 1) {
            habitat.set(null, height, broad);
            habitat.set(this, dr, dc);
            attack();
        }
    }
    
    /**Die, makes bear null in the board
     */
    public void die(){
        int[] position = habitat.find(this);
        if (position == null) {
            return; 
        }
        
        int height = position[0];
        int broad = position[1];  
        habitat.set(null, height, broad);
    }
    
    /**Returns the shape
    @return integer that it's 1, their main is the type of figure round
     */
    public final int shape(){
        return Entity.ROUND;
    }
    
    /**
     * Verify if bear can attack something
     * and attack if this can
     */
    public void attack() {
        int[] position = habitat.find(this);
        if (position == null) {
            return; 
        }
        
        int height = position[0];
        int broad = position[1];  
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int deltaRow = height - i, deltaColumn = broad - j;
                boolean entityInBounds = habitat.isInside(deltaRow, deltaColumn);
                boolean selfPosition = i == 0 && j == 0;
                if (entityInBounds && !selfPosition) { // Ayudado por Gemini Pro IA
                // 1. Obtenemos la entidad que está en esa posición
                Entity target = habitat.get(deltaRow, deltaColumn);
                
                // 2. Verificamos que no sea nula y que sea un Organismo (para no atacar tormentas u objetos no vivos)
                if (target instanceof Organism) {
                    Organism org = (Organism) target;
                    
                    // 3. Le aplicamos el daño usando tu nuevo método
                    org.makeDamage(DAMAGE);
                    
                    // 4. Verificamos si su energía llegó a 0 o menos para eliminarlo del tablero
                    if (org.getEnergy() <= 0) {
                        target.disappear();
                    }
                }
                }
            }
        }
    }
}