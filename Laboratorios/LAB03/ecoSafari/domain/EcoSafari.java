package domain;

import java.util.List;
import java.util.ArrayList;

public class EcoSafari{
 
    private static final int SIZE=25;
    private Entity[][] cells;
    private int counterTicTac = 0;
    
    /**
     * Constructs a new EcoSafari
     */
    public EcoSafari() {
        cells=new Entity[SIZE][SIZE];
        someEntities();
    }

    /**
     * Pupulates the EcoSafari with some entities
     */
    public void someEntities(){   
        Elephant dumbo = new Elephant(this, 5, 5);
        Elephant babar = new Elephant(this, 10, 10);        
        Elephant white = new Elephant(this, 3, 1);
<<<<<<< HEAD
        Storm sharkNado = new Storm(this, 4, 0);
        Storm thor  = new Storm(this, 7, 0);
        Storm tempest   = new Storm(this, 9, 0);
        Bush mopane  = new Bush(this, 3, 5);
        Bush acacia  = new Bush(this, 8, 5);
<<<<<<< HEAD:Laboratorios/ecoSafari/domain/EcoSafari.java
        
        Bear pablo = new Bear(this, 12, 3);
        Bear samuel = new Bear(this, 18, 9);
=======

        African gualdron = new African(this,8,8);
        African steveveen = new African(this,15,15);
        
        African p1 = new African(this,1,1);
        Elephant p2 = new Elephant(this,3,1);
        Bush bush = new Bush(this, 2, 1);
>>>>>>> d238d586d72c5625abe3f87698dba4e3a2ae5d65:Laboratorios/LAB03/ecoSafari/domain/EcoSafari.java
    }
=======
            }
>>>>>>> 4192d4ff8be721095eeb64279a261d772db6d79c
    
    /**
     * Returns the size of the EcoSafari 
     * @return 
     */
    public int  getSize(){
        return SIZE;
    }

    /**
     * Determines whether a position is inside the EcoSafari
     * @param r the row
     * @param c the column
     * @return 
     */
    public boolean isInside(int r, int c){
        
        return ((0<=r) && (r<SIZE) && (0<=c) && (c<SIZE));
    }
    
    /**
     * Returns the entity located at a specified position
     * @param r the row
     * @param c the column
     * @return 
     */
    public Entity get(int r,int c){
        return (isInside(r,c)? cells[r][c]: null);
    }

    /**
     * Places an entity at a specified position
     * @param r the row
     * @param c the column
     */
    public void set(Entity e, int r, int c){
        if (isInside(r,c)){
            Entity entity = cells[r][c];
            boolean thereIsCollision = (entity != null) && (e != null) && (entity != e);
            cells[r][c]=e;
            if (thereIsCollision){
                entity.collideWith(e);
                e.collideWith(entity);
            }
        }
    }

    
    /**
     * Finds the position of a specified entity
     * @param e the entity
     * @return an array {row, column} if the entity is found. null otherwise
     */
    public int[]  find(Entity e){
       int[] position=null;
       for (int r=0 ; r<SIZE && position== null; r++){
           for (int c=0 ; c<SIZE && position==null ;c++){
               if (cells[r][c]==e){
                   position=new int [] {r,c};
               }
           }
       }
       return position;
    }
    
 
    /**
     * Advances the simulation by one time step
     */
    public void ticTac(){ 
        boolean isCounterEven = counterTicTac%2==0;
        List<Entity> entitiesThisTurn = snapshotEntities();
        for (Entity currentEntity : entitiesThisTurn){
            if (isCounterEven){ //First, all entities execute their tic() action
                currentEntity.tic();
            } else { //Then, all entities execute their tac() actions
                currentEntity.tac();
            }
        }
        counterTicTac++;
    }

    /**
     * Captures the entities currently on the board, so that ticTac() can
     * iterate over a stable list even as the board is mutated by the
     * entities' own actions.
     * @return the list of entities present at the moment of the call
     */
    private List<Entity> snapshotEntities() {
        List<Entity> entities = new ArrayList<>();
        for (int f = 0; f < SIZE; f++) {
            for (int c = 0; c < SIZE; c++) {
                Entity entity = cells[f][c];
                if (entity != null) {
                    entities.add(entity);
                }
            }
        }
        return entities;
    }
}
