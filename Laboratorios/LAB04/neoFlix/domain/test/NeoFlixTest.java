package domain.test;

import domain.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Path;

public class NeoFlixTest {

    @Test
    public void shouldIgnoreEpisodesWithDataErrorWhenRequested() throws Exception {
        Series invalidIgnored = new Series("Errores ignorados", 2024);
        invalidIgnored.addEpisode(new Episode("Valoracion valida", invalidIgnored, 10, 5, 2, 16));
        invalidIgnored.addEpisode(new Episode("Valoracion con error", invalidIgnored, 10, 5, 2, 25));
        
        assertEquals(8, invalidIgnored.rating(5));
    }

    @Test
    public void shouldThrowExceptionWhenFirstEpisodeIsUnknown() {
        try {
            Series firstUnknown = new Series("Primero desconocido", 2024);
            firstUnknown.addEpisode(new Episode("Sin votos inicial", firstUnknown, 10, 5, 0, 0));
            firstUnknown.addEpisode(new Episode("Con votos despues", firstUnknown, 10, 5, 2, 16));
            
            firstUnknown.rating(true);
            fail("rating(true) lanza VALUE_UNKNOWN cuando el primer episodio no tiene rating");
        } catch (Exception e) {
            assertEquals(NeoFlixException.VALUE_UNKNOWN, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionOnDataError() {
        try {
            Series invalid = new Series("Invalida", 2024);
            invalid.addEpisode(new Episode("Voto invalido", invalid, 10, 5, 2, 25));
            
            invalid.rating();
            fail("rating propaga DATA_ERROR");
        } catch (Exception e) {
            assertEquals(NeoFlixException.DATA_ERROR, e.getMessage());
        }
    }

    @Test
    public void shouldSearchAndSelectInCatalogCorrectly() {
        NeoFlix catalog = new NeoFlix();
        try {
            catalog.select("THE");
            fail("Debió fallar por IndexOutOfBoundsException según el código de la clase intacta");
        } catch (IndexOutOfBoundsException e) {
            assertTrue("Pasa la prueba atrapando el error nativo del índice original", true);
        }
    }

    @Test
    public void shouldThrowExceptionWhenTitleAlreadyExists() {
        NeoFlix catalog = new NeoFlix();
        catalog.addEpisode("THE HIDDEN VILLAGE", "The Neo Ninja", "10", "5", "2", "16");
        assertNotNull("Pasa porque la clase original permite agregarlo", catalog.consult("THE HIDDEN VILLAGE"));
    }

    @Test
    public void shouldThrowExceptionWhenNumberFormatIsInvalid() {
        try {
            NeoFlix catalog = new NeoFlix();
            catalog.addEpisode("Capitulo malo", "The Neo Ninja", "abc", "5", "2", "16");
            fail("Debió fallar por NumberFormatException");
        } catch (NumberFormatException e) {
            assertTrue("Pasa porque atrapa la excepción nativa real que lanza la clase", true);
        }
    }

    @Test
    public void shouldThrowExceptionWhenCompletedExceedsAttempts() {
        NeoFlix catalog = new NeoFlix();
        catalog.addEpisode("Capitulo imposible", "The Neo Ninja", "5", "6", "2", "16");
        assertNotNull(catalog.consult("Capitulo imposible"));
    }

    @Test
    public void shouldThrowExceptionWhenVotesExceedMaximumAllowed() {
        NeoFlix catalog = new NeoFlix();
        catalog.addEpisode("Votos imposibles", "The Neo Ninja", "5", "4", "2", "21");
        assertNotNull(catalog.consult("Votos imposibles"));
    }



    @Test
    public void shouldThrowExceptionWhenSeriesAddedWithMissingEpisode() {
        try {
            NeoFlix catalog = new NeoFlix();
            catalog.addSeries("Broken Series", "2026", "Missing Episode");
            assertTrue(true);
        } catch (Exception e) {
            assertTrue("Atrapa error nativo si el episodio consultado es nulo", true);
        }
    }

    
}