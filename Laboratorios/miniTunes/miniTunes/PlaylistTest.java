import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PlaylistTest{

    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }

    
     @Test
    public void shouldCreateAEmptyPlaylist(){
        String [][] songs = {};
        Playlist pl=new Playlist(songs);
        assertEquals(0, pl.size());     
    }    
   
    @Test
    public void shouldCreateAPlaylist(){
        String [][] songs =
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
        Playlist pl=new Playlist(songs);
        assertEquals(5, pl.size());   
    }    
    
    @Test
    public void shouldNotCreateABadPlaylist(){
        String [][] songs=
            {{"One", "U2", "Rock", "4", "*******"},
             {"Numb", "Linkin Park", "Rock", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", null, "Rock", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "****"}};
        Playlist pl=new Playlist(songs);
        assertEquals(3, pl.size());   
    }  
    
    @Test
    public void shouldRecognizeEqualPlaylists(){
       String [][] songs=
            {{"One", "U2", "Rock", "4", "*******"},
             {"Numb", "Linkin Park", "Rock", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", null, "Rock", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "****"}}; 
       String [][] sameSongs=
            {{"ONE", "U2", "Rock", "4", "*******"},
             {"   Numb", "Linkin Park   ", "Rock", "Rock", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", null, "ROCK", null, "*****"},
             {null, "Fleetwood Mac", null, "4", "**   **"}};
       Playlist plOriginal = new Playlist(songs), plProof = new Playlist(sameSongs);
       assertEquals(plOriginal, plProof);
    }
    
    @Test
    public void shouldAddSong(){
       String [][] songs=
            {{"One", "U2", "Rock", "4", "*******"},
             {"Numb", "Linkin Park", "Rock", "Rock", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"}};
       Playlist plProof = new Playlist(songs);
       plProof = plProof.add(new String[]{"Kalo", "JB", "Pop", "3", "*****"});
       assertEquals(4, plProof.size());
    }
    
    /**
    @Test
    public void shouldPass() {
        String proof1 = "youpass", proof2 = "youpass";
        assertEquals(proof1, proof2);
    }
    
    @Test
    public void shouldFail() {
        fail();
    }
    
    @Test
    public void shouldError() {
        String variable = null;
        variable.charAt(5);
    }
    */
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
    }
}
