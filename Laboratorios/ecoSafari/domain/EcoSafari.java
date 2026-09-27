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
        Storm sharkNado = new Storm(this, 4, 0);
    }
    
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
     * entities' own actions (e.g. Storm jumping rows). Storms are placed
     * first in the list so they always act before the rest of the entities
     * within the same turn.
     * @return the list of entities present at the moment of the call
     */
    private List<Entity> snapshotEntities(){ // Hecho por Claude Sonnet 4.6
        List<Entity> storms = new ArrayList<>();
        List<Entity> others = new ArrayList<>();
        for (int f=0; f<SIZE; f++){
            for (int c=0; c<SIZE; c++){
                Entity entity = cells[f][c];
                if (entity instanceof Storm){
                    storms.add(entity);
                } else if (entity!=null){
                    others.add(entity);
                }
            }
        }
        storms.addAll(others);
        return storms;
    }
}
