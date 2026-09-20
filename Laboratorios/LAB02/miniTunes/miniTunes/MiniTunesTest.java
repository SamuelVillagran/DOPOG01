import org.junit.After;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MiniTunesTest.
 *
 * @author  Gualdron-STEVEEN
 */
public class MiniTunesTest
{
    private String[][] songs1;
    private String[][] songs2;
    private String[][] songs3;
    private String[][] songs4;
    private String[][] songs5;
    private String[][] songs6;
    private MiniTunes miniTunes;
    
    @BeforeEach
    public void playlists() { //IA to fill songs
        songs1 = new String[][]
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
             
        songs2 = new String[][]
            {{"Plástica", "Rubén Blades", "Salsa", "5", "****"},
             {"La Gota", "Grupo Niche", "Salsa", "4", "***"},
             {"El Periódico de Ayer", "Héctor Lavoe", "Salsa", "3", "****"}};
             
        songs3 = new String[][]
            {{"Get Lucky", "Daft Punk", "Electronic", "4", "*****"},
             {"Strobe", "deadmau5", "Electronic", "8", "****"},
             {"Midnight City", "M83", "Synthpop", "4", null},
             {"Clarity", "Zedd", "EDM", "4", "***"}};
             
        songs4 = new String[][]
            {{"Billie Jean", "Michael Jackson", "Pop", "4", "*****"},
             {"Uptown Funk", "Bruno Mars", "Funk", "4", "****"},
             {"Shape of You", "Ed Sheeran", "Pop", "3", "***"},
             {"Levitating", "Dua Lipa", "Pop", null, "****"}};
             
        songs5 = new String[][]
            {{"La Camisa Negra", "Juanes", "Latin Rock", "3", "****"},
             {"Danza Kuduro", "Don Omar", "Reggaeton", "3", "*****"},
             {"La Tierra del Olvido", "Carlos Vives", "Vallenato", "4", "*****"},
             {"Bidi Bidi Bom Bom", "Selena", "Cumbia", "3", "****"}};
        
        songs6 = new String[][] {{"La Camisa Negra", "Juanes", "Latin Rock", "3", "****"}};
        
        miniTunes = new MiniTunes();
    }
    
    /**
     *  should save all the names
     */
    @Test 
    public void define(){
        miniTunes.define("play1");
        miniTunes.define("play2");
        miniTunes.define("play3");
        miniTunes.define("play4");
        miniTunes.define("play5");
        assertEquals(miniTunes.size(),5);
    }
    
    /**
     *  should tell me the size of everything
     */
    @Test 
    public void size(){
        miniTunes.define("play1");
        miniTunes.assign("play1", songs1);
        int a = miniTunes.size("play1");
        assertEquals(5,a);
    }
    
    /**
     *  should put all the names together, separated by a comma.
     */   
    @Test
    public void t0String(){
        miniTunes.define("play1");
        miniTunes.define("play2");   
        assertEquals("play1,play2",miniTunes.toString());
    }
    
    /**
     *  should put all the names together, separated by a comma.
     */   
    @Test
    public void t0StringPlayList(){
        miniTunes.define("play1");
        miniTunes.assign("play1", songs6);
        assertEquals("TITLE   ARTIST   GENRE   DURATION   RATING\nLa camisa negra   Juanes   Latin rock   3     ****\n", miniTunes.toString("play1"));    
    }
    /**
     * Test the unit operation of add
     */
    @Test
    public void assignUnaryAddTest() {
        miniTunes.define("playOrigen");
        miniTunes.define("playDestino");
        miniTunes.assign("playOrigen", songs6); 
        String[] newsong = {"Gasolina", "Daddy Yankee", "Reggaeton", "3", "*****"};
        miniTunes.assignUnary("playDestino", "playOrigen", 'a', newsong);
        assertEquals(2, miniTunes.size("playDestino"));
        assertEquals(1, miniTunes.size("playOrigen"));
    }

    /**
     * Test the unit operation of delete
     */
    @Test
    public void assignUnaryDeleteTest() {
        miniTunes.define("playOrigen");
        miniTunes.define("playDestino");
        miniTunes.assign("playOrigen", songs5); 
        String[] deletesong = {"La Camisa Negra", "Juanes", "Latin Rock", "3", "****"};   
        miniTunes.assignUnary("playDestino", "playOrigen", 'd', deletesong);
        assertEquals(3, miniTunes.size("playDestino"));
    }
    
    /**
     *Test the binary union operation
     */
    @Test
    public void assignBinaryUnionTest() {
        miniTunes.define("playA"); 
        miniTunes.define("playB");
        miniTunes.define("playC");
        miniTunes.assign("playB", songs6); 
        miniTunes.assign("playC", songs5);
        miniTunes.assignBinary("playA", "playB", 'u', "playC");
        assertEquals(4, miniTunes.size("playA"));
    }

    /**
     * Test the binary intersection operation
     */
    @Test
    public void assignBinaryIntersectionTest() {
        miniTunes.define("playA");
        miniTunes.define("playB");
        miniTunes.define("playC");
        miniTunes.assign("playB", songs5);
        miniTunes.assign("playC", songs6);
        miniTunes.assignBinary("playA", "playB", 'i', "playC");
        assertEquals(1, miniTunes.size("playA"));
    }

    /**
     * Try the binary difference operation
     */
    @Test
    public void assignBinaryDifferenceTest() {
        miniTunes.define("playA");
        miniTunes.define("playB");
        miniTunes.define("playC");
        miniTunes.assign("playB", songs5);
        miniTunes.assign("playC", songs6);
        miniTunes.assignBinary("playA", "playB", 'd', "playC");
        assertEquals(3, miniTunes.size("playA"));
    }
    
}