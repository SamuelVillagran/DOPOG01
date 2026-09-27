package domain;


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
            cells[r][c]=e;
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
        boolean isCounterEven = false, existsCurrentEntity = false;
        Entity currentEntity = null;
        for (int f = 0; f<SIZE; f++) {
            for (int c = 0; c<SIZE; c++) {
                isCounterEven = counterTicTac%2==0;
                currentEntity = cells[f][c];
                existsCurrentEntity = currentEntity != null;
                if (existsCurrentEntity) {
                    if (isCounterEven) {//First, all entities execute their tic() action
                        currentEntity.tic();
                    } else if (!isCounterEven) {//Then, all entities execute their tac() actions
                        currentEntity.tac();
                    }
                }
            }
        }
        counterTicTac++;
    }

}
