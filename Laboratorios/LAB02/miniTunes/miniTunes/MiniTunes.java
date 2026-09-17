import java.util.TreeMap;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** MiniTunes.java
 * 
 * @author ESCUELA 2026-02
 */
    
public class MiniTunes{
    
    private TreeMap<String,Playlist> playlists;
    private boolean operation;
    
    public MiniTunes(){
        playlists = new TreeMap<String,Playlist>();
       
    }
    
    /**
     * Return a playlists size
     */
    public int size(){
        return playlists.size();
    }
    
    
    /**
     * Define a new playlist name
     */
    public void define(String name){
        playlists.put(name,null);
        
    }
     
    /**
     * Assign a playlist to an existing playlist name
     */
    //a := playlist
    public void assign(String a, String [][] playlist){
        Playlist list = new Playlist(playlist);
        playlists.replace(a, list);
        
    }    


    /**
     * Return a playlist's size
     */
    public int size(String a){
        Playlist list = playlists.get(a);
        return list.size();
    }
    
    /**
     * @return the playlist names in alphabetical order. comma-separated
     */

    public String toString(){
        return String.join(",", playlists.keySet());
    }
    /**
     * @return the string representation of a playlist.
     */
    public String toString(String name){
        Playlist list = playlists.get(name);
        return list.toString();

    }    
    /**
     * Assigns the value of a unary operation to a playlist name
     * a := b op parameters
     * The operator characters are: 'a' (add) , 'd' (delete),'s'(select)
     * For add and delete, the values correspond to the song data. For select, the parameters define the search pattern.
     */

    public void assignUnary(String a, String b, char op, String [] values){
        switch (op) {
            case 'a':
                Playlist copy1 =  new Playlist(playlists.get(b).getSongs());
                copy1.add(values);
                assign(a,copy1.getSongs());
                break;
            case 'd':
                Playlist copy2 =  new Playlist(playlists.get(b).getSongs());
                copy2.delete(values);
                assign(a,copy2.getSongs());
                break;
            case 's':
                Playlist copy3 =  new Playlist(playlists.get(b).select(values));
                assign(a,copy3.getSongs());
                break;
        }
    }
      
    
    /**Assigns the value of a binary operation to a playlist name
     * a = b op c
     * The operator characters are:  'u' union, 'i' intersection, 'd' difference
     * Songs preserve their original order in the resulting playlist.
     */
    public void assignBinary(String a, String b, char op, String c){
        Set<List<String>> setC = new LinkedHashSet<>();
        for (String[] row : playlists.get(c).getSongs()) {
            setC.add(Arrays.asList(row)); 
        }
        Set<List<String>> setB = new LinkedHashSet<>();
        for (String[] row : playlists.get(b).getSongs()) {
            setB.add(Arrays.asList(row));
        }
        switch (op) {
            case 'u': 
                setB.addAll(setC);
                break; 
            case 'i': 
                setB.retainAll(setC);
                break;
            case 'd': // 
                setB.removeAll(setC);
                break;
        }
        String[][] lista = new String[setB.size()][];
        int i = 0;
        for (List<String> row : setB) {
            lista[i] = row.toArray(new String[0]);
            i++;
        }
        assign(a, lista);
    }
  
    /**
     * If the last operation was successfully completed
     */
    
    public boolean ok() { // analisis de como hacer una buena comprobacion con IA
        if (this.playlists == null) {
            return false;
        }

        for (Playlist playlist : this.playlists.values()) {
            if (playlist == null) {
                return false;
            }
        }

        return true;
    }
}
    



