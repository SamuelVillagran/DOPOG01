import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class PlaylistTest{

    private String[][] cancionesSalsa;
    private String[][] songs;
    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        songs = new String[][]
            {{"One", "U2", "Rock", "4", "*****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "****"}};
        
        cancionesSalsa = new String[][] {
            {"Plástica", "Rubén Blades", "Salsa", "5", "****"},
            {"La Gota", "Grupo Niche", "Salsa", "4", "***"},
            {"El Periódico de Ayer", "Héctor Lavoe", "Salsa", "3", "****"}
        };
        
    }

    
     @Test
    public void shouldCreateAEmptyPlaylist(){
        String [][] songs = {};
        Playlist pl=new Playlist(songs);
        assertEquals(0, pl.size());     //Se tendría que poder hacer una lista vacia 
    }    
   
    @Test
    public void shouldCreateAPlaylist(){
        Playlist pl=new Playlist(songs);
        assertEquals(5, pl.size());   //Se deberia agregar todas las canciones
    }    
    
    @Test
    public void shouldNotCreateABadPlaylist(){
        String [][] someBadSongs = { // String[] generado por Gemini 3.1 Pro
        {"One", "U2", "Rock", "4", "*****"},           // VÁLIDA: Cumple todo.
        {"Numb", null, "Rock", "3", null},             // INVÁLIDA: ¡Falta el artista (es obligatorio)!
        {null, "Pearl Jam", "Rock", "5", "****"},      // INVÁLIDA: ¡Falta el título (es obligatorio)!
        {"Creep", "Radiohead", "Rock", "99", "*****"}, // INVÁLIDA: ¡La duración es 99 (debe ser de 1 a 9)!
        {"Dreams", "Fleetwood Mac", null, "4", "****"} // VÁLIDA: Falta el género, pero el género no es obligatorio.
            };
        Playlist pl=new Playlist(someBadSongs);
        assertEquals(2, pl.size());   //Solo se deben permitir dos canciones, el resto no cumplen el invariante
    }  
    
    @Test
    public void shouldRecognizeEqualPlaylists() {
       String [][] sameSongs=
            {{"ONE", "U2", "Rock", "4", "*****"},
             {"   Numb", "Linkin Park   ", "Rock", "3", null},
             {"Alive", "PEARL   JAM", "Rock", "5", "****"},
             {"Creep", "Radiohead", "Rock", null, "*****"},
             {"Dreams", "Fleetwood Mac", null, "4", "**   **"}};
       Playlist plOriginal = new Playlist(songs), plProof = new Playlist(sameSongs); // Se crea la playlist donde se va a probar
       assertEquals(plOriginal, plProof);
    }
    
    @Test
    public void shouldAddSong(){
       String [][] songs=
            {{"One", "U2", "Rock", "4", "****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"}};
       Playlist plProof = new Playlist(songs);
       assertEquals(3, plProof.size());
       String[] songOfProof = new String[]{"Kalo", "JB", "Pop", "3", "*****"}; // Cancion de prueba
       plProof = plProof.add(songOfProof); // Agrega
       assertEquals(4, plProof.size()); // Deberia agregar una decima en size porque se le agregó una canción
       assertTrue(Arrays.equals(songOfProof, plProof.getSongs()[3])); // Deberia coincidir que se agrega en la última parte
    }
    
    @Test
    public void shouldDeleteSong(){
       String [][] songs=
            {{"One", "U2", "Rock", "4", "****"},
             {"Numb", "Linkin Park", "Rock", "3", null},
             {"Alive", "Pearl Jam", "Rock", "5", "****"}};
       Playlist plProof = new Playlist(songs);
       assertEquals(3, plProof.size()); // Deberia tener solo las 3 canciones puestas
       String[] songOfProof = new String[]{"Numb", "Linkin Park", "Rock", "3", null};
       plProof = plProof.delete(songOfProof);
       assertEquals(2, plProof.size()); // Al eliminar se le quita una decima de size
       assertTrue(Arrays.equals(new String[]{"One", "U2", "Rock", "4", "****"}, plProof.getSongs()[0])); // deberian coincidir las únicas dos que se tienen como canciones
       assertTrue(Arrays.equals(new String[]{"Alive", "Pearl Jam", "Rock", "5", "****"}, plProof.getSongs()[1]));
    }
    
    @Test //Prueba generada con Gemini Pro 3.1
    public void shouldSelectSongsWithMultipleConditionsAndReturnEmpty() {
        Playlist pl = new Playlist(cancionesSalsa);
        String[] filtro = {null, null, null, "3", "*****"};
        
        String[][] resultado = pl.select(filtro);
        
        assertEquals(0, resultado.length);
    }
    
    @Test //Prueba generada con Gemini Pro 3.1
    public void shouldSelectSongsByMinutesOnly() {
        Playlist pl = new Playlist(cancionesSalsa);
        String[] filtro = {null, null, null, "3", null};
        
        String[][] resultado = pl.select(filtro);
        
        assertEquals(1, resultado.length);
        String[] songExpected = new String[]{"El Periódico de Ayer", "Héctor Lavoe", "Salsa", "3", "****"};
        assertArrayEquals(songExpected, resultado[0]); 
    }
    
    @Test //Prueba generada con Gemini Pro 3.1
    public void shouldSelectAllSongsWhenFilterIsEmpty() {
        Playlist pl = new Playlist(cancionesSalsa);
        String[] filtro = {null, null, null, null, null};
        
        String[][] resultado = pl.select(filtro);
        
        assertEquals(3, resultado.length);
        assertArrayEquals(new String[]{"Plástica", "Rubén Blades", "Salsa", "5", "****"}, resultado[0]);
        assertArrayEquals(new String[]{"La Gota", "Grupo Niche", "Salsa", "4", "***"}, resultado[1]);
        assertArrayEquals(new String[]{"El Periódico de Ayer", "Héctor Lavoe", "Salsa", "3", "****"}, resultado[2]);
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
